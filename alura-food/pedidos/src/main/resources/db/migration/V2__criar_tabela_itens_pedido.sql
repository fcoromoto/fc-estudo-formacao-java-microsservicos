CREATE TABLE if not exists pedidodb.item_pedido (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    descricao varchar(255) DEFAULT NULL,
    quantidade int(11) NOT NULL,
    pedido_id bigint(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (pedido_id) REFERENCES pedidodb.pedidos(id)
);
