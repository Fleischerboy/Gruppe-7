import {
  dummyProductData,
  dummyUserData,
} from './../dummyData/dummyData';
// /prisma/seed.js
import { PrismaClient } from '@prisma/client';
const { createUser } = require('../users/users.services');
const { createProduct } = require('../products/products.service');

const prisma = new PrismaClient();

const createUsersWithProducts = async () => {};

// Seed funksjoner
async function main() {
  console.log(`Start seeding ...`);
  console.log(`Seeding finished.`);
}

main()
  .catch((e) => {
    console.error(e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
