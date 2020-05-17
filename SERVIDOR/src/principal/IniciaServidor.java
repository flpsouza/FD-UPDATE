
package principal;

import bean.ProdutoBean;
import dao.ConexaoDB;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;

public class IniciaServidor {

    public static void main(String[] args) {
        try{
            System.out.println("Iniciando Servidor...");
            
            Connection conn = null;
            
            try{
                conn = ConexaoDB.retornaConexao();
                
                if(conn == null){
                     System.out.println("Erro de Conexao");
                }else{
                     System.out.println("Conectado ao banco de dados");
                }
            }catch(Exception edb){
                System.out.println("Erro de Banco: "+edb.toString());
            }
                    
                    
            LocateRegistry.createRegistry(1099);
            
            ProdutoBean objetoProduto = new ProdutoBean();
            Naming.rebind("rmi://192.168.0.120:1099/Produto", objetoProduto);     
        }
        catch(RemoteException re){
            System.out.println("Erro Remoto: "+re.toString());
        }
        catch(Exception e){
            System.out.println("Erro Local: "+e.toString());
        }
    }
    
}
