package start;
import java.sql.*;
public class BD {
    public Connection conexao = null;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String BDNAME = "jenniffernailsdesigner";
    private final String URL = "jdbc:mysql://localhost:3306/" + BDNAME;
    private final String LOGIN = "root";
    private final String SENHA = "AR!@0921jj";

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