CREATE TABLE "users" (
  "id" BIGSERIAL PRIMARY KEY,
  "nome" varchar(255) NOT NULL,
  "email" varchar(128) UNIQUE NOT NULL,
  "password" varchar(255) UNIQUE NOT NULL,
  "cpf" varchar(11) UNIQUE NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "endereco" (
  "id" BIGSERIAL PRIMARY KEY,
  "id_user" BIGINT NOT NULL,
  "nome" varchar(255) NOT NULL,
  "cidade" varchar(255) NOT NULL,
  "cep" varchar(64) NOT NULL,
  "rua" varchar(255) NOT NULL,
  "bairro" varchar(255) NOT NULL,
  "numero" BIGINT NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "funcionario" (
  "id" BIGSERIAL PRIMARY KEY,
  "id_user" BIGINT UNIQUE NOT NULL,
  "status" varchar(64) NOT NULL,
  "cargo" varchar(64) NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "cliente" (
  "id" BIGSERIAL PRIMARY KEY,
  "id_user" BIGINT UNIQUE NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "item" (
  "id" BIGSERIAL PRIMARY KEY,
  "codigo" BIGINT UNIQUE NOT NULL,
  "nome" varchar(255) NOT NULL,
  "descricao" varchar(528),
  "marca" varchar(255) NOT NULL,
  "peso" decimal(10,2) NOT NULL,
  "type_item" varchar(128) NOT NULL,
  "standard_price" decimal(10,2) NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "carro" (
  "id" BIGSERIAL PRIMARY KEY,
  "nome" varchar(255) NOT NULL,
  "marca" varchar(255) NOT NULL,
  "motor" varchar(255) NOT NULL,
  "versao" varchar(255) NOT NULL,
  "modelo" varchar(255) NOT NULL,
  "ano_fabricacao" date NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "carro_items" (
  "id_carro" BIGINT,
  "id_item" BIGINT,
  PRIMARY KEY ("id_carro", "id_item")
);

CREATE TABLE "venda" (
  "id" BIGSERIAL PRIMARY KEY,
  "id_cliente" BIGINT NOT NULL,
  "id_funcionario" BIGINT NOT NULL,
  "id_forma_pagamento" BIGINT NOT NULL,
  "status" varchar(128) NOT NULL,
  "total_value" decimal(10,2),
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "item_venda" (
  "id" BIGSERIAL PRIMARY KEY,
  "id_item" BIGINT NOT NULL,
  "id_venda" BIGINT NOT NULL,
  "item_value" decimal(10,2) NOT NULL,
  "quantidade" decimal(10,2) NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "forma_pagamento" (
  "id" BIGSERIAL PRIMARY KEY,
  "name" varchar(255) NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "estoque" (
  "id" BIGSERIAL PRIMARY KEY,
  "id_unidade" BIGINT NOT NULL,
  "lugar" varchar(255) NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "movimentacao_estoque" (
  "id" BIGSERIAL PRIMARY KEY,
  "id_item" BIGINT NOT NULL,
  "id_estoque" BIGINT NOT NULL,
  "type_movimentacao" varchar(255) NOT NULL,
  "quantidade" decimal(10,2) NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "estoque_item" (
  "id" BIGSERIAL PRIMARY KEY,
  "id_item" BIGINT NOT NULL,
  "id_estoque" BIGINT NOT NULL,
  "quantidade" decimal(10,2) NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "unidade" (
  "id" BIGSERIAL PRIMARY KEY,
  "endereco" varchar(255) NOT NULL,
  "status" varchar(255) NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "fornecedor" (
  "id" BIGSERIAL PRIMARY KEY,
  "cnpj" varchar(255) NOT NULL,
  "nome" varchar(255) NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "compra" (
  "id" BIGSERIAL PRIMARY KEY,
  "id_fornecedor" BIGINT NOT NULL,
  "id_funcionario" BIGINT NOT NULL,
  "id_forma_pagamento" BIGINT NOT NULL,
  "status" varchar(128) NOT NULL,
  "total_value" decimal(10,2),
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

CREATE TABLE "item_compra" (
  "id" BIGSERIAL PRIMARY KEY,
  "id_item" BIGINT NOT NULL,
  "id_compra" BIGINT NOT NULL,
  "item_value" decimal(10,2) NOT NULL,
  "quantidade" decimal(10,2) NOT NULL,
  "created_at" timestamp NOT NULL,
  "update_at" timestamp NOT NULL
);

ALTER TABLE "endereco"
ADD FOREIGN KEY ("id_user") REFERENCES "users" ("id");

ALTER TABLE "funcionario"
ADD FOREIGN KEY ("id_user") REFERENCES "users" ("id");

ALTER TABLE "cliente"
ADD FOREIGN KEY ("id_user") REFERENCES "users" ("id");

ALTER TABLE "venda"
ADD FOREIGN KEY ("id_forma_pagamento") REFERENCES "forma_pagamento" ("id");

ALTER TABLE "venda"
ADD FOREIGN KEY ("id_cliente") REFERENCES "cliente" ("id");

ALTER TABLE "venda"
ADD FOREIGN KEY ("id_funcionario") REFERENCES "funcionario" ("id");

ALTER TABLE "compra"
ADD FOREIGN KEY ("id_funcionario") REFERENCES "funcionario" ("id");

ALTER TABLE "compra"
ADD FOREIGN KEY ("id_fornecedor") REFERENCES "fornecedor" ("id");

ALTER TABLE "compra"
ADD FOREIGN KEY ("id_forma_pagamento") REFERENCES "forma_pagamento" ("id");

ALTER TABLE "carro_items"
ADD FOREIGN KEY ("id_carro") REFERENCES "carro" ("id");

ALTER TABLE "carro_items"
ADD FOREIGN KEY ("id_item") REFERENCES "item" ("id");

ALTER TABLE "estoque"
ADD FOREIGN KEY ("id_unidade") REFERENCES "unidade" ("id");

ALTER TABLE "movimentacao_estoque"
ADD FOREIGN KEY ("id_estoque") REFERENCES "estoque" ("id");

ALTER TABLE "estoque_item"
ADD FOREIGN KEY ("id_estoque") REFERENCES "estoque" ("id");

ALTER TABLE "movimentacao_estoque"
ADD FOREIGN KEY ("id_item") REFERENCES "item" ("id");

ALTER TABLE "estoque_item"
ADD FOREIGN KEY ("id_item") REFERENCES "item" ("id");

ALTER TABLE "item_venda"
ADD FOREIGN KEY ("id_item") REFERENCES "item" ("id");

ALTER TABLE "item_venda"
ADD FOREIGN KEY ("id_venda") REFERENCES "venda" ("id");

ALTER TABLE "item_compra"
ADD FOREIGN KEY ("id_item") REFERENCES "item" ("id");

ALTER TABLE "item_compra"
ADD FOREIGN KEY ("id_compra") REFERENCES "compra" ("id");
