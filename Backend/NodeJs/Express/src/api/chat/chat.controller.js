const {
  createMsg,
  getChatById,
  getMessages,
} = require('./chat.service');

const getAllChats = (req, res) => {};

const createdChat = (req, res) => {};

const geChatById = async (req, res) => {
  const chatId = parseInt(req.params.chatId);
  const getChat = await getChatById({ chatId });

  if (!getChat) return res.status(404).send('could not find chat');

  return res.status(200).json(getChat);
};

const getAllMessagesByChatId = async (req, res) => {
  const chatId = parseInt(req.params.chatId);
  const getChat = await getChatById({ chatId });

  if (!getChat) return res.status(404).send('could not find chat');

  const messages = await getMessages({ chatId });

  if (!messages)
    return res.status(404).send('failed finding messages');

  return res.status(200).json(messages);
};

const createMessageToASpecificChat = async (req, res) => {
  const chatId = parseInt(req.params.chatId);
  const { userId, message } = req.body;

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
  geChatById,
  getAllMessagesByChatId,
};
