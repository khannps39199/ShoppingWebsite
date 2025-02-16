use PS39199_Java5_ASMSQL;
INSERT INTO Categories (Name, Description)
VALUES 
    ('Electronics', 'Devices, gadgets, and other electronic products'),
    ('Flowers', 'A wide range of fresh and artificial flowers'),
    ('Clothing', 'Mens, womens, and kids clothing'),
    ('Watches', 'Luxury and casual watches for all tastes'),
    ('Furniture', 'Furniture for home and office'),
    ('Books', 'Books in various genres'),
    ('Toys', 'Toys for children of all ages'),
    ('Beauty', 'Cosmetics and beauty products'),
    ('Sports', 'Sports equipment and apparel'),
    ('Food', 'Groceries, snacks, and beverages');
	select * from Categories;
INSERT INTO Products (Name, Description, Price, Discount, Stock, Image, CategoryID)
VALUES
    ('Smartphone A', 'Latest smartphone with high-end specs', 599.99, 10, 100, 'https://example.com/smartphone_a.jpg', 1),
    ('Laptop B', 'Powerful laptop for work and play', 899.99, 5, 50, 'https://example.com/laptop_b.jpg', 1),
    ('Headphones C', 'Noise-canceling headphones', 129.99, 0, 150, 'https://example.com/headphones_c.jpg', 1),
    ('Smartwatch D', 'Fitness tracking and notifications', 199.99, 15, 75, 'https://example.com/smartwatch_d.jpg', 1),
    ('TV E', '4K Ultra HD Smart TV', 799.99, 10, 60, 'https://example.com/tv_e.jpg', 1),
    ('Camera F', 'DSLR camera with high-resolution images', 499.99, 20, 30, 'https://example.com/camera_f.jpg', 1),
    ('Tablet G', 'Lightweight tablet with powerful features', 349.99, 5, 120, 'https://example.com/tablet_g.jpg', 1),
    ('Bluetooth Speaker H', 'Portable speaker with great sound', 79.99, 0, 200, 'https://example.com/bluetooth_speaker_h.jpg', 1),
    ('Drone I', 'Aerial drone with HD camera', 499.99, 15, 40, 'https://example.com/drone_i.jpg', 1),
    ('Game Console J', 'Next-gen gaming console with exclusive games', 399.99, 5, 100, 'https://example.com/game_console_j.jpg', 1),
    ('Smartphone K', 'High-performance smartphone with camera', 699.99, 5, 90, 'https://example.com/smartphone_k.jpg', 1),
    ('Laptop L', 'Laptop for professionals and students', 799.99, 15, 65, 'https://example.com/laptop_l.jpg', 1),
    ('Camera M', 'Compact camera with high-definition capture', 399.99, 10, 85, 'https://example.com/camera_m.jpg', 1),
    ('Smartwatch N', 'Smartwatch with health monitoring features', 149.99, 0, 200, 'https://example.com/smartwatch_n.jpg', 1),
    ('TV O', 'LED TV with smart features', 499.99, 20, 70, 'https://example.com/tv_o.jpg', 1),
    ('Headphones P', 'Over-ear headphones with great bass', 89.99, 0, 150, 'https://example.com/headphones_p.jpg', 1),
    ('Game Console Q', 'Portable game console with latest games', 299.99, 10, 80, 'https://example.com/game_console_q.jpg', 1),
    ('Bluetooth Speaker R', 'Waterproof portable Bluetooth speaker', 59.99, 5, 200, 'https://example.com/bluetooth_speaker_r.jpg', 1),
    ('Smartphone S', 'Affordable smartphone with decent features', 299.99, 15, 130, 'https://example.com/smartphone_s.jpg', 1),
    ('Laptop T', 'Laptop for gaming and creative work', 1199.99, 10, 55, 'https://example.com/laptop_t.jpg', 1);

