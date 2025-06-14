CREATE TABLE IF NOT EXISTS `rating` (
    `user_id` BIGINT NOT NULL,
    `beer_id` BIGINT NOT NULL,
    `rating` SMALLINT,
    `rating_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`beer_id`) REFERENCES `beer` (`id`),
    PRIMARY KEY (`user_id`, `beer_id`),
    CHECK (`rating`>=1 AND `rating`<=5)
);