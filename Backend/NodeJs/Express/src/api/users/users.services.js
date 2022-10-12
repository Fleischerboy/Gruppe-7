const { db } = require('../../utils/db');
const uuid = require('uuid');

const {
  saltHashPassword,
  checkHashPassword,
} = require('../../utils/passwordHash');

function findUserByEmail(email) {
  return db.user.findUnique({
    where: {
      email,
    },
  });
}

function createUser(user) {
  const { fullname, email, password } = user;
  const hash_data = saltHashPassword(password);
  const passwordHash = hash_data.passwordHash;
  const salt = hash_data.salt;
  // make unique id
  const uid = uuid.v4();
  return db.user.create({
    data: {
      unique_id: uid,
      fullname: fullname,
      email: email.toLowerCase(),
      encrypted_password: passwordHash,
      salt: salt,
    },
  });
}

function findUserById(id) {
  return db.user.findUnique({
    where: {
      id: parseInt(id),
    },
    select: {
      id: true,
      fullname: true,
      profileImageUrl: true,
      email: true,
      createdAt: true,
    },
  });
}

const getUserProductsById = (id) => {};

module.exports = {
  findUserByEmail,
  createUser,
  findUserById,
  getUserProductsById,
};
