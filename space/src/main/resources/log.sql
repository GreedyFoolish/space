CREATE SCHEMA `space` ;

use `space` ;

CREATE TABLE `space`.`user` (
    `id` INT NOT NULL,
    `name` VARCHAR(45) NULL,
    `code` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `space`.`user` (`id`, `name`, `code`) VALUES ('1', 'abc', '123');

