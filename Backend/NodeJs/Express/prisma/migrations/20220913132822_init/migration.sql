/*
  Warnings:

  - Added the required column `address` to the `product` table without a default value. This is not possible if the table is not empty.
  - Added the required column `imageUrl` to the `product` table without a default value. This is not possible if the table is not empty.
  - Added the required column `title` to the `product` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE `product` ADD COLUMN `address` VARCHAR(100) NOT NULL,
    ADD COLUMN `imageUrl` VARCHAR(2080) NOT NULL,
    ADD COLUMN `title` VARCHAR(48) NOT NULL;

-- CreateTable
CREATE TABLE `product_location` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `productId` INTEGER NOT NULL,
    `latitud` DECIMAL(65, 30) NOT NULL,
    `longitude` DECIMAL(65, 30) NOT NULL,

    UNIQUE INDEX `product_location_productId_key`(`productId`),
    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- AddForeignKey
ALTER TABLE `product_location` ADD CONSTRAINT `product_location_productId_fkey` FOREIGN KEY (`productId`) REFERENCES `product`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
