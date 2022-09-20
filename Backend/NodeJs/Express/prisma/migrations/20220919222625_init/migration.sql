/*
  Warnings:

  - You are about to drop the `product_location` table. If the table is not empty, all the data it contains will be lost.

*/
-- DropForeignKey
ALTER TABLE `product_location` DROP FOREIGN KEY `product_location_productId_fkey`;

-- DropTable
DROP TABLE `product_location`;
