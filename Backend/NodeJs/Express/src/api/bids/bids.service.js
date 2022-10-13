const { db } = require("../../utils/db");

const getAllBids = () => {
  return db.bid.findMany();
};

const getBid = (id) => {
  return db.bid.findUnique({
    where: {
      id: id,
    },
  });
};

// might need the whole objects of product and user?
const createBid = (productId, userId, price) => {
  return db.bid.create({
    data: {
      ownerId: parseInt(userId),
      productId: parseInt(productId),
      price: price,
    },
  });
};

module.exports = {
  createBid,
  getAllBids,
  getBid,
};
