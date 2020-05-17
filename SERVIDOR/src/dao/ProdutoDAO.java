package dao;

import bean.ProdutoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutoDAO {
    
    public static void adicionar(ProdutoBean produto){
        
        String sql = "INSERT INTO produto (descricao, preco, quantidade) VALUES (?, ?, ?)";
        
        try{
            Connection conexao = ConexaoDB.retornaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.execute();
        }catch(Exception e){
            System.err.println("Erro no Adicionar Produto: "+e.toString());
        }    
    }
    
    public static void excluir(int id){
        
        String sql = "DELETE FROM produto WHERE id = ?";
        
        try{
            Connection conexao = ConexaoDB.retornaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();            
        }catch(Exception e){
            System.err.println("Erro no Excluir Produto: "+e.toString());
        } 
    }
    
    public static ArrayList<ProdutoBean> listar(){
        
        ArrayList<ProdutoBean> produtos = new ArrayList<>();
        
        String sql = "SELECT * FROM produto";
        
        try{
            Connection conexao = ConexaoDB.retornaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet registro = stmt.executeQuery();
            
            while(registro.next()){               
                ProdutoBean temporario = new ProdutoBean();
                temporario.setId(registro.getInt("id"));
                temporario.setDescricao(registro.getString("descricao"));
                temporario.setPreco(registro.getDouble("preco"));
                temporario.setQuantidade(registro.getInt("quantidade"));               
                produtos.add(temporario);
            }
            
            return produtos;            
        
        }catch(Exception e){
            System.err.println("Erro no Listar Produto: "+e.toString());
        } 
        
        return null;
    }      
    
    public static void atualizar(ProdutoBean produto){
    
        String sql = "UPDATE produto SET descricao = ?, preco = ?, quantidade = ? WHERE id = ?"; 
    
        try{
            Connection conexao = ConexaoDB.retornaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getId());             
            
            int rowsUpdated = stmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                System.out.println("As alterações foram efetuadas com sucesso!");
            }
            
        }catch(Exception e){
            System.err.println("Erro ao Editar Produto: "+e.toString());
        } 
     }    
    
    
     public static ProdutoBean pegaProduto(int id) {
            ProdutoBean produto = null;
            
            String sql = "SELECT * FROM produto WHERE id = ?";
          
        try{
            Connection conexao = ConexaoDB.retornaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);   
            
            ResultSet registro = stmt.executeQuery();            
            
            while (registro.next()) {
                produto = new ProdutoBean();
                produto.setId(registro.getInt("id"));
                produto.setDescricao(registro.getString("descricao"));
                produto.setPreco(registro.getDouble("preco"));
                produto.setQuantidade(registro.getInt("quantidade"));
            }            
                         
        }catch(Exception e){
            System.err.println("Erro na consulta de Produto: "+e.toString());
        } 
        return produto;            
        
    }
    
    
}
