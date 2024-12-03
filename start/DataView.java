package start;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class DataView extends JFrame{
   
    JTable tabelabanco;
    JButton executar, voltar;
    JScrollPane rolamento;
    PreparedStatement statement;
    ResultSet resultSet;
    String sql;
    BD bd ;
    
    public DataView(){
    
        inicializarComponentes();
        definirEventos();
   
    }
    public void inicializarComponentes(){
    
        setLayout(null);
        setTitle("Agenda de Clientes");
        setBounds(200, 200, 600, 500);
        setResizable(false);
        executar = new JButton("Consulta");
        executar.setBounds(170, 70, 100, 25);
        voltar = new JButton("voltar");
        voltar.setBounds(50, 70, 100, 25);
        rolamento = new JScrollPane();
        rolamento.setBounds(50, 100, 500, 300);
        add(voltar);
        add(executar);
        add(rolamento);
        bd = new BD();
        
    }
    
    public void definirEventos(){
    
        executar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try{
                    
                    if(!bd.getConnection()){
                    JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado");
                    System.exit(0);
                    }
                    sql = "SELECT * FROM clientes";
                    statement = bd.conexao.prepareStatement(sql);
                    resultSet = statement.executeQuery();
                    DefaultTableModel tableModel = new DefaultTableModel(
                    new String[]{}, 0){
                        public boolean isCellEditable(int row, int col){
                            if(col == 0){
                                return false;
                            }
                            if(col == 1){
                                return false;
                            }
                            if(col == 2){
                                return false;
                            }
                            if(col == 3){
                                return false;
                            }
                            if(col == 4){
                                return false;
                            }
                            return true;
                        }
                    }; 
                    
                    int qtdeColunas = resultSet.getMetaData().getColumnCount();
                    for(int indice = 1; indice <= qtdeColunas; indice++){
                        tableModel.addColumn(resultSet.getMetaData().getColumnName(indice));
                    }
                    
                    tabelabanco = new JTable(tableModel);
                    DefaultTableModel dtm = (DefaultTableModel) tabelabanco.getModel();
                    
                    while(resultSet.next()){
                    
                        try{
                            
                            String[] dados = new String[qtdeColunas];
                            for (int i = 1; i <= qtdeColunas; i++){
                                dados[i - 1] = resultSet.getString(i);
                                System.out.println(resultSet.getString(i));
                            }
                            dtm.addRow(dados);
                            System.out.println();
                        } catch(SQLException erro){
                        }
                        rolamento.setViewportView(tabelabanco);
                    }
                    resultSet.close();
                    statement.close();
                    bd.close();
                    
                } catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Comando inválido");
                }
                
            }
        });
        
        voltar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                NailsDesigner.voltar();
                
            }
        });
    
    }
    
    public static void abrir() {
    
        JFrame frame = new DataView();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
   
}
