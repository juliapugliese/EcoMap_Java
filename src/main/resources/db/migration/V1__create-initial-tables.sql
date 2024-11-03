CREATE TABLE T_USUARIOS (
    ID_USUARIO INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NOME VARCHAR2(100) NOT NULL,
    CEP VARCHAR2(10) NOT NULL,
    EMAIL VARCHAR2(255) UNIQUE NOT NULL,
    SENHA VARCHAR2(100) NOT NULL,
    PAPEL VARCHAR2(50) DEFAULT 'USER'
);


CREATE TABLE T_DRONES (
    ID_DRONE INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    MODELO VARCHAR2(150) NOT NULL,
    STATUS VARCHAR2(150) DEFAULT 'ATIVO' CHECK (STATUS IN ('ATIVO', 'INATIVO', 'EM MANUTENÇÃO')),
    DATA_AQUISICAO DATE
);

CREATE TABLE T_COLETAS (
    ID_COLETA INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    DATA_COLETA DATE NOT NULL,
    QUANTIDADE_RESIDUO DECIMAL(10,2)
);


CREATE TABLE T_SOLICITACOES (
    ID_SOLICITACAO INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    DESCRICAO VARCHAR2(250) NOT NULL,
    DATA_SOLICITACAO DATE DEFAULT SYSDATE,
    STATUS VARCHAR2(150) DEFAULT 'PENDENTE' CHECK (STATUS IN ('PENDENTE', 'EM ANDAMENTO', 'CONCLUÍDA')),
    ID_DENUNCIANTE INTEGER,
    FOREIGN KEY (ID_DENUNCIANTE) REFERENCES T_USUARIOS(ID_USUARIO)
);

CREATE TABLE T_AREA_MAPEADA (
    ID_AREA INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NOME_AREA VARCHAR(250) NOT NULL,
    LATITUDE DECIMAL(9,6) NOT NULL,
    LONGITUDE DECIMAL(9,6) NOT NULL,
    ID_DRONE INT NOT NULL,
    ID_COLETA INT NOT NULL,
    FOREIGN KEY (ID_DRONE) REFERENCES T_DRONES(ID_DRONE),
    FOREIGN KEY (ID_COLETA) REFERENCES T_COLETAS(ID_COLETA)
);


CREATE TABLE T_RESIDUOS (
    ID_RESIDUO INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    TIPO_RESIDUO VARCHAR(150) NOT NULL,
    DESCRICAO VARCHAR(150) NOT NULL,
    QUANTIDADE DECIMAL(10,2),
    ID_AREA_MAPEADA INT NOT NULL,
    FOREIGN KEY (ID_AREA_MAPEADA) REFERENCES T_AREA_MAPEADA(ID_AREA)
);


CREATE TABLE T_AREA_SOLICITACAO (
    ID_SOLICITACAO INT NOT NULL,
    ID_AREA INT NOT NULL,
    PRIMARY KEY (ID_SOLICITACAO, ID_AREA),
    FOREIGN KEY (ID_SOLICITACAO) REFERENCES T_SOLICITACOES(ID_SOLICITACAO),
    FOREIGN KEY (ID_AREA) REFERENCES T_AREA_MAPEADA(ID_AREA)
);




