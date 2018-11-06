package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
    private static Connection conn = null;
    
    private static final String STRCONEX = "jdbc:mysql://localhost:3306/sasenai";
    private static final String DRIVER = "org.gjt.mm.mysql.Driver";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    
    public static Connection getConexao() {
        if (conn == null) {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(STRCONEX, USUARIO, SENHA);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return conn;
    }
}
