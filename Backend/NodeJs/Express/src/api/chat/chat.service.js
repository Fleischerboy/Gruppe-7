const { create } = require('../chat/chat.repository');

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

module.exports = {
  createChat,
};
