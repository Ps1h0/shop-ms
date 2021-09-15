create table orders (
    id bigserial primary key,
    user_id bigint,
    price int,
    address varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table order_items (
    id bigserial primary key,
    order_id bigint references orders(id),
    product_id bigint,
    title varchar(255),
    quantity int,
    price_per_product int,
    price int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table carts (
    id UUID primary key,
    user_id bigint,
    price int
);

create table cart_items(
    id bigserial primary key,
    cart_id UUID references carts(id),
    product_id bigint,
    title varchar(255),
    quantity int,
    price_per_product int,
    price int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);