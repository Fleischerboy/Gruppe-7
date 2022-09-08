const express = require('express');
const { findUserById } = require('./users.services');

const router = express.Router();

router.get('/profile', async (req, res, next) => {

});

module.exports = router;