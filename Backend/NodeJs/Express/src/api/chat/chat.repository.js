const { db } = require('../../utils/db');

const create = async (data) => {
  return db.chat.create({ data });
};

const exist = async ({ chatId }) => {
  return db.chat.findUnique({
    where: {
      id: chatId,
    },
  });
};

const createMessage = async (data) => {
  return db.chatMessages.create({ data });
};

const getAllMessagesByChatId = async ({ chatId }) => {
  return db.chatMessages.findMany({
    where: {
      chatId: chatId,
    },
  });
};

module.exports = {
  create,
  exist,
  createMessage,
  getAllMessagesByChatId
};
