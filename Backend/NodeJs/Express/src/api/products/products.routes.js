const express = require('express');
const auth = require('../middleware/auth');
const router = express.Router();
const {
  createProduct,
  getAllProducts,
} = require('../products/products.service');

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
        res.status(400).send('missing input!');
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
