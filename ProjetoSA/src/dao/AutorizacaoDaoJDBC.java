package dao;

import entidade.Autorizacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AutorizacaoDaoJDBC implements AutorizacaoDao {

    @Override
    public void salvar(Autorizacao autorizacao) {
        if (autorizacao.getId() == null) {
            try {
                Connection conn = Conexao.getConexao();
                String query = "INSERT INTO AUTORIZACAO (IDALUNO, IDCOORDENADOR, DTCRIACAO) VALUES (?, ?, now())";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, autorizacao.getAluno().getId());
                ps.setInt(2, autorizacao.getCoordenador().getId());
                ps.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                Connection conn = Conexao.getConexao();
                String query = "UPDATE AUTORIZACAO SET IDALUNO = ?, IDCOORDENADOR = ?, DTCRIACAO = ?, DTSAIDA = now() WHERE IDAUTORIZACAO = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, autorizacao.getIdAluno());
                ps.setInt(2, autorizacao.getIdCoordenador());
                long dateAsLong = autorizacao.getCriacao().getTime();
                ps.setDate(3, new java.sql.Date(dateAsLong));
                ps.setInt(4, autorizacao.getId());
                ps.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void excluir(Autorizacao autorizacao) {
        try {
            Connection conn = Conexao.getConexao();
            String query = "DELETE FROM AUTORIZACAO WHERE IDAUTORIZACAO = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, autorizacao.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Autorizacao> getAll() {
        List<Autorizacao> lista = new ArrayList();
        Connection conn = Conexao.getConexao();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM AUTORIZACAO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Autorizacao a = new Autorizacao();
                a.setId(rs.getInt("IDAUTORIZACAO"));
                a.setIdAluno(rs.getInt("IDALUNO"));
                a.setIdCoordenador(rs.getInt("IDCOORDENADOR"));
                a.setCriacao(rs.getDate("DTCRIACAO"));
                a.setSaida(rs.getTime("DTSAIDA"));
                lista.add(a);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
