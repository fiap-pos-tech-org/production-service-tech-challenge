CREATE TABLE IF NOT EXISTS pedido (
  id bigint AUTO_INCREMENT,
  status enum ('PENDENTE_DE_PAGAMENTO','PAGO','RECEBIDO','EM_PREPARACAO','PRONTO','FINALIZADO','CANCELADO'),
  data timestamp NOT NULL,
  primary key (id)
);

CREATE TABLE IF NOT EXISTS item_pedido (
  id bigint AUTO_INCREMENT,
  pedido_id bigint NOT NULL,
  produto_nome varchar(30) NOT NULL,
  quantidade int NOT NULL,
  primary key (id),
  FOREIGN KEY (pedido_id) REFERENCES pedido (id)
);
