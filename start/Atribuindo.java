package start;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Atribuindo {
    /*
        Atributos para o desenvolvimento dos métodos.
           ||      ||  conectar o banco de dados.
           ||      ||  instânciar a classe Atributos.
           ||      ||  chamar as classes necessárias para a execução do SCRIPT SQL.
        Três constantes estáticas do tipo byte para manipulações das operações de INCLUSAO, ALTERACAO e EXCLUSÃO.    
    */
    public Atributos atributos;
    public BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    public String men, sql;
   
    public static final byte INCLUSAO = 1;
    public static final byte ALTERACAO = 2;
    public static final byte EXCLUSAO = 3;
    
    public Atribuindo() {
        //Instânciando as classes do Banco de Dados e Atributos para a utilização na classe principal.
        bd = new BD();
        atributos = new Atributos();
    }
    
    
    
    public String atualizar(int operacao){
        /*
            Operação de Inclusão para capturar os dados da interface do usuário.
            Aqui é feita a persistência dos dados no Banco de Dados.
        */
        men = "Operação realizada com sucesso!";
        try {
            if(operacao == INCLUSAO){
         
                sql = "INSERT INTO clientes VALUES (?,?,?,?,?)";
                statement = bd.conexao.prepareStatement(sql);
                statement.setString(1, atributos.getNome());
                statement.setString(2, atributos.getData());
                statement.setString(3, atributos.getServico());
                statement.setString(4, atributos.getPreco());
                statement.setString(5, atributos.getId());
                statement.execute();
            } else if (operacao == ALTERACAO){
                sql = "UPDATE clientes SET nome = ?, calendario = ?, trabalho = ?,"
                        + "valor = ? WHERE nome = ?";
                statement = bd.conexao.prepareStatement(sql);
                statement.setString(1, atributos.getNome());
                statement.setString(2, atributos.getData());
                statement.setString(3, atributos.getServico());
                statement.setString(4, atributos.getPreco());
                statement.setString(5, atributos.getNome());
                statement.execute();
            } else if (operacao == EXCLUSAO){
                sql = " DELETE FROM clientes WHERE id = ?";
                statement = bd.conexao.prepareStatement(sql);
                statement.setString(1, atributos.getId());
                statement.execute();
            }else if(statement.executeUpdate() == 0){
                men = "Falha na operação";
             }
        }catch(SQLException erro) {
                men = "Falha no operação 14" + erro.toString();
        }
        return men;
    }
    
  
}