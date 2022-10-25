const {
  create,
  exist,
  createMessage,
  getAllMessagesByChatId,
  getChats,
} = require('../chat/chat.repository');

const { findUserById } = require('../users/users.services');

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

    const checkIfUserExist = await findUserById(userId);

    if (!checkIfUserExist) return;

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

const getChatsByUserId = async (data) => {
  try {
    const { userId, chatType } = data;

    const checkIfUserExist = await findUserById(userId);

    if (!checkIfUserExist) return;

    const chats = await getChats({ userId, chatType });

    if (!chats) return;

    return chats;
  } catch (error) {
    console.log(error);
  }
};

const userChatById = async (identifier) => {
  try {
    const { userId, chatId } = identifier;

    const checkIfUserExist = await findUserById(userId);
    if (!checkIfUserExist) return;

    const getChat = await exist({ chatId });

    if (!getChat) return;

    return getChat;
  } catch (error) {
    console.log(error);
  }
};

module.exports = {
  createChat,
  getChatsByUserId,
  userChatById,
  createMsg,
  getMessages,
};
