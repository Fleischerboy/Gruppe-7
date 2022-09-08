/*
  Warnings:

  - You are about to alter the column `unique_id` on the `user` table. The data in that column could be lost. The data in that column will be cast from `VarChar(191)` to `VarChar(23)`.

*/
-- DropIndex
DROP INDEX `user_unique_id_key` ON `user`;

-- AlterTable
ALTER TABLE `user` MODIFY `unique_id` VARCHAR(23) NOT NULL;
