const express = require('express');
const router = express.Router();
const jwt = require('jsonwebtoken');
const { checkHashPassword } = require('../../utils/passwordHash');
const {
  findUserByEmail,
  createUser,
  findUserById,
} = require('../users/users.services');

router.post('/signup', async (req, res, next) => {
  try {
    // Get user input
    const { fullname, email, password } = req.body;
    console.log(fullname, email, password);

    // Validate user input
    if (!(email && password && fullname)) {
      res
        .status(400)
        .send('You must provide an full name, email and password.');
    }

    // Validate if user exist in our database
    const existingUser = await findUserByEmail(email);

    if (existingUser) {
      res.status(400);
      throw new Error('Email already in use.');
    }

    // Create user in our database
    const user = await createUser({ fullname, email, password });

    // if user is created successfully
    if (user != null) {
      res.status(200).json(user);
    } else {
      res.status(400);
    }
  } catch (err) {
    console.log(err);
  }
});

router.post('/signin', async (req, res, next) => {
  // Extracting password and email from request
  const { email, password } = req.body;

  // Validate user input
  if (!(email && password)) {
    return res.status(400).send('All input is required');
  }

  const getUser = await findUserByEmail(email);

  if (
    getUser &&
    (await (getUser.encrypted_password ===
      checkHashPassword(password, getUser.salt).passwordHash))
  ) {
    // create token
    const token = jwt.sign(
      { user_id: getUser.id },
      process.env.TOKEN_KEY,
      {
        expiresIn: '10h',
      }
    );

    const { id, fullname, email, createdAt, updatedAt } = getUser;
    res.status(200).json({
      id: id,
      fullname: fullname,
      email: email,
      createdAt: createdAt,
      updatedAt: updatedAt,
      token: token,
    });
  } else {
    return res.status(401).json({
      msg: 'Wrong email or password',
    });
  }
});

module.exports = router;
