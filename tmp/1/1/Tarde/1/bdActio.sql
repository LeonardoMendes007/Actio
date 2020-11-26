create database bdActio
go*/

drop database bdActio

use master

use bdActio
go

create table tbUsuario(
	idUsuario int primary key identity,
	nomeUsuario varchar(200) not null,
	sobrenomeUsuario varchar(200) not null,
	emailUsuario varchar(250) unique not null,
	senhaUsuario varchar(50) not null,
	fotoPerfil varchar(500) not null
)

create table tbProfessor(
	idProfessor int primary key 

--Herança
foreign key(idProfessor) references tbUsuario(idUsuario)
)


create table tbDisciplina(
	idDisciplina int primary key identity,
	nomeDisciplina varchar(250) unique not null,
	corDisciplina char(7) not null
)

create table tbTurma(
	idTurma int primary key identity,
	cursoTurma varchar(100) unique not null,
	semestreTurma int not null,
	periodoTurma varchar(50) not null,
	idDisciplina int foreign key references tbDisciplina(idDisciplina),
	idProfessor  int foreign key references tbProfessor(idProfessor),

	constraint chk_semestre
		check(semestreTurma > 0 and semestreTurma <= 6)
)


create table tbAluno(
	idAluno int primary key ,
	idTurma int foreign key references tbTurma (idTurma),

--Herança
foreign key(idAluno) references tbUsuario(idUsuario)

)


create table tbAtividade (
	idAtividade int primary key,
	nomeAtividade varchar(150) not null,
	descAtividade varchar(500) not null,
	dtEmissaoAtividade datetime not null,
	dtPublicacaoAtividade datetime not null,
	dtFechamentoAtividade datetime not null,
	arquivosAtividade varchar(500),
	idTurma int foreign key references tbTurma (idTurma),

	constraint chk_data
		check(dtEmissaoAtividade = getDate() and dtPublicacaoAtividade >= getdate() and dtFechamentoAtividade >= dtPublicacaoAtividade)

)

create table tbEntrega(
	idEntrega int primary key identity,
	arquivosEntrega varchar(500) not null,
	dtEntrega datetime not null,
	nota float not null,

	idAtividade int foreign key references tbAtividade(idAtividade),
	idAluno int foreign key references tbAluno(idAluno),

	constraint chk_nota
		check (nota >= 0 and nota <= 10)

)


insert into tbUsuario(nomeUsuario, sobrenomeUsuario, emailUsuario, senhaUsuario, fotoPerfil)
	values 
	('Alicia', 'Santos', 'alichia09@hotmail.com', '123456', 'https://img.ibxk.com.br/2017/06/22/22100428046161.jpg?w=1200&h=675&mode=crop&scale=both')
	,('Leonardo', 'Mendes', 'leoMendes@hotmail.com', '123456', 'https://img.ibxk.com.br/2017/06/22/22100428046161.jpg?w=1200&h=675&mode=crop&scale=both')
	,('Adriano', 'Galan', 'adrgalan@hotmail.com', '123456', 'https://img.ibxk.com.br/2017/06/22/22100428046161.jpg?w=1200&h=675&mode=crop&scale=both')
	,('Roberto', 'Mitsunari', 'robMit@hotmail.com', '123456', 'https://img.ibxk.com.br/2017/06/22/22100428046161.jpg?w=1200&h=675&mode=crop&scale=both')
	,('Gustavo', 'Narciso', 'souLindo@hotmail.com', '123456', 'https://img.ibxk.com.br/2017/06/22/22100428046161.jpg?w=1200&h=675&mode=crop&scale=both')
	,('Karine', 'Medeiros', 'kaMedeiros@hotmail.com', '123456', 'https://img.ibxk.com.br/2017/06/22/22100428046161.jpg?w=1200&h=675&mode=crop&scale=both')

	
select * from tbUsuario

insert into tbProfessor values 	 
	(3)
	,(4)
	,(6)
	
insert into tbDisciplina (nomeDisciplina, corDisciplina) 
	values ('Programação Orientada a Odio', '#FF9DBA')
		,('Lógica de Programação', '#9DC4FF')
		,('Banco de Dados', '#9DFFA1')
		,('Engenharia de Software III', '#FFDE9D')
		,('Sistemas da Informação', '#FFBA9D')
		
insert into tbTurma (cursoTurma, semestreTurma, periodoTurma, idDisciplina, idProfessor)
	values ('ADS', 2, 'Tarde', 1, 1)
		,('ADS', 2, 'Noite', 1, 2)
		,('ADS', 1, 'Tarde', 2, 3)
		,('ADS', 1, 'Noite', 2, 2)
		
	
insert into tbAluno values 
	(1, 1)
	,(2, 1)
	,(5, 2)


insert into tbAtividade (nomeAtividade, descAtividade, dtEmissaoAtividade, 
						dtPublicacaoAtividade, dtFechamentoAtividade, arquivosAtividade, idTurma)
values ('Lista de Exercícios', 'Entregar pelo site', getdate(), getdate(), '2019-11-25', '', 1)
	 ,('Entrega do Sistema', 'Entregar pelo site', getdate(), getdate(), '2019-11-28', '', 2)
	 ,('Lote 01', 'Entregar pelo email', getdate(), getdate(), '2019-11-25', '', 3)
	
	
create table tbEntrega(arquivosEntrega, dtEntrega, nota, idAtividade, idAluno) values 
 ('', '2019-11-20', 8.0, 1, 1),
		 ('', '2019-11-21', 10.0, 1, 2),
		('', '2019-11-25', 10.0, 2, 3)
		
	

	
	
	