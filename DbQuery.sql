CREATE DATABASE IF NOT EXISTS `topfood`;
USE `topfood`;
-- DROP DATABASE IF EXISTS `topfood`;

-- test
SELECT * FROM topfood.restaurant_images;

DELETE FROM food_images
WHERE food_id = 352;
SELECT * FROM topfood.food_images;

UPDATE your_table_name
SET is_vegetarian = 0;