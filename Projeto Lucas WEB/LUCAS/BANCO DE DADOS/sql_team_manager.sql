CREATE TABLE IF NOT EXISTS cidade (
  codigo SERIAL,
  nome VARCHAR(45) NOT NULL,
  uf CHAR(2) NOT NULL,
  PRIMARY KEY (codigo))
;

CREATE TABLE IF NOT EXISTS jogador (
  codigo SERIAL,
  nome VARCHAR(45) NOT NULL,
  data_nascimento DATE NOT NULL,
  numero SMALLINT NOT NULL,
  telefone CHAR(14) NOT NULL,
  cidade INT NOT NULL,
  altura DECIMAL(3,2) NOT NULL,
  peso DECIMAL(4,1) NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT fk_jogadores_cidades1
    FOREIGN KEY (cidade)
    REFERENCES cidade (codigo))
;

CREATE UNIQUE INDEX numero_UNIQUE ON jogador (numero ASC);
CREATE INDEX fk_jogadores_cidades1_idx ON jogador (cidade ASC);

CREATE TABLE IF NOT EXISTS atributo (
  codigo SERIAL,
  descricao VARCHAR(45) NOT NULL,
  PRIMARY KEY (codigo))
;

CREATE TABLE IF NOT EXISTS atributos_dos_jogadores (
  codigo_atributo INT NOT NULL,
  codigo_jogador INT NOT NULL,
  pontuacao SMALLINT NOT NULL,
  PRIMARY KEY (codigo_atributo, codigo_jogador),
  CONSTRAINT fk_habilidades_has_jogadores_habilidades
    FOREIGN KEY (codigo_atributo)
    REFERENCES atributo (codigo),
  CONSTRAINT fk_habilidades_has_jogadores_jogadores1
    FOREIGN KEY (codigo_jogador)
    REFERENCES jogador (codigo));

CREATE INDEX fk_habilidades_has_jogadores_jogadores1_idx on atributos_dos_jogadores (codigo_jogador ASC);
CREATE INDEX fk_habilidades_has_jogadores_habilidades_idx on atributos_dos_jogadores (codigo_atributo ASC);

CREATE TABLE IF NOT EXISTS adversario (
  codigo SERIAL,
  nome VARCHAR(45) NOT NULL,
  telefone CHAR(14) NOT NULL,
  cidades_codigo INT NOT NULL,
  PRIMARY KEY (codigo));

CREATE TABLE IF NOT EXISTS competicao(
  codigo SERIAL,
  descricao VARCHAR(45) NOT NULL,
  tipo CHAR(1) NOT NULL,
  PRIMARY KEY (codigo));

CREATE TABLE IF NOT EXISTS jogo (
  codigo SERIAL,
  data DATE NOT NULL,
  hora TIME NOT NULL,
  lugar CHAR(1) NOT NULL,
  cidades_codigo INT NOT NULL,
  adversarios_codigo INT NOT NULL,
  competicoes_codigo INT NOT NULL,
  pontos_time INT,
  pontos_adversario INT,
  status CHAR(1) NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT fk_jogos_cidades1
    FOREIGN KEY (cidades_codigo)
    REFERENCES cidade (codigo),
  CONSTRAINT fk_jogos_adversarios1
    FOREIGN KEY (adversarios_codigo)
    REFERENCES adversario (codigo),
  CONSTRAINT fk_jogos_competicoes1
    FOREIGN KEY (competicoes_codigo)
    REFERENCES competicao (codigo));

CREATE INDEX fk_jogos_cidades1_idx on jogo (cidades_codigo ASC);
CREATE INDEX fk_jogos_adversarios1_idx on jogo (adversarios_codigo ASC);
CREATE INDEX fk_jogos_competicoes1_idx on jogo (competicoes_codigo ASC);

CREATE TABLE IF NOT EXISTS posicao (
  codigo SERIAL,
  descricao VARCHAR(45) NOT NULL,
  PRIMARY KEY (codigo))
;

CREATE TABLE IF NOT EXISTS jogadores_do_jogo (
  jogadores_codigo INT NOT NULL,
  jogos_codigo INT NOT NULL,
  titular BOOLEAN NOT NULL,
  PRIMARY KEY (jogadores_codigo, jogos_codigo),
  CONSTRAINT fk_jogadores_has_jogos_jogadores1
    FOREIGN KEY (jogadores_codigo)
    REFERENCES jogador (codigo),
  CONSTRAINT fk_jogadores_has_jogos_jogos1
    FOREIGN KEY (jogos_codigo)
    REFERENCES jogo (codigo))
;

CREATE INDEX fk_jogadores_has_jogos_jogos1_idx on jogadores_do_jogo (jogos_codigo ASC);
CREATE INDEX fk_jogadores_has_jogos_jogadores1_idx on jogadores_do_jogo (jogadores_codigo ASC);

CREATE TABLE IF NOT EXISTS usuarios (
  codigo SERIAL,
  login VARCHAR(20) NOT NULL,
  email VARCHAR(45) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  admin BOOLEAN NOT NULL,
  PRIMARY KEY (codigo))
;

CREATE TABLE IF NOT EXISTS posicoes_do_jogador (
  codigo_jogador INT NOT NULL,
  codigo_posicao INT NOT NULL,
  principal BOOLEAN NOT NULL,
  PRIMARY KEY (codigo_jogador, codigo_posicao),
  CONSTRAINT fk_jogadores_has_posicoes_jogadores1
    FOREIGN KEY (codigo_jogador)
    REFERENCES jogador (codigo),
  CONSTRAINT fk_jogadores_has_posicoes_posicoes1
    FOREIGN KEY (codigo_posicao)
    REFERENCES posicao (codigo))
;

CREATE INDEX fk_jogadores_has_posicoes_posicoes1_idx ON posicoes_do_jogador (codigo_posicao ASC);
CREATE INDEX fk_jogadores_has_posicoes_jogadores1_idx ON posicoes_do_jogador (codigo_jogador ASC);