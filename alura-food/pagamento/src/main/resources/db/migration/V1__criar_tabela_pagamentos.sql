
CREATE TABLE IF NOT EXISTS pagamentodb.pagamento (
                          id bigint(20) NOT NULL AUTO_INCREMENT,
                          valor decimal(19,2) NOT NULL,
                          nome varchar(100) DEFAULT NULL,
                          numero varchar(19) DEFAULT NULL,
                          expiracao varchar(7) DEFAULT NULL,
                          cvc varchar(3) DEFAULT NULL,
                          status varchar(255) NOT NULL,
                          pagamento_from_id bigint(20) NOT NULL,
                          pedido_id bigint(20) NOT NULL,
                          PRIMARY KEY (id)
);
