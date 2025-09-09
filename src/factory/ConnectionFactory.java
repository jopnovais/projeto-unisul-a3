package br.com.projetoescola.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/escola_db?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root"; // Seu usuário do MySQL
    private static final String PASSWORD = "root"; // Sua senha do MySQL

    public static Connection createConnectionToMySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco de dados: ", e);
        }
    }
}