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

const getChats = async (data) => {
  const { userId, chatType } = data;
  switch (chatType) {
    case 'sellerId': {
      return db.chat.findMany({
        where: {
          sellerId: userId,
        },
      });
    }
    case 'buyerId': {
      return db.chat.findMany({
        where: {
          buyerId: userId,
        },
      });
    }
    default:
      return;
  }
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
  getChats,
  createMessage,
  getAllMessagesByChatId,
};
