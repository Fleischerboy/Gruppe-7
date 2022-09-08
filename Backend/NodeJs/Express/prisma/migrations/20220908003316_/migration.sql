/*
  Warnings:

  - You are about to drop the column `password` on the `user` table. All the data in the column will be lost.
  - Added the required column `encrypted_password` to the `user` table without a default value. This is not possible if the table is not empty.
  - Added the required column `salt` to the `user` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE `user` DROP COLUMN `password`,
    ADD COLUMN `encrypted_password` VARCHAR(128) NOT NULL,
    ADD COLUMN `salt` VARCHAR(16) NOT NULL;
