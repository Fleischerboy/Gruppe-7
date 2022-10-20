const { db } = require('../../utils/db');
const auth = require('../middleware/auth');

const getAllProducts = () => {
  return db.product.findMany();
};

const getUserProductsById = (id) => {
  return db.product.findMany({
    where: {
      ownerId: parseInt(id),
    },
  });
};

const getProduct = (id) => {
  return db.product.findUnique({
    where: {
      id: id,
    },
  });
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
  getUserProductsById,
  getProduct,
};
