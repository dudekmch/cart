INSERT INTO cart.cart (id, created, shop_name) VALUES (1, CURRENT_TIMESTAMP(), 'shopName');
INSERT INTO cart.item (id, name, quantity, cart_id) VALUES (1, 'nameItemOne', 2, 1);
INSERT INTO cart.item (id, name, quantity, cart_id) VALUES (2, 'nameItemTwo', 3, 1);