const { db } = require('../../utils/db');

const create = async (data) => {
  return db.chat.create({ data });
};

module.exports = {
  create,
};
