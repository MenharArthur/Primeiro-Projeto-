package start;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TabelaDoBanco extends AbstractTableModel{
    
    
    private ArrayList<Object[]> linhas = new ArrayList<>();
    private String[] colunas;
    private PreparedStatement stmt;
    private String query;
    private ResultSet resultSet;
    private ResultSetMetaData rsmd;
    private BD bd = new BD();
    
    public TabelaDoBanco(String query){
    
        this.query = query;
        try {
            stmt = bd.conexao.prepareStatement(this.query);
            resultSet = stmt.executeQuery();
            loadData();
        } catch (SQLException ex) {
            System.out.println("problemas na conexão");
        }
    }
    
    public void loadData(){
        System.out.println("TabelaDoBanco[loadData()]");
        try{
            rsmd = resultSet.getMetaData();
            int numCols = rsmd.getColumnCount(); //retorna um número inteiro de colunas na tabela
            colunas = new String[numCols]; // adiciona o número de colunas no Array
            for(int i = 0; i < numCols; i++){colunas[i] = rsmd.getColumnName(i+1);} //adiciona o nome de cada coluna em um Array
            linhas.clear();
            while(resultSet.next()){
                Object l[] = new Object[numCols]; // adiciona o número de colunas ao Array de objetos
                for(int i=0; i < numCols; i++){l[i] = resultSet.getObject(i+1);} //retorna todos os objetos de cada coluna
                linhas.add(l);
            }
                fireTableDataChanged();
        } catch(Exception e){
        System.out.println("Problema no método");
        }
    }

    @Override
    public int getRowCount() {return linhas.size();}

    @Override
    public int getColumnCount() {return colunas.length;}

    @Override
    public Object getValueAt(int lin, int col) {
    
        try{
        Object l[] = linhas.get(lin);
        return l[col];
        } catch (Exception e){
        System.out.println("problemas no métodos adicionais");
        }
    return null;
    }
    
   
    
}
