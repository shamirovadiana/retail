CREATE TABLE IF NOT EXISTS "categories"(
    "id"        BIGSERIAL       PRIMARY KEY NOT NULL,
    "name"      VARCHAR(100)    NOT NULL
);
CREATE TABLE IF NOT EXISTS "products"(
    "id"        BIGSERIAL       PRIMARY KEY NOT NULL,
    "sku"       VARCHAR(100)     NOT NULL ,
    "size"      VARCHAR(100)     NOT NULL ,
    "colour"    VARCHAR(100)     NOT NULL ,
    "description"   VARCHAR(200)    NULL ,
    "price"         DOUBLE PRECISION    NOT NULL ,
    "category_id"      BIGINT    NOT NULL,
    FOREIGN KEY("category_id") REFERENCES categories("id")
);
CREATE TABLE IF NOT EXISTS "orders"(
                                       "id"    BIGSERIAL       PRIMARY KEY NOT NULL,
                                       "created_at"    TIMESTAMP       NOT NULL,
                                       "updated_at"    TIMESTAMP       NOT NULL ,
                                       "items"         BIGINT[]        NOT NULL,
                                       "total_price"   DOUBLE PRECISION    NOT NULL
);
CREATE TABLE IF NOT EXISTS "order_items"(
    "id"        BIGSERIAL       PRIMARY KEY NOT NULL,
    "product_id"    BIGINT   NOT NULL ,
    "product_amount"    DOUBLE PRECISION    NOT NULL,
    "price"     DOUBLE PRECISION    NOT NULL,
    "order_id"  BIGINT    NOT NULL,
    FOREIGN KEY ("product_id") REFERENCES products("id")
);
CREATE TABLE IF NOT EXISTS "orders_items_relationship"(
    "order_item_id" BIGINT PRIMARY KEY ,
    "order_id" BIGINT,
    FOREIGN KEY ("order_id") REFERENCES orders("id")
);

ALTER TABLE "order_items" ADD CONSTRAINT fk_order_id FOREIGN KEY ("order_id") REFERENCES orders("id");
ALTER TABLE "orders_items_relationship" ADD CONSTRAINT fk_order_item_id FOREIGN KEY ("order_item_id") REFERENCES order_items("id");
ALTER TABLE "order_items" ALTER COLUMN "price" DROP NOT NULL;
DROP TABLE IF EXISTS "orders_items_relationship";
ALTER TABLE "order_items" DROP COLUMN "order_id";
ALTER TABLE "orders" ADD CONSTRAINT fk_items FOREIGN KEY ("items") REFERENCES  order_items("id") ON DELETE CASCADE;
CREATE TABLE IF NOT EXISTS "orders_items"(
  "order_item_id"   BIGINT  PRIMARY KEY ,
  "order_id"    BIGINT,
  FOREIGN KEY ("order_item_id") REFERENCES order_items("id"),
  FOREIGN KEY ("order_id")  REFERENCES orders("id")
);