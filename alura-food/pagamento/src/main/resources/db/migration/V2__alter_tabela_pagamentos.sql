ALTER TABLE pagamentodb.pagamentos
    MODIFY COLUMN status ENUM('CRIADO', 'CONFIRMADO', 'CANCELADO', 'CONFIRMADO_SEM_INTEGRACAO') NOT NULL;
