-- CreateTable
CREATE TABLE `bid` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `productId` INTEGER NOT NULL,
    `productOwnerId` INTEGER NOT NULL,
    `bidUserId` INTEGER NOT NULL,
    `bidAmount` DECIMAL(65, 30) NOT NULL,
    `isBidAccepted` BOOLEAN NOT NULL DEFAULT false,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- AddForeignKey
ALTER TABLE `bid` ADD CONSTRAINT `bid_productId_fkey` FOREIGN KEY (`productId`) REFERENCES `product`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE `bid` ADD CONSTRAINT `bid_bidUserId_fkey` FOREIGN KEY (`bidUserId`) REFERENCES `user`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
