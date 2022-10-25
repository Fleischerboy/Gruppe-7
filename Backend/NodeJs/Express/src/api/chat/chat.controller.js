import * as chatService from './chat.service';
import * as bidService from '../bids/bids.service';

export const getAllChats = (req, res) => {};

export const geChatById = (req, res) => {};

export const createChat = (req, res) => {
  chatService.create();
};

export const createMessageToASpecificChat = (req, res) => {};
