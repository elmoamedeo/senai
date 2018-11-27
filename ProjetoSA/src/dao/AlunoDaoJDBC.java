package dao;

import entidade.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlunoDaoJDBC implements AlunoDao {

    @Override
    public void salvar(Aluno aluno) {
        if (aluno.getId() == null) {
            try {
                Connection conn = Conexao.getConexao();
                String query = "INSERT INTO ALUNO (NOME, TURMA) VALUES (?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, aluno.getNome());
                // ALTERAÇÃO NECESSÁRIA JÁ QUE O CURSO DEIXOU DE SER UMA SIMPLES STRING E VIROU CLASSE/TABELA
                ps.setString(2, aluno.getTurma());
                ps.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                Connection conn = Conexao.getConexao();
                String query = "UPDATE ALUNO SET NOME = ?, TURMA = ? WHERE IDALUNO = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, aluno.getNome());
                // ALTERAÇÃO NECESSÁRIA JÁ QUE O CURSO DEIXOU DE SER UMA SIMPLES STRING E VIROU CLASSE/TABELA
                ps.setString(2, aluno.getTurma());
                ps.setInt(3, aluno.getId());
                ps.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void excluir(Aluno aluno) {
        try {
            Connection conn = Conexao.getConexao();
            String query = "DELETE FROM ALUNO WHERE IDALUNO = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, aluno.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Aluno> getAll() {
        List<Aluno> lista = new ArrayList();
        Connection conn = Conexao.getConexao();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ALUNO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getInt("IDALUNO"));
                a.setNome(rs.getString("NOME"));
                /* ALTERAÇÃO NECESSÁRIA JÁ QUE O CURSO DEIXOU DE SER UMA SIMPLES STRING E VIROU CLASSE/TABELA
                a.setTurma(rs.getString("TURMA"));
                */
                lista.add(a);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

}
