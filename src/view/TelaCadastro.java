package view;

import DAO.AlunoDAO;
import DAO.ProfessorDAO;
import model.Aluno;
import model.Professor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaCadastro extends JFrame {

    private final AlunoDAO alunoDAO;
    private final ProfessorDAO professorDAO;

    // --- Componentes da aba de Alunos ---
    private JTextField txtNomeAluno, txtIdadeAluno, txtMatriculaAluno;
    private JTable tabelaAlunos;
    private DefaultTableModel modeloTabelaAlunos;

    // --- Componentes da aba de Professores ---
    private JTextField txtNomeProfessor, txtMateriaProfessor;
    private JTable tabelaProfessores;
    private DefaultTableModel modeloTabelaProfessores;


    public TelaCadastro() {
        setTitle("Sistema de Cadastro Escolar");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        alunoDAO = new AlunoDAO();
        professorDAO = new ProfessorDAO();

        JTabbedPane abas = new JTabbedPane();

        JPanel painelAlunos = criarPainelAlunos();
        abas.addTab("Alunos", painelAlunos);

        JPanel painelProfessores = criarPainelProfessores();
        abas.addTab("Professores", painelProfessores);

        add(abas);

        atualizarTabelaAlunos();
        atualizarTabelaProfessores();
    }

    private JPanel criarPainelAlunos() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Cadastrar Novo Aluno"));

        painelFormulario.add(new JLabel("Nome:"));
        txtNomeAluno = new JTextField();
        painelFormulario.add(txtNomeAluno);

        painelFormulario.add(new JLabel("Idade:"));
        txtIdadeAluno = new JTextField();
        painelFormulario.add(txtIdadeAluno);

        painelFormulario.add(new JLabel("Matrícula:"));
        txtMatriculaAluno = new JTextField();
        painelFormulario.add(txtMatriculaAluno);

        JButton btnSalvarAluno = new JButton("Salvar Aluno");
        painelFormulario.add(new JLabel());
        painelFormulario.add(btnSalvarAluno);

        painel.add(painelFormulario, BorderLayout.NORTH);

        String[] colunas = {"ID", "Nome", "Idade", "Matrícula"};
        modeloTabelaAlunos = new DefaultTableModel(colunas, 0);
        tabelaAlunos = new JTable(modeloTabelaAlunos);
        JScrollPane scrollPane = new JScrollPane(tabelaAlunos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Alunos Cadastrados"));

        painel.add(scrollPane, BorderLayout.CENTER);

        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnEditarAluno = new JButton("Editar Selecionado");
        JButton btnExcluirAluno = new JButton("Excluir Selecionado");
        painelAcoes.add(btnEditarAluno);
        painelAcoes.add(btnExcluirAluno);
        painel.add(painelAcoes, BorderLayout.SOUTH);

        btnSalvarAluno.addActionListener(e -> salvarAluno());
        btnEditarAluno.addActionListener(e -> editarAluno());
        btnExcluirAluno.addActionListener(e -> excluirAluno());
        return painel;
    }

    private JPanel criarPainelProfessores() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Cadastrar Novo Professor"));

        painelFormulario.add(new JLabel("Nome:"));
        txtNomeProfessor = new JTextField();
        painelFormulario.add(txtNomeProfessor);

        painelFormulario.add(new JLabel("Matéria:"));
        txtMateriaProfessor = new JTextField();
        painelFormulario.add(txtMateriaProfessor);

        JButton btnSalvarProfessor = new JButton("Salvar Professor");
        painelFormulario.add(new JLabel());
        painelFormulario.add(btnSalvarProfessor);

        painel.add(painelFormulario, BorderLayout.NORTH);

        String[] colunas = {"ID", "Nome", "Matéria"};
        modeloTabelaProfessores = new DefaultTableModel(colunas, 0);
        tabelaProfessores = new JTable(modeloTabelaProfessores);
        JScrollPane scrollPane = new JScrollPane(tabelaProfessores);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Professores Cadastrados"));

        painel.add(scrollPane, BorderLayout.CENTER);

        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnEditarProfessor = new JButton("Editar Selecionado");
        JButton btnExcluirProfessor = new JButton("Excluir Selecionado");
        painelAcoes.add(btnEditarProfessor);
        painelAcoes.add(btnExcluirProfessor);
        painel.add(painelAcoes, BorderLayout.SOUTH);

        btnSalvarProfessor.addActionListener(e -> salvarProfessor());
        btnEditarProfessor.addActionListener(e -> editarProfessor());
        btnExcluirProfessor.addActionListener(e -> excluirProfessor());
        return painel;
    }

    private void salvarAluno() {
        String nome = txtNomeAluno.getText();
        String idadeStr = txtIdadeAluno.getText();
        String matricula = txtMatriculaAluno.getText();

        if (nome.isEmpty() || idadeStr.isEmpty() || matricula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos de aluno devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idade = Integer.parseInt(idadeStr);
            Aluno aluno = new Aluno();
            aluno.setNome(nome);
            aluno.setIdade(idade);
            aluno.setMatricula(matricula);
            alunoDAO.save(aluno);
            JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCamposAluno();
            atualizarTabelaAlunos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "A idade deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarProfessor() {
        String nome = txtNomeProfessor.getText();
        String materia = txtMateriaProfessor.getText();

        if (nome.isEmpty() || materia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos de professor devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Professor professor = new Professor();
        professor.setNome(nome);
        professor.setMateria(materia);
        professorDAO.save(professor);

        JOptionPane.showMessageDialog(this, "Professor salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        limparCamposProfessor();
        atualizarTabelaProfessores();
    }

    private void editarAluno() {
        int selectedRow = tabelaAlunos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modeloTabelaAlunos.getValueAt(selectedRow, 0);
        String nomeAtual = (String) modeloTabelaAlunos.getValueAt(selectedRow, 1);
        int idadeAtual = (int) modeloTabelaAlunos.getValueAt(selectedRow, 2);
        String matriculaAtual = (String) modeloTabelaAlunos.getValueAt(selectedRow, 3);

        String nome = JOptionPane.showInputDialog(this, "Nome:", nomeAtual);
        if (nome == null) return;
        String idadeStr = JOptionPane.showInputDialog(this, "Idade:", String.valueOf(idadeAtual));
        if (idadeStr == null) return;
        String matricula = JOptionPane.showInputDialog(this, "Matrícula:", matriculaAtual);
        if (matricula == null) return;

        try {
            int idade = Integer.parseInt(idadeStr);
            Aluno aluno = new Aluno();
            aluno.setId(id);
            aluno.setNome(nome);
            aluno.setIdade(idade);
            aluno.setMatricula(matricula);
            alunoDAO.update(aluno);
            atualizarTabelaAlunos();
            JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "A idade deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirAluno() {
        int selectedRow = tabelaAlunos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o aluno selecionado?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        int id = (int) modeloTabelaAlunos.getValueAt(selectedRow, 0);
        alunoDAO.deleteById(id);
        atualizarTabelaAlunos();
        JOptionPane.showMessageDialog(this, "Aluno excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarProfessor() {
        int selectedRow = tabelaProfessores.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um professor na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) modeloTabelaProfessores.getValueAt(selectedRow, 0);
        String nomeAtual = (String) modeloTabelaProfessores.getValueAt(selectedRow, 1);
        String materiaAtual = (String) modeloTabelaProfessores.getValueAt(selectedRow, 2);

        String nome = JOptionPane.showInputDialog(this, "Nome:", nomeAtual);
        if (nome == null) return;
        String materia = JOptionPane.showInputDialog(this, "Matéria:", materiaAtual);
        if (materia == null) return;

        Professor professor = new Professor();
        professor.setId(id);
        professor.setNome(nome);
        professor.setMateria(materia);
        professorDAO.update(professor);
        atualizarTabelaProfessores();
        JOptionPane.showMessageDialog(this, "Professor atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void excluirProfessor() {
        int selectedRow = tabelaProfessores.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um professor na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o professor selecionado?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        int id = (int) modeloTabelaProfessores.getValueAt(selectedRow, 0);
        professorDAO.deleteById(id);
        atualizarTabelaProfessores();
        JOptionPane.showMessageDialog(this, "Professor excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void atualizarTabelaAlunos() {
        modeloTabelaAlunos.setRowCount(0);
        List<Aluno> alunos = alunoDAO.getAlunos();
        for (Aluno a : alunos) {
            modeloTabelaAlunos.addRow(new Object[]{a.getId(), a.getNome(), a.getIdade(), a.getMatricula()});
        }
    }

    private void atualizarTabelaProfessores() {
        modeloTabelaProfessores.setRowCount(0);
        List<Professor> professores = professorDAO.getProfessores();
        for (Professor p : professores) {
            modeloTabelaProfessores.addRow(new Object[]{p.getId(), p.getNome(), p.getMateria()});
        }
    }

    private void limparCamposAluno() {
        txtNomeAluno.setText("");
        txtIdadeAluno.setText("");
        txtMatriculaAluno.setText("");
    }

    private void limparCamposProfessor() {
        txtNomeProfessor.setText("");
        txtMateriaProfessor.setText("");
    }
}