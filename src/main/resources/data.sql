INSERT INTO
  PRODUCTS (
    code,
    name,
    description,
    image,
    category,
    price,
    quantity,
    internal_reference,
    shell_id,
    inventory_status,
    rating,
    created_at,
    updated_at
  )
VALUES
  (
    'P001',
    'Product 1',
    'Description for product 1',
    'image1.jpg',
    'Category 1',
    19.99,
    50,
    'IR001',
    1,
    'INSTOCK',
    4.5,
    1633072800,
    1633072800
  ),
  (
    'P002',
    'Product 2',
    'Description for product 2',
    'image2.jpg',
    'Category 2',
    29.99,
    20,
    'IR002',
    2,
    'LOWSTOCK',
    4.0,
    1633072800,
    1633072800
  ),
  (
    'P003',
    'Product 3',
    'Description for product 3',
    'image3.jpg',
    'Category 3',
    39.99,
    0,
    'IR003',
    3,
    'OUTOFSTOCK',
    3.5,
    1633072800,
    1633072800
  );