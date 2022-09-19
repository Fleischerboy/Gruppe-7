const express = require('express');
const router = express.Router();

router.get('/products', async (req,res) => {
res.send("user products")
})


module.exports = router;

