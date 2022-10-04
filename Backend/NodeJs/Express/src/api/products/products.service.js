const { db } = require("../../utils/db");
const auth = require("../middleware/auth");

const getAllProducts = () => {
  return db.product.findMany();
};

const getProduct = (id) => {
  return db.product.findUnique({
    where: {
      id: id,
    },
  });
};

const createProduct = (user) => {
  const { userId, title, imageUrl, description, address } = user;
  return db.product.create({
    data: {
      ownerId: parseInt(userId),
      title: title,
      imageUrl: imageUrl,
      description: description,
      address: address,
    },
  });
};

module.exports = {
  createProduct,
  getAllProducts,
  getProduct,
};
