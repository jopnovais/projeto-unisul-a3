# Projeto de Cadastro Escolar em Java
Este é um projeto de desktop simples para um sistema de cadastro de alunos e professores, desenvolvido em Java com a biblioteca gráfica Swing. As informações são armazenadas em um banco de dados MySQL.

## Tecnologias Utilizadas
Java 11 (ou superior)

Java Swing para a interface gráfica (GUI)

JDBC (Java Database Connectivity) para comunicação com o banco de dados

MySQL Server 8.0 (ou compatível)

Pré-requisitos
Antes de começar, garanta que você tenha os seguintes softwares instalados em sua máquina:

JDK (Java Development Kit) - Versão 11 ou superior.

O banco de dados onde os dados serão armazenados.

Uma IDE Java, como IntelliJ IDEA ou Eclipse.

## Passo a Passo para Configuração e Execução
Siga as instruções abaixo para configurar o ambiente e rodar o projeto.

### 1. Configuração do Banco de Dados
   Primeiro, é necessário criar o banco de dados e as tabelas que a aplicação utilizará.

Abra seu cliente MySQL (MySQL Workbench, DBeaver, etc.) e execute o seguinte script SQL:

SQL

-- Cria o banco de dados se ele ainda não existir
CREATE DATABASE IF NOT EXISTS escola_db;

-- Seleciona o banco de dados para usar
USE escola_db;

-- Cria a tabela de alunos
CREATE TABLE IF NOT EXISTS alunos (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(255) NOT NULL,
idade INT,
matricula VARCHAR(50) NOT NULL UNIQUE
);

-- Cria a tabela de professores
CREATE TABLE IF NOT EXISTS professores (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(255) NOT NULL,
materia VARCHAR(100) NOT NULL
);
### 2. Instalação da Biblioteca (MySQL Connector/J)
   Este projeto precisa de uma biblioteca externa (um arquivo .jar) para que o Java possa se comunicar com o MySQL.

Baixe o Driver: Faça o download do MySQL Connector/J no site oficial:

https://dev.mysql.com/downloads/connector/j/

No menu "Select Operating System", escolha Platform Independent.

Baixe o arquivo ZIP e extraia-o.

Localize o Arquivo: Dentro da pasta extraída, você encontrará o arquivo que precisamos: mysql-connector-j-X.X.XX.jar. Guarde-o em um local de fácil acesso.

### 3. Configuração do Projeto na IDE
   Após baixar o código-fonte do projeto, abra-o na sua IDE e adicione a biblioteca que você acabou de baixar.

Para IntelliJ IDEA:
Com o projeto aberto, vá em File -> Project Structure... (ou Ctrl+Alt+Shift+S).

No painel esquerdo, selecione Modules.

Na aba Dependencies, clique no ícone + e escolha JARs or directories....

Navegue até o local onde você salvou o arquivo mysql-connector-j-X.X.XX.jar e selecione-o.

Clique em Apply e depois em OK.

Para Eclipse:
Clique com o botão direito no projeto no "Package Explorer".

Vá em Build Path -> Configure Build Path....

Na aba Libraries, clique em Add External JARs....

Navegue até o local onde você salvou o arquivo mysql-connector-j-X.X.XX.jar e selecione-o.

Clique em Apply and Close.

### 4. Configuração da Conexão
  A última etapa da configuração é garantir que a aplicação consiga acessar seu banco de dados.

Abra o arquivo: src/br/com/projetoescola/factory/ConnectionFactory.java.

Edite as variáveis USER e PASSWORD com o seu usuário e senha do MySQL.

Java

// ATENÇÃO: Configure com seus dados de acesso ao MySQL
private static final String URL = "jdbc:mysql://localhost:3306/escola_db?useTimezone=true&serverTimezone=UTC";
private static final String USER = "seu_usuario_aqui";      // Ex: "root"
private static final String PASSWORD = "sua_senha_aqui"; // Ex: "12345"
### 5. Como Rodar o Projeto
   Com tudo configurado, você pode executar a aplicação.

Navegue até o arquivo principal do projeto: src/br/com/projetoescola/main/Main.java.

Clique com o botão direito sobre o arquivo.

Selecione a opção Run 'Main.main()' (no IntelliJ) ou Run As -> Java Application (no Eclipse).

A janela do sistema de cadastro deverá aparecer na sua tela, pronta para uso.