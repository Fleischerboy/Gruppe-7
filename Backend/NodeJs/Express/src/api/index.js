const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const dotenv = require('dotenv');
dotenv.config();
// Body-parser is the Node. js body parsing middleware.
// It is responsible for parsing the incoming request bodies in a middleware before you handle it.
app.use(bodyParser.json()); // accept json params
app.use(bodyParser.urlencoded({ extended: true })); // accept url encoded params

const auth = require('./auth/auth.routes');
const users = require('./users/users.routes');
const products = require('../api/products/products.routes');
const bids = require('./bids/bids.routes');
const chat = require('./chat/chat.routes');
app.use(auth);

app.use(users);

app.use(products);

app.use(bids);

app.use(chat);

const port = 3000;
// start server
app.listen(process.env.PORT || port, (error) => {
  if (error) return console.log(`Error: ${error}`);
  console.log(`server is running on port ${port}`);
});
