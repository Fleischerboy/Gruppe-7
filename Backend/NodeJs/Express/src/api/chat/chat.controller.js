import * as chatService from './chat.service';

export const getAllChats = (req, res) => {};

export const geChatById = (req, res) => {};

export const createChat = (req, res) => {
  chatService.create();
};

export const createMessageToASpecificChat = (req, res) => {};
