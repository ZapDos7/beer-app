-- Add verification boolean
ALTER TABLE users
ADD verified TINYINT(1) DEFAULT '0';

-- Add tokens table
CREATE TABLE IF NOT EXISTS `token` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `token` BINARY(16) NOT NULL,
    `user_id` BIGINT NOT NULL,
    `expiry_date` DATETIME,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);