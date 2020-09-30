CREATE DATABASE call_chamados;

create SEQUENCE chamado_incre increment 1 minvalue 1 maxvalue 9223372036854775807 start 1000;
CREATE TYPE tipoUsuario AS ENUM('CLIENTE', 'FUNCIONARIO');

create table usuario (

	id SERIAL PRIMARY KEY,
	nome varchar(150),
	tipo tipoUsuario NOT NULL
	
);

DROP TABLE chamado;

create table chamado(

	id INTEGER PRIMARY KEY,
	titulo varchar(200) NOT NULL,
	descricao text NOT NULL,
	cliente_id INTEGER
		REFERENCES usuario(id) NOT NULL,
	funcionario_id INTEGER
		REFERENCES usuario(id),
	observacao text,
	data DATE NOT NULL,
	status varchar(50) NOT NULL
	
);

alter table chamado alter column id set default nextval('chamado_incre' :: regclass);


INSERT INTO usuario(nome, tipo) VALUES
('Rafael', 'FUNCIONARIO'),
('João', 'CLIENTE'),
('Micael', 'CLIENTE'),
('Maria', 'FUNCIONARIO');

INSERT INTO chamado(titulo, descricao, cliente_id, data, status)
VALUES ('Problema com NotBook', 'Erro xyzKT', 1, CURRENT_DATE, 'PENDENTE'),
	   ('Windows Travando', 'Ae pessoal, recebi isso aqui por e-mail a um tempo atrás, então já vou 
		adiantando que é coisa velha e o texto é gigante, se não quiser não leia e espere um 
		resumo ou comentários do pessoal. Não tem muita graça mas to postando pois alguém pode
		gostar e como o boteco tá meio parado mesmo, aqui vai o texto:
		Meu nome é Afonso Soares de Melo, e resolvi contar algo que se passou comigo: 
		Estava sentado no meu escritório quando lembrei de uma chamada telefônica que tinha que fazer.
		Encontrei o número e disquei.', 1, CURRENT_DATE, 'PENDENTE'),
	   ('Problema com C A V A L O', 'Erro KZ', 1, CURRENT_DATE, 'FINALIZADO');









