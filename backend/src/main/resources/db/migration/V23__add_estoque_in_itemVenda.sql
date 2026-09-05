ALTER TABLE item_venda
ADD COLUMN id_estoque BIGINT ,
ADD CONSTRAINT fk_estoque_item_venda
    FOREIGN KEY (id_estoque)
    REFERENCES estoque(id);