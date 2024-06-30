CREATE TABLE if not exists pedidodb.pedidos (
         id bigint(20) NOT NULL AUTO_INCREMENT,
         data_hora datetime NOT NULL,
         status ENUM('REALIZADO','CANCELADO','PAGO','NAO_AUTORIZADO','CONFIRMADO','PRONTO','SAIU_PARA_ENTREGA','ENTREGUE') NOT NULL,
         PRIMARY KEY (id)
);
