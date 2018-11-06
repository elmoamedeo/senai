package dao;

import entidade.Coordenador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoordenadorDaoJDBC implements CoordenadorDao {

    @Override
    public void salvar(Coordenador coordenador) {
        if (coordenador.getId() == null) {
            try {
                Connection conn = Conexao.getConexao();
                String query = "INSERT INTO COORDENADOR (NOME, CURSO, SENHA) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, coordenador.getNome());
                ps.setString(2, coordenador.getCurso());
                ps.setString(3, coordenador.getSenha());
                ps.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                Connection conn = Conexao.getConexao();
                String query = "UPDATE COORDENADOR SET NOME = ?, CURSO = ?, SENHA = ? WHERE IDCOORDENADOR = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, coordenador.getNome());
                ps.setString(2, coordenador.getCurso());
                ps.setString(3, coordenador.getSenha());
                ps.setInt(4, coordenador.getId());
                ps.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void excluir(Coordenador coordenador) {
        try {
            Connection conn = Conexao.getConexao();
            String query = "DELETE FROM COORDENADOR WHERE IDCOORDENADOR = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, coordenador.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Coordenador> getAll() {
        List<Coordenador> lista = new ArrayList();
        Connection conn = Conexao.getConexao();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM COORDENADOR");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Coordenador c = new Coordenador();
                c.setId(rs.getInt("IDCOORDENADOR"));
                c.setNome(rs.getString("NOME"));
                c.setCurso(rs.getString("CURSO"));
                c.setSenha(rs.getString("SENHA"));
                lista.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

}