-- Flowers
INSERT INTO Products (Name, Description, Price, Discount, Stock, Image, CategoryID)
VALUES
    ('Tulip Bouquet A', 'A fresh bouquet of colorful tulips', 19.99, 5, 50, 'https://example.com/tulip_bouquet_a.jpg', 2),
    ('Rose Bouquet B', 'Romantic red rose bouquet', 29.99, 10, 75, 'https://example.com/rose_bouquet_b.jpg', 2),
    ('Orchid C', 'Elegant white orchid flower', 39.99, 0, 100, 'https://example.com/orchid_c.jpg', 2),
    ('Sunflower D', 'Bright and cheerful sunflower bouquet', 15.99, 0, 120, 'https://example.com/sunflower_d.jpg', 2),
    ('Lily E', 'Beautiful lily flowers', 24.99, 5, 60, 'https://example.com/lily_e.jpg', 2),
    ('Daisy F', 'Fresh daisies for any occasion', 19.99, 10, 80, 'https://example.com/daisy_f.jpg', 2),
    ('Tulip G', 'Colorful tulip arrangement', 21.99, 0, 110, 'https://example.com/tulip_g.jpg', 2),
    ('Carnation H', 'Long-lasting carnations', 18.99, 5, 90, 'https://example.com/carnation_h.jpg', 2),
    ('Chrysanthemum I', 'A bouquet of vibrant chrysanthemums', 22.99, 10, 95, 'https://example.com/chrysanthemum_i.jpg', 2),
    ('Peony J', 'Romantic peony bouquet', 34.99, 0, 130, 'https://example.com/peony_j.jpg', 2),
    ('Tulip K', 'Springtime tulips in full bloom', 16.99, 15, 110, 'https://example.com/tulip_k.jpg', 2),
    ('Lily L', 'White lilies for elegant occasions', 25.99, 5, 70, 'https://example.com/lily_l.jpg', 2),
    ('Rose M', 'Red roses for a special event', 26.99, 0, 60, 'https://example.com/rose_m.jpg', 2),
    ('Sunflower N', 'A bouquet of cheerful sunflowers', 19.99, 5, 50, 'https://example.com/sunflower_n.jpg', 2),
    ('Daisy O', 'A bundle of fresh daisies', 14.99, 10, 130, 'https://example.com/daisy_o.jpg', 2),
    ('Orchid P', 'White orchid in a decorative pot', 45.99, 0, 40, 'https://example.com/orchid_p.jpg', 2),
    ('Tulip Q', 'Exquisite tulip bouquet', 22.99, 10, 70, 'https://example.com/tulip_q.jpg', 2),
    ('Chrysanthemum R', 'Chrysanthemums in a mixed bouquet', 24.99, 0, 90, 'https://example.com/chrysanthemum_r.jpg', 2),
    ('Peony S', 'Luxurious peony flowers', 35.99, 15, 50, 'https://example.com/peony_s.jpg', 2);
-- Clothing
INSERT INTO Products (Name, Description, Price, Discount, Stock, Image, CategoryID)
VALUES
    ('T-shirt A', 'Comfortable cotton t-shirt', 14.99, 0, 150, 'https://example.com/tshirt_a.jpg', 3),
    ('Jeans B', 'Stylish and trendy denim jeans', 39.99, 10, 100, 'https://example.com/jeans_b.jpg', 3),
    ('Sweater C', 'Warm sweater for the winter season', 29.99, 5, 120, 'https://example.com/sweater_c.jpg', 3),
    ('Jacket D', 'Fashionable leather jacket', 79.99, 15, 50, 'https://example.com/jacket_d.jpg', 3),
    ('Dress E', 'Elegant evening dress', 59.99, 0, 80, 'https://example.com/dress_e.jpg', 3),
    ('Shirt F', 'Classic button-up shirt', 24.99, 5, 150, 'https://example.com/shirt_f.jpg', 3),
    ('Shoes G', 'Comfortable and stylish sneakers', 49.99, 10, 200, 'https://example.com/shoes_g.jpg', 3),
    ('Hat H', 'Casual cap for outdoor activities', 15.99, 0, 180, 'https://example.com/hat_h.jpg', 3),
    ('Scarf I', 'Warm knitted scarf', 19.99, 0, 100, 'https://example.com/scarf_i.jpg', 3),
    ('Boots J', 'Durable boots for winter weather', 89.99, 15, 50, 'https://example.com/boots_j.jpg', 3),
    ('T-shirt K', 'Graphic t-shirt with a cool design', 19.99, 5, 130, 'https://example.com/tshirt_k.jpg', 3),
    ('Shorts L', 'Comfortable shorts for casual wear', 22.99, 10, 90, 'https://example.com/shorts_l.jpg', 3),
    ('Sweater M', 'Soft sweater in multiple colors', 39.99, 0, 70, 'https://example.com/sweater_m.jpg', 3),
    ('Blouse N', 'Elegant blouse for work or dinner', 34.99, 0, 60, 'https://example.com/blouse_n.jpg', 3),
    ('Skirt O', 'Stylish skirt for various occasions', 29.99, 15, 50, 'https://example.com/skirt_o.jpg', 3),
    ('Pants P', 'Casual pants for everyday wear', 34.99, 10, 140, 'https://example.com/pants_p.jpg', 3),
    ('Shirt Q', 'Short-sleeve shirt with a modern cut', 19.99, 5, 110, 'https://example.com/shirt_q.jpg', 3),
    ('Sweater R', 'Soft cashmere sweater for colder weather', 99.99, 0, 40, 'https://example.com/sweater_r.jpg', 3),
    ('Jacket S', 'Stylish windbreaker for outdoor activities', 69.99, 10, 80, 'https://example.com/jacket_s.jpg', 3),
    ('T-shirt T', 'Casual cotton t-shirt with a graphic design', 17.99, 0, 150, 'https://example.com/tshirt_t.jpg', 3);

-- Watches
INSERT INTO Products (Name, Description, Price, Discount, Stock, Image, CategoryID)
VALUES
    ('Analog Watch A', 'Classic analog wristwatch', 49.99, 5, 200, 'https://example.com/analog_watch_a.jpg', 4),
    ('Digital Watch B', 'Digital watch with advanced features', 89.99, 10, 100, 'https://example.com/digital_watch_b.jpg', 4),
    ('Luxury Watch C', 'Luxury watch with gold details', 199.99, 20, 50, 'https://example.com/luxury_watch_c.jpg', 4),
    ('Sports Watch D', 'Durable sports watch for active people', 69.99, 0, 150, 'https://example.com/sports_watch_d.jpg', 4),
    ('Smartwatch E', 'Smartwatch with fitness and notification features', 149.99, 15, 80, 'https://example.com/smartwatch_e.jpg', 4),
    ('Leather Strap Watch F', 'Elegant leather strap wristwatch', 120.00, 5, 100, 'https://example.com/leather_strap_watch_f.jpg', 4),
    ('Automatic Watch G', 'Automatic movement wristwatch', 399.99, 0, 50, 'https://example.com/automatic_watch_g.jpg', 4),
    ('Fashion Watch H', 'Trendy fashion watch for all occasions', 39.99, 10, 120, 'https://example.com/fashion_watch_h.jpg', 4),
    ('Pocket Watch I', 'Vintage pocket watch with chain', 59.99, 5, 90, 'https://example.com/pocket_watch_i.jpg', 4),
    ('Casual Watch J', 'Casual watch for everyday wear', 29.99, 0, 200, 'https://example.com/casual_watch_j.jpg', 4),
    ('Chronograph Watch K', 'Chronograph watch with stopwatch function', 129.99, 15, 70, 'https://example.com/chronograph_watch_k.jpg', 4),
    ('Pilot Watch L', 'Pilot-style watch with large dial', 199.99, 0, 60, 'https://example.com/pilot_watch_l.jpg', 4),
    ('Digital Sport Watch M', 'Sporty digital watch with altimeter', 79.99, 10, 150, 'https://example.com/digital_sport_watch_m.jpg', 4),
    ('Dress Watch N', 'Sleek dress watch for formal events', 89.99, 0, 100, 'https://example.com/dress_watch_n.jpg', 4),
    ('Wooden Watch O', 'Eco-friendly wooden wristwatch', 59.99, 10, 80, 'https://example.com/wooden_watch_o.jpg', 4),
    ('Sunset Watch P', 'Stylish watch with sunset-inspired design', 49.99, 5, 110, 'https://example.com/sunset_watch_p.jpg', 4),
    ('Silver Watch Q', 'Silver metal watch for daily wear', 89.99, 0, 90, 'https://example.com/silver_watch_q.jpg', 4),
    ('Luxury Digital Watch R', 'Premium digital watch with advanced features', 249.99, 20, 40, 'https://example.com/luxury_digital_watch_r.jpg', 4),
    ('Sports Digital Watch S', 'Sporty watch with heart rate monitor', 99.99, 10, 120, 'https://example.com/sports_digital_watch_s.jpg', 4),
    ('Genuine Leather Watch T', 'Genuine leather strap with elegant design', 119.99, 5, 70, 'https://example.com/genuine_leather_watch_t.jpg', 4);

-- Furniture
INSERT INTO Products (Name, Description, Price, Discount, Stock, Image, CategoryID)
VALUES
    ('Sofa A', 'Comfortable three-seat sofa', 399.99, 10, 50, 'https://example.com/sofa_a.jpg', 5),
    ('Dining Table B', 'Elegant dining table with six chairs', 499.99, 5, 30, 'https://example.com/dining_table_b.jpg', 5),
    ('Bookshelf C', 'Wooden bookshelf for living room', 149.99, 15, 80, 'https://example.com/bookshelf_c.jpg', 5),
    ('Coffee Table D', 'Modern coffee table with storage', 199.99, 5, 70, 'https://example.com/coffee_table_d.jpg', 5),
    ('Armchair E', 'Comfortable armchair for reading', 249.99, 0, 60, 'https://example.com/armchair_e.jpg', 5),
    ('Bed Frame F', 'Sturdy bed frame with wooden finish', 399.99, 10, 50, 'https://example.com/bed_frame_f.jpg', 5),
    ('Wardrobe G', 'Spacious wardrobe with multiple shelves', 499.99, 15, 40, 'https://example.com/wardrobe_g.jpg', 5),
    ('TV Stand H', 'Sleek TV stand with drawers', 149.99, 0, 120, 'https://example.com/tv_stand_h.jpg', 5),
    ('Desk I', 'Modern desk for home office', 199.99, 10, 80, 'https://example.com/desk_i.jpg', 5),
    ('Chair J', 'Ergonomic office chair with adjustable height', 89.99, 5, 150, 'https://example.com/chair_j.jpg', 5),
    ('Sofa K', 'Sectional sofa with reclining features', 699.99, 5, 60, 'https://example.com/sofa_k.jpg', 5),
    ('Dining Chair L', 'Comfortable dining chair with cushioned seat', 89.99, 0, 100, 'https://example.com/dining_chair_l.jpg', 5),
    ('Nightstand M', 'Compact nightstand with drawer', 69.99, 15, 110, 'https://example.com/nightstand_m.jpg', 5),
    ('Shelf N', 'Wall-mounted shelf for books and decor', 59.99, 10, 130, 'https://example.com/shelf_n.jpg', 5),
    ('Office Chair O', 'Ergonomic office chair with lumbar support', 129.99, 0, 70, 'https://example.com/office_chair_o.jpg', 5),
    ('Kitchen Table P', 'Small kitchen table for cozy spaces', 149.99, 5, 80, 'https://example.com/kitchen_table_p.jpg', 5),
    ('Couch Q', 'Large couch with soft cushions', 799.99, 10, 40, 'https://example.com/couch_q.jpg', 5),
    ('Loveseat R', 'Comfortable loveseat for small spaces', 399.99, 0, 90, 'https://example.com/loveseat_r.jpg', 5),
    ('Bench S', 'Wooden bench for entryway or dining area', 79.99, 15, 120, 'https://example.com/bench_s.jpg', 5),
    ('Recliner T', 'Leather recliner for ultimate relaxation', 499.99, 5, 50, 'https://example.com/recliner_t.jpg', 5);

	INSERT INTO Users (Username, password_hash, Email, full_name, Phone, Address, Role, is_activated)
VALUES 
('admin', '123456hashed', 'admin@example.com', 'Admin User', '0123456789', '123 Admin Street', 'Admin', 1),
('john_doe', 'abcdef123hashed', 'john@example.com', 'John Doe', '0987654321', '456 User Lane', 'Customer', 1),
('jane_smith', 'xyz987hashed', 'jane@example.com', 'Jane Smith', '0912345678', '789 Customer Ave', 'Customer', 1),
('michael_nguyen', 'mnp456hashed', 'michael@example.com', 'Michael Nguyen', '0934567890', '567 Market St', 'Customer', 1),
('susan_lee', 'susan123hashed', 'susan@example.com', 'Susan Lee', '0976543210', '321 Park Blvd', 'Customer', 1),
('william_johnson', 'william123hashed', 'william.johnson@example.com', 'William Johnson', '0967123456', '23 Maple Street', 'Customer', 1),
('olivia_martin', 'olivia456hashed', 'olivia.martin@example.com', 'Olivia Martin', '0908123456', '88 Rose Avenue', 'Customer', 1),
('ethan_clark', 'ethan789hashed', 'ethan.clark@example.com', 'Ethan Clark', '0945236789', '12 Oak Road', 'Customer', 1),
('ava_robinson', 'ava111hashed', 'ava.robinson@example.com', 'Ava Robinson', '0987456321', '56 Sunflower St', 'Customer', 1),
('james_white', 'james222hashed', 'james.white@example.com', 'James White', '0923456789', '99 Lakeview Dr', 'Customer', 1),
('charlotte_adams', 'charlotte123hashed', 'charlotte.adams@example.com', 'Charlotte Adams', '0912345678', '14 Elm Street', 'Customer', 1),
('daniel_baker', 'daniel456hashed', 'daniel.baker@example.com', 'Daniel Baker', '0923456789', '22 Pine Avenue', 'Customer', 1),
('mia_wilson', 'mia789hashed', 'mia.wilson@example.com', 'Mia Wilson', '0934567890', '33 Cedar Road', 'Customer', 1),
('benjamin_moore', 'benjamin111hashed', 'benjamin.moore@example.com', 'Benjamin Moore', '0945678901', '44 Birch Lane', 'Customer', 1),
('sophia_taylor', 'sophia222hashed', 'sophia.taylor@example.com', 'Sophia Taylor', '0956789012', '55 Magnolia Blvd', 'Customer', 1),
('logan_anderson', 'logan333hashed', 'logan.anderson@example.com', 'Logan Anderson', '0967890123', '66 Oak Street', 'Customer', 1),
('harper_thomas', 'harper444hashed', 'harper.thomas@example.com', 'Harper Thomas', '0978901234', '77 Redwood Drive', 'Customer', 1),
('alexander_jackson', 'alexander555hashed', 'alexander.jackson@example.com', 'Alexander Jackson', '0989012345', '88 Cypress Ave', 'Customer', 1),
('amelia_harris', 'amelia666hashed', 'amelia.harris@example.com', 'Amelia Harris', '0990123456', '99 Cherry Lane', 'Customer', 1),
('elijah_martinez', 'elijah777hashed', 'elijah.martinez@example.com', 'Elijah Martinez', '0901234567', '100 Maple Drive', 'Customer', 1),
('ella_thompson', 'ella888hashed', 'ella.thompson@example.com', 'Ella Thompson', '0912345670', '111 Pine Grove', 'Customer', 1),
('matthew_garcia', 'matthew999hashed', 'matthew.garcia@example.com', 'Matthew Garcia', '0923456701', '121 Birchwood St', 'Customer', 1),
('scarlett_lopez', 'scarlett000hashed', 'scarlett.lopez@example.com', 'Scarlett Lopez', '0934567012', '131 Willow Road', 'Customer', 1),
('henry_lee', 'henry101hashed', 'henry.lee@example.com', 'Henry Lee', '0945670123', '141 Oak Park', 'Customer', 1),
('grace_hall', 'grace202hashed', 'grace.hall@example.com', 'Grace Hall', '0956781234', '151 Cedar Path', 'Customer', 1),
('jackson_allen', 'jackson303hashed', 'jackson.allen@example.com', 'Jackson Allen', '0967892345', '161 Redwood Terrace', 'Customer', 1),
('chloe_walker', 'chloe404hashed', 'chloe.walker@example.com', 'Chloe Walker', '0978903456', '171 Maple Grove', 'Customer', 1),
('sebastian_young', 'sebastian505hashed', 'sebastian.young@example.com', 'Sebastian Young', '0989014567', '181 Pine Crest', 'Customer', 1),
('lily_hernandez', 'lily606hashed', 'lily.hernandez@example.com', 'Lily Hernandez', '0990125678', '191 Birch Heights', 'Customer', 1),
('jack_carter', 'jack707hashed', 'jack.carter@example.com', 'Jack Carter', '0901236789', '201 Oak Haven', 'Customer', 1);

UPDATE Products 
SET Image = CONCAT(ProductID, '.png') 
WHERE ProductID BETWEEN 1 AND 50;
