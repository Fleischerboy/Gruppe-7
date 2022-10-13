const express = require("express");
const auth = require("../middleware/auth");
const router = express.Router();
const { createBid, getAllBids, getBid } = require("../bids/bids.service");

router.get("/bids", async (req, res) => {
  try {
    const bids = await getAllBids();
    if (bids != null) {
      res.json(bids);
    }
  } catch (err) {
    console.log(err);
  }
});

router.get("/bids/:bidId", async (req, res) => {
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

router.post("/products/:productId/createbid", async (req, res) => {
  try {
    const productId = parseInt(req.params.productId);
    const userId = req.body.userId;
    const price = parseFloat(req.body.price);

    if (!(price && productId && userId)) {
      return res.status(400).send("something went wrong");
    }

    const bid = await createBid({
      productId,
      userId,
      price,
    });

    if (bid != null) {
      res.status(200).json(bid);
    } else {
      res.status(400);
    }
  } catch (err) {
    console.log(err);
  }
});

module.exports = router;
