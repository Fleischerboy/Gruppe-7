const {
  create,
  exist,
  createMessage,
  getAllMessagesByChatId,
} = require('../chat/chat.repository');

const createChat = async (data) => {
  const { productOwnerId, bidUserId, productId } = data;
  const createdChat = await create({
    sellerId: productOwnerId,
    buyerId: bidUserId,
    productId: productId,
  });

  if (!createdChat)
    return res.status(500).send('Failed creating chat');

  return createdChat;
};

const createMsg = async (data) => {
  try {
    const { userId, chatId, message } = data;
    console.log('hva er dette ', data);

    const checkIfChatExist = await exist({ chatId });

    if (!checkIfChatExist) return;

    const createdMessage = await createMessage({
      userId: parseInt(userId),
      chatId,
      message,
    });

    if (!createdMessage) return;

    return createdMessage;
  } catch (error) {
    console.log(error);
  }
};

const getMessages = async (data) => {
  try {
    const { chatId } = data;

    const messages = await getAllMessagesByChatId({ chatId });

    if (!messages) return;

    return messages;
  } catch (error) {
    console.log(error);
  }
};

const getChatById = async (identifier) => {
  try {
    const { chatId } = identifier;
    const getChat = await exist({ chatId });

    if (!getChat) return;

    return getChat;
  } catch (error) {
    console.log(error);
  }
};

module.exports = {
  createChat,
  createMsg,
  getChatById,
  getMessages,
};
