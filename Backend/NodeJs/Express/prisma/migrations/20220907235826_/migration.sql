/*
  Warnings:

  - The primary key for the `user` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - You are about to alter the column `id` on the `user` table. The data in that column could be lost. The data in that column will be cast from `VarChar(191)` to `Int`.
  - You are about to drop the `RefreshToken` table. If the table is not empty, all the data it contains will be lost.
  - A unique constraint covering the columns `[unique_id]` on the table `user` will be added. If there are existing duplicate values, this will fail.
  - Added the required column `fullName` to the `user` table without a default value. This is not possible if the table is not empty.
  - The required column `unique_id` was added to the `user` table with a prisma-level default value. This is not possible if the table is not empty. Please add this column as optional, then populate it before making it required.

*/
-- DropForeignKey
ALTER TABLE `RefreshToken` DROP FOREIGN KEY `RefreshToken_userId_fkey`;

-- DropIndex
DROP INDEX `user_id_key` ON `user`;

-- AlterTable
ALTER TABLE `user` DROP PRIMARY KEY,
    ADD COLUMN `fullName` VARCHAR(250) NOT NULL,
    ADD COLUMN `unique_id` VARCHAR(191) NOT NULL,
    MODIFY `id` INTEGER NOT NULL AUTO_INCREMENT,
    ADD PRIMARY KEY (`id`);

-- DropTable
DROP TABLE `RefreshToken`;

-- CreateIndex
CREATE UNIQUE INDEX `user_unique_id_key` ON `user`(`unique_id`);
