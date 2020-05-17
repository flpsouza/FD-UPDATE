package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String USUARIO = "root"; 
    private static final String SENHA = "123456789";
    private static final String URL_BANCO = "jdbc:mysql://127.0.0.1:3306/crud?useTimezone=true&serverTimezone=UTC";
    
    public static Connection retornaConexao(){
        Connection conexao = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL_BANCO, USUARIO, SENHA );            
        }catch(SQLException e){
            System.err.println("Erro de Conexao:" + e.toString());
        } catch (ClassNotFoundException e) {            
            System.err.println("Classe Não Encontrada:" + e.toString());
        }
        
        return conexao;
    }
    
//    public static void printSQLException(SQLException ex) {
//        for (Throwable e: ex) {
//            if (e instanceof SQLException) {
//                e.printStackTrace(System.err);
//                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
//                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
//                System.err.println("Message: " + e.getMessage());
//                Throwable t = ex.getCause();
//                while (t != null) {
//                    System.out.println("Cause: " + t);
//                    t = t.getCause();
//                }
//            }
//        }
//    }
}
