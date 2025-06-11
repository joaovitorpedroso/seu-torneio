
CREATE TABLE Posicao(
	idPosicao SERIAL PRIMARY KEY,
	nomePosicao VARCHAR(100) NOT NULL,
	descPosicao VARCHAR(100) NOT NULL
);

CREATE TABLE Jogador(
	idJogador SERIAL PRIMARY KEY,
	nomeJogador VARCHAR(100) NOT NULL,
	dtNascJogador DATE NOT NULL,
	idPosicao INT REFERENCES Posicao(idPosicao),
	statusJogador VARCHAR(50) NOT NULL,
	peDominante VARCHAR(50) NOT null
);


CREATE TABLE Tecnico(
	idTecnico SERIAL PRIMARY KEY,
	nomeTecnico VARCHAR(100) NOT NULL,
	dtNascTecnico DATE NOT NULL
);

CREATE TABLE Equipe(
	idEquipe SERIAL PRIMARY KEY,
	nomeEquipe VARCHAR(100) NOT NULL,
	fundacao DATE NOT NULL,
	presidente VARCHAR(100) NOT NULL
);


create TABLE Campeonato(
	idCampeonato SERIAL PRIMARY KEY,
	nomeCampeonato VARCHAR(100)
);

CREATE TABLE EdicaoCampeonato(
	idEdicao SERIAL PRIMARY KEY,
	nomeEdicao VARCHAR(100),
	ano INT,
	descricaoEdicao VARCHAR(100),
	dtInicio DATE NOT NULL,
	dtFim DATE,
	idCampeonato INT  REFERENCES Campeonato(idCampeonato)
);

create TABLE FaseCampeonato(
	idFaseCampeonato SERIAL PRIMARY KEY NOT NULL,
	idEdicao INT REFERENCES EdicaoCampeonato(idEdicao),
	nomeFase VARCHAR(100),
	ordem INT
);

create TABLE PontuacaoEquipe(
	idPontuacaoEquipe SERIAL PRIMARY KEY NOT NULL,
	idFaseCampeonato INT  REFERENCES FaseCampeonato(idFaseCampeonato),
	idEquipe INT  REFERENCES Equipe (idEquipe),
	vitorias INT,
	derrotas INT,
	empates INT,
	golsFeitos INT,
	golSofridos INT,
	sgGols INT,
	cartoesAmarelos INT,
	cartoesVermelhos INT,
	pontos INT
);

CREATE TABLE Partida(
	idPartida SERIAL PRIMARY KEY,
	dataInicio DATE,
	golsTotais INT,
	estadio VARCHAR,
	idFaseCampeonato INT  REFERENCES FaseCampeonato(idFaseCampeonato)
);

CREATE TABLE PartidaEquipe(
	idPartidaEquipe SERIAL PRIMARY KEY,
	gols INT,
	situacao VARCHAR(100),
	idEquipe INT  REFERENCES Equipe(idEquipe),
	idPartida INT  REFERENCES Partida(idPartida)
);


CREATE TABLE ContratoJogador(
	idContratoJogador SERIAL PRIMARY KEY,
	dtInicio DATE NOT NULL,
	dtFim DATE NOT NULL,
	idEquipe INT REFERENCES Equipe(idEquipe),
	idJogador INT REFERENCES Jogador(idJogador),
	numeroJogador INT,
	status VARCHAR(100) NOT NULL
);

create TABLE ContratoTecnico(
	idContratoTecnico SERIAL PRIMARY KEY,
	dtInicio DATE NOT NULL,
	dtFim DATE NOT NULL,
	idEquipe INT REFERENCES Equipe(idEquipe),
	idTecnico INT REFERENCES Tecnico(idTecnico),
	status VARCHAR(100) NOT NULL
);


INSERT INTO Posicao(nomePosicao,descPosicao) VALUES('Goleiro','GL');
INSERT INTO Posicao(nomePosicao,descPosicao) VALUES('Zagueiro','ZG');
INSERT INTO Posicao(nomePosicao,descPosicao) VALUES('Lateral Esquerdo','LE');
INSERT INTO Posicao(nomePosicao,descPosicao) VALUES('Lateral Direito','LD');
INSERT INTO Posicao(nomePosicao,descPosicao) VALUES('Volante','VOL');
INSERT INTO Posicao(nomePosicao,descPosicao) VALUES('Meia Armador','MC');
INSERT INTO Posicao(nomePosicao,descPosicao) VALUES('Meia Esquerda','ME');
INSERT INTO Posicao(nomePosicao,descPosicao) VALUES('Meia Direita','MD');
INSERT INTO Posicao(nomePosicao,descPosicao) VALUES('Ponta Direita','PD');
INSERT INTO Posicao(nomePosicao,descPosicao) VALUES('Ponta Esquerda','PE');
INSERT INTO Posicao(nomePosicao,descPosicao) VALUES('Segundo atacante','SA');
INSERT INTO Posicao(nomePosicao,descPosicao) VALUES('Centro-avante','CA');