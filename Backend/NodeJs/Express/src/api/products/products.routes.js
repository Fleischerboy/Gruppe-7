const express = require('express');
const auth = require('../middleware/auth');
const router = express.Router();
const {
  createProduct,
  getAllProducts,
  getUserProductsById,
} = require('../products/products.service');
const { findUserById } = require('../users/users.services');

router.get('/products', async (req, res, next) => {
  try {
    const products = await getAllProducts();
    if (products != null) {
      res.json(products);
    }
  } catch (err) {
    next();
  }
});

router.get('/users/:userId/products', async (req, res) => {
  try {
    const userId = req.params.userId;
    const findUser = await findUserById(userId);
    if (!findUser) return res.status(404).send('user does not exist');
    const userProducts = await getUserProductsById(userId);

    if (userProducts) {
      return res.status(200).json(userProducts);
    }
    res.status(404).send('products not found');
  } catch (err) {
    console.log(err);
  }
});

router.post(
  '/users/:userId/createProduct',
  auth,
  async (req, res, next) => {
    try {
      const userId = parseInt(req.params.userId);
      const { title, imageUrl, description, productPrice, address } =
        req.body;

      if (
        !(title && imageUrl && description && address && productPrice)
      ) {
        return res.status(400).send('missing input!');
      }

      const product = await createProduct({
        userId,
        title,
        imageUrl,
        description,
        productPrice,
        address,
      });

      if (product != null) {
        res.status(200).json(product);
      } else {
        res.status(400);
      }
    } catch (err) {
      console.log(err);
    }
  }
);

module.exports = router;
