-- CreateTable
CREATE TABLE `bid` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `Price` DOUBLE NOT NULL,
    `productId` INTEGER NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- AddForeignKey
ALTER TABLE `bid` ADD CONSTRAINT `bid_productId_fkey` FOREIGN KEY (`productId`) REFERENCES `product`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE;
