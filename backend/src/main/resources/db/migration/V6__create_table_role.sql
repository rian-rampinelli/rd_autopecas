alter table role
rename to roles;

create table funcionario_roles(
    id_funcionario BIGINT NOT NULL,
    id_role BIGINT NOT NULL,
    PRIMARY KEY (id_funcionario, id_role),
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id),
    FOREIGN KEY (id_role) REFERENCES roles(id)
);