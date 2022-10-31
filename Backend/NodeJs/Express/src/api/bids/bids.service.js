const { db } = require('../../utils/db');

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
const createBid = (bid) => {
  const { id, ownerId, bidUserId, bidAmount } = bid;
  return db.bid.create({
    data: {
      productId: id,
      productOwnerId: ownerId,
      bidUserId: parseInt(bidUserId),
      bidAmount: bidAmount,
      isBidAccepted: false,
    },
  });
};

const acceptBid = (bidId) => {
  return db.bid.update({
    where: {
      id: bidId,
    },
    data: {
      isBidAccepted: true,
    },
  });
};

module.exports = {
  createBid,
  getAllBids,
  getBid,
  acceptBid,
};
