const express = require("express");
const auth = require("../middleware/auth");
const router = express.Router();
const {
  createBid,
  getAllBids,
  getBid,
  acceptBid,
  getBidsOnProduct,
} = require("../bids/bids.service");
const { createChat } = require("../chat/chat.service");

const { getProductById } = require("../products/products.service");

router.get("/api/bids", async (req, res) => {
  try {
    const bids = await getAllBids();
    if (bids) {
      return res.json(bids);
    }
    return res.status(404).send("did not find this resource");
  } catch (err) {
    console.log(err);
    return res.status(500).send("Failed finding bids");
  }
});

router.get("/api/bids/:bidId", async (req, res) => {
  try {
    const bidId = parseInt(req.params.bidId);
    const bid = await getBid(bidId);
    if (bid) {
      return res.json(bid);
    }
    return res.status(404).send("did not find this resource");
  } catch (err) {
    console.log(err);
    return res.status(500).send("failed finding bid");
  }
});

router.get("/api/bids/product/:productId", async (req, res) => {
  try {
    const productId = parseInt(req.params.productId);
    const bids = await getBidsOnProduct(productId);
    if (bids) return res.json(bids);
    return res.status(404).send("No bids on product");
  } catch (err) {
    console.log(err);
    return res.status(500).send("Failed fetching bids");
  }
});

router.post("/api/products/:productId/createbid", auth, async (req, res) => {
  try {
    const productId = parseInt(req.params.productId);
    const bidAmount = parseFloat(req.body.bidAmount);
    const bidUserId = req.body.userId;

    if (!(bidAmount && productId && bidUserId)) {
      return res
        .status(400)
        .send("Missing required fields: bidAmount, productId and userId");
    }

    const getProduct = await getProductById(productId);

    if (!getProduct) return res.status(400).send("product not found");

    const { id, ownerId } = getProduct;
    const bid = await createBid({
      id,
      ownerId,
      bidUserId,
      bidAmount,
    });
    if (!bid) {
      return res.status(500).send("Failed creating bid");
    }
    return res.status(201).json(bid);
  } catch (err) {
    console.log(err);
    return res.status(500).send("Failed creating bid");
  }
});

router.put("/api/bids/:bidId/acceptBid", async (req, res) => {
  try {
    const bidId = parseInt(req.params.bidId);
    const bid = await getBid(bidId);

    if (!bid) return res.status(404).send("bid not found");

    const accept = await acceptBid(bidId);

    if (!accept) return res.status(500).send("Failed to accept the bid");

    const { productOwnerId, bidUserId, productId } = accept;
    const createdChat = await createChat({
      productOwnerId,
      bidUserId,
      productId,
    });

    if (!createdChat) {
      return res.status(500).send("Failed creating chat");
    }

    return res.status(201).json(createdChat);
  } catch (err) {
    console.log(err);
  }
});

module.exports = router;
