const express = require("express");
const { findUserById } = require("./users.services");
const auth = require("../middleware/auth");
const router = express.Router();

router.get("/api/users/:userId", auth, async (req, res, next) => {
  const userId = req.params.userId;
  const user = await findUserById(userId);
  if (user) {
    return res.json(user);
  }
  res.status(404).send("user not found");
});

module.exports = router;
