const express = require('express');
const router = express.Router();
const auth = require('../middleware/auth');
import * as chatController from './chat.controller';

router.get('/api/chats', async (req, res) => {});

// get 1 chat
router.get('/api/chat/:chatId', async (req, res) => {});

router.get('/api/chat/:chatId/newMessage', async (req, res) => {});

router.post('/api/chats/createChat', async (req, res) => {});
