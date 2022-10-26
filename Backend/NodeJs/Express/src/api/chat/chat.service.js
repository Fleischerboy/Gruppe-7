const {
  create,
  exist,
  createMessage,
  getAllMessagesByChatId,
  getChats,
} = require('../chat/chat.repository');
const { getProductById } = require('../products/products.service');

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
    console.log(data)
    const checkIfUserExist = await findUserById(userId);

    if (!checkIfUserExist) return;

    const checkIfChatExist = await exist({ chatId });

    if (!checkIfChatExist) return;

    const createdMessage = await createMessage({
      userId: parseInt(userId),
      chatId: parseInt(chatId),
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

    const updatedChat = updateChatResponse(chats);

    return updatedChat;
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

const updateChatResponse = async (array) => {
  let updatedChatList = [];
  const updated = array.map(async (element) => {
    const chatProduct = await getProductById(element.productId);
    const new_Obj = {
      ...element,
      product: {
        title: chatProduct.title,
        img: chatProduct.imageUrl,
      },
    };
    updatedChatList.push(new_Obj);
  });

  await Promise.all(updated);
  return updatedChatList;
};

module.exports = {
  createChat,
  getChatsByUserId,
  userChatById,
  createMsg,
  getMessages,
};
