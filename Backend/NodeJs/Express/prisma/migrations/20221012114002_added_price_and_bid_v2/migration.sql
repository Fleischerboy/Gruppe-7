/*
  Warnings:

  - You are about to drop the column `Price` on the `bid` table. All the data in the column will be lost.
  - Added the required column `ownerId` to the `bid` table without a default value. This is not possible if the table is not empty.
  - Added the required column `price` to the `bid` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE `bid` DROP COLUMN `Price`,
    ADD COLUMN `ownerId` INTEGER NOT NULL,
    ADD COLUMN `price` DOUBLE NOT NULL;

-- AddForeignKey
ALTER TABLE `bid` ADD CONSTRAINT `bid_ownerId_fkey` FOREIGN KEY (`ownerId`) REFERENCES `user`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
