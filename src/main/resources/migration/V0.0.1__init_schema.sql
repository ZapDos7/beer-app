DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
`id` binary(16) NOT NULL PRIMARY KEY,
`name` varchar(50) NOT NULL,
`email` varchar(500) NOT NULL,
`password` varchar(500) NOT NULL,
`role` ENUM('ADMIN', 'USER')
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `beer`;
CREATE TABLE `beer` (
`id` binary(16) NOT NULL PRIMARY KEY,
`name` varchar(50) NOT NULL,
`country_of_origin` varchar(60) NOT NULL,
`description` varchar(500) NOT NULL,
`type` ENUM ('ALE', 'LAGER', 'PORTER', 'STOUT', 'BLONDE_ALE', 'BROWN_ALE', 'PALE_ALE', 'IPA', 'WHEAT', 'PILSNER', 'SOUR_ALE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `rating`;
CREATE TABLE `rating` (
`user_id` binary(16) NOT NULL,
`beer_id` binary(16) NOT NULL,
`rating` SMALLINT,
`rating_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
FOREIGN KEY (`beer_id`) REFERENCES `beer` (`id`),
PRIMARY KEY (`user_id`, `beer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;