package DAO;

import factory.ConnectionFactory;
import model.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public void save(Professor professor) {
        String sql = "INSERT INTO professores(nome, materia) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, professor.getNome());
            pstm.setString(2, professor.getMateria());
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Professor professor) {
        String sql = "UPDATE professores SET nome = ?, materia = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, professor.getNome());
            pstm.setString(2, professor.getMateria());
            pstm.setInt(3, professor.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM professores WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Professor> getProfessores() {
        String sql = "SELECT * FROM professores";
        List<Professor> professores = new ArrayList<>();
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rset = pstm.executeQuery()) {
            while (rset.next()) {
                Professor professor = new Professor();
                professor.setId(rset.getInt("id"));
                professor.setNome(rset.getString("nome"));
                professor.setMateria(rset.getString("materia"));
                professores.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professores;
    }
}