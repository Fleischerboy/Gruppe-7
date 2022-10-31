const {
  createMsg,
  getChatsByUserId,
  userChatById,
  getMessages,
} = require('./chat.service');


const getAllChatsByUserId = async (req, res) => {
  const userId = parseInt(req.params.userId);
  const chatType = req.query.chatType;

  if (!chatType) return res.status(400).send('Missing chatType');

  /*
  if (!chatType === 'sellerId' || 'buyerId')
    return res.status(400).send('do not support this type');
    */

  const chats = await getChatsByUserId({ userId, chatType });

  if (!chats) return res.status(404).send('could not find any chat');

  return res.status(200).json(chats);
};

//const createdChat = (req, res) => {};

const getUserChatById = async (req, res) => {
  const userId = parseInt(req.params.userId);
  const chatId = parseInt(req.params.chatId);
  const getChat = await userChatById({ chatId, userId });

  if (!getChat) return res.status(404).send('could not find chat');

  return res.status(200).json(getChat);
};

const getAllMessagesByChatId = async (req, res) => {
  const userId = parseInt(req.params.userId);
  const chatId = parseInt(req.params.chatId);
  const getChat = await userChatById({ chatId, userId });

  if (!getChat) return res.status(404).send('could not find chat');

  const messages = await getMessages({ chatId });

  if (!messages)
    return res.status(404).send('failed finding messages');

  return res.status(200).json(messages);
};

const createMessageToASpecificChat = async (req, res) => {
  const chatId = parseInt(req.params.chatId);
  const userId = parseInt(req.params.userId);
  const { message } = req.body;

  if (!userId) {
    return res.status(400).send('Missing userId');
  }

  const createdMessage = await createMsg({ userId, chatId, message });

  if (!createdMessage) {
    return res.status(500).send('could not create msg');
  }

  return res.status(201).json(createdMessage);
};

module.exports = {
  createMessageToASpecificChat,
  getUserChatById,
  getAllMessagesByChatId,
  getAllChatsByUserId,
};
