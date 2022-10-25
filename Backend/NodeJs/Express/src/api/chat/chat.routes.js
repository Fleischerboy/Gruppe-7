const express = require('express');
const router = express.Router();
const auth = require('../middleware/auth');
const {
  createMessageToASpecificChat,
  geChatById,
  getAllMessagesByChatId,
} = require('./chat.controller');

router.get('/api/chats', async (req, res) => {});

// get 1 chat
router.get('/api/chats/:chatId', async (req, res) => {
  await geChatById(req, res);
});

router.get('/api/chats/:chatId/messages', async (req, res) => {
  await getAllMessagesByChatId(req, res);
});

router.post('/api/chats/:chatId/newMessage', async (req, res) => {
  await createMessageToASpecificChat(req, res);
});

router.post('/api/chats/createChat', async (req, res) => {});

module.exports = router;
