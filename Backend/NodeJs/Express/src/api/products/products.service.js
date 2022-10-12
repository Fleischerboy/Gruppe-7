const { db } = require('../../utils/db');
const auth = require('../middleware/auth');

const getAllProducts = () => {
  return db.product.findMany();
};

const createProduct = (user) => {
  const {
    userId,
    title,
    imageUrl,
    description,
    productPrice,
    address,
  } = user;
  return db.product.create({
    data: {
      ownerId: parseInt(userId),
      title: title,
      imageUrl: imageUrl,
      description: description,
      address: address,
      // må gjøres noe her
      productPrice: parseFloat(productPrice).toFixed(2),
    },
  });
};

module.exports = {
  createProduct,
  getAllProducts,
};
