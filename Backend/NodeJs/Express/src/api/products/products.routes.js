const express = require("express");
const auth = require("../middleware/auth");
const router = express.Router();
const {
  createProduct,
  getAllProducts,
} = require("../products/products.service");

router.get("/products", async (req, res, next) => {
  try {
    const products = await getAllProducts();
    if (products != null) {
      res.json(products);
    }
  } catch (err) {
    next();
  }
});

router.post("/users/:userId/createProduct", auth, async (req, res, next) => {
  try {
    const userId = parseInt(req.params.userId);
    console.log(userId);
    const { title, imageUrl, description, address } = req.body;

    if (!(title && imageUrl && description && address)) {
      return res
        .status(400)
        .send("You must provide an full name, email and password.");
    }

    const product = await createProduct({
      userId,
      title,
      imageUrl,
      description,
      address,
    });

    if (product != null) {
      res.status(200).json(product);
    } else {
      res.status(400);
    }
  } catch (err) {
    next(err);
  }
});

module.exports = router;
