const crypto = require('crypto');

const genRandomString = (length) => {
    return crypto.randomBytes(Math.ceil(length/2))
        .toString('hex') /* convert to hexa format */
        .slice(0, length) /* required number of characters */
}

const sha512 = (password, salt) => {
    const hash = crypto.createHash('sha512', salt);
    hash.update(password);
    const value = hash.digest('hex');
    return {
        salt: salt,
        passwordHash: value
    };
}

const saltHashPassword = (userPassword) => {
    const salt = genRandomString(16); // generate random string with 16 character
    return sha512(userPassword, salt);
}

const checkHashPassword = (user_password, salt) => {
    return sha512(user_password, salt);
}

module.exports = {
    saltHashPassword,
    checkHashPassword
  };