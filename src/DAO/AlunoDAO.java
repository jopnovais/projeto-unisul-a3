package DAO;

import factory.ConnectionFactory;
import model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void save(Aluno aluno) {
        String sql = "INSERT INTO alunos(nome, idade, matricula) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, aluno.getNome());
            pstm.setInt(2, aluno.getIdade());
            pstm.setString(3, aluno.getMatricula());
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, idade = ?, matricula = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, aluno.getNome());
            pstm.setInt(2, aluno.getIdade());
            pstm.setString(3, aluno.getMatricula());
            pstm.setInt(4, aluno.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> getAlunos() {
        String sql = "SELECT * FROM alunos";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rset = pstm.executeQuery()) {
            while (rset.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rset.getInt("id"));
                aluno.setNome(rset.getString("nome"));
                aluno.setIdade(rset.getInt("idade"));
                aluno.setMatricula(rset.getString("matricula"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }
}