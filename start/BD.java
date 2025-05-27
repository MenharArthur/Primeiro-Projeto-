package start;
import java.sql.*;
public class BD {
    public Connection conexao = null;
    private final String DRIVER = "";
    private final String BDNAME = "";
    private final String URL = "" + BDNAME;
    private final String LOGIN = "root";
    private final String SENHA = "";

public boolean getConnection() {
        
    try {
        
        Class.forName(DRIVER);
        conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
        System.out.println("Conectou");
        return true;
        
        } catch(ClassNotFoundException erro) {
            System.out.println("Driver não conectou " + erro.toString());
            return false;
        } catch (SQLException erro){
            System.out.println("Falha ao conectar" + erro.toString());
            return false;
        }
    }

  public void close(){
      
      try{
          conexao.close();
          System.out.println("Desconectou");
      } catch(SQLException erro){
      }
  }
}
