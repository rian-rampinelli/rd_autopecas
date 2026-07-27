ALTER TABLE movimentacao_estoque
DROP COLUMN id_item,
DROP COLUMN id_estoque,
ADD COLUMN id_estoque_item BIGINT not null,
ADD CONSTRAINT fk_movimentacao_estoque_item
    FOREIGN KEY (id_estoque_item)
    REFERENCES estoque_item(id);