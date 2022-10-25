const express = require('express');
const router = express.Router();
const auth = require('../middleware/auth');
const {
  createMessageToASpecificChat,
  getUserChatById,
  getAllMessagesByChatId,
  getAllChatsByUserId,
} = require('./chat.controller');

// get all chats in the application
router.get('/api/chats', async (req, res) => {});

// get all chats by specific user (seller or buyer)
router.get('/api/chats/:userId', async (req, res) => {
  await getAllChatsByUserId(req, res);
});

// get 1 chat
router.get('/api/chats/:userId/:chatId', async (req, res) => {
  await getUserChatById(req, res);
});

router.get(
  '/api/chats/:userId/:chatId/messages',
  async (req, res) => {
    await getAllMessagesByChatId(req, res);
  }
);

router.post(
  '/api/chats/:userId/:chatId/newMessage',
  async (req, res) => {
    await createMessageToASpecificChat(req, res);
  }
);

router.post('/api/chats/createChat', async (req, res) => {});

module.exports = router;
