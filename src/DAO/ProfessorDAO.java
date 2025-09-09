package br.com.projetoescola.DAO;

import br.com.projetoescola.factory.ConnectionFactory;
import br.com.projetoescola.model.Professor;

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