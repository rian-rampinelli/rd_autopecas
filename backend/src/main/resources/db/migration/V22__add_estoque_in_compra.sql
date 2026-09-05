ALTER TABLE compra
ADD COLUMN id_estoque BIGINT ,
ADD CONSTRAINT fk_estoque_compra
    FOREIGN KEY (id_estoque)
    REFERENCES estoque(id);