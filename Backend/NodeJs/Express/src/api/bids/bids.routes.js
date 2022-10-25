const express = require('express');
const auth = require('../middleware/auth');
const router = express.Router();
const {
  createBid,
  getAllBids,
  getBid,
} = require('../bids/bids.service');

const { getProductById } = require('../products/products.service');

router.get('/api/bids', async (req, res) => {
  try {
    const bids = await getAllBids();
    if (bids != null) {
      res.json(bids);
    }
  } catch (err) {
    console.log(err);
  }
});

router.get('/api/bids/:bidId', async (req, res) => {
  try {
    const bidId = parseInt(req.params.bidId);
    console.log(bidId);
    const bid = await getBid(bidId);
    if (bid != null) {
      res.json(bid);
    }
  } catch (err) {
    console.log(err);
  }
});

router.post(
  '/api/products/:productId/createbid',
  auth,
  async (req, res) => {
    try {
      const productId = parseInt(req.params.productId);
      const bidAmount = parseFloat(req.body.bidAmount);
      const bidUserId = req.body.userId;

      if (!(bidAmount && productId && bidUserId)) {
        return res
          .status(400)
          .send(
            'Missing required fields: bidAmount, productId and userId'
          );
      }

      const getProduct = await getProductById(productId);

      if (!getProduct)
        return res.status(400).send('product not found');

      const { id, ownerId } = getProduct;
      const bid = await createBid({
        id,
        ownerId,
        bidUserId,
        bidAmount,
      });
      if (bid) {
        res.status(201).json(bid);
      } else {
        res.status(500).send('Failed creating bid');
      }
    } catch (err) {
      console.log(err);
      return res.status(500).send('Failed creating bid');
    }
  }
);

module.exports = router;
