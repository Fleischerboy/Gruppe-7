const { db } = require("../../utils/db");
const uuid = require("uuid");

const {
  saltHashPassword,
  checkHashPassword,
} = require("../../utils/passwordHash");

function findUserByEmail(email) {
  return db.user.findUnique({
    where: {
      email,
    },
  });
}

function createUser(user) {
  const hash_data = saltHashPassword(user.password);
  const passwordHash = hash_data.passwordHash;
  const salt = hash_data.salt;
  // make unique id
  const uid = uuid.v4();
  return db.user.create({
    data: {
      unique_id: uid,
      fullname: user.fullname,
      email: user.email.toLowerCase(),
      encrypted_password: passwordHash,
      salt: salt,
    }
  });
}

function findUserById(id) {
  return db.user.findUnique({
    where: {
      id,
    },
  });
}

module.exports = {
  findUserByEmail,
  createUser,
  findUserById,
};