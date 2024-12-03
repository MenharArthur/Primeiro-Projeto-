package start;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class NailsDesigner extends JFrame {
    /*
    Criando atributos de caixa de texto , atributo de lebel , botões , painel e da classe Atribuindo.
    */
     JLabel cliente, dia, servico, valor, id;
     JPanel tabelaAdicao, mostrartabela, tabeladobanco;
     JButton adicionar, mostrar, atualizar, excluir;
     JTable tabelabanco;
     JScrollPane rolamento;
     private static NailsDesigner frame;
     static JTextField tfCliente, tfDia, tfServico, tfvalor, tfvalornovo, tftexto,tfId;
     private Atribuindo atribuindo;
     PreparedStatement statement = null;
     ResultSet resultSet;
     TabelaDoBanco tm;
     BD bd = new BD() ;
     /*
     Método main para iniciar todas as classes e métodos implementados e construidos. 
     */
    
     
    public NailsDesigner(){ //Não colocar o método construtor como void
        
        inicializarComponentes();
        definirEventos();
    }
    
    public void inicializarComponentes(){
    
        setLayout(null);
        setTitle("Jenniffer Nails Designer");
        setBounds(0, 0, 1000, 380);
        cliente = new JLabel("Cliente:");
        cliente.setBounds(100,40, 100, 25);
        dia = new JLabel("Data:");
        dia.setBounds(425, 40, 100, 25);
        servico = new JLabel("Trabalho: ");
        servico.setBounds(700, 15, 100, 80);
        valor = new JLabel("Valor Cobrado: ");
        valor.setBounds(100, 100, 100, 80);
        id = new JLabel("Id do cliente: (Observar a tabela)");
        id.setBounds(425, 100, 210, 80);
        tfCliente = new JTextField();
        tfCliente.setBounds(100, 65, 200, 25);
        tfDia = new JTextField();
        tfDia.setBounds(425, 65, 150, 25);
        tfServico = new JTextField();
        tfServico.setBounds(700, 65, 200, 25);
        tfvalor = new JTextField();
        tfvalor.setBounds(100, 150, 150, 25);
        tfId = new JTextField();
        tfId.setBounds(425, 150, 150, 25);
        //---------------------------Criando o painel para adicionar os dados das caixas de texto.------------------
        tabelaAdicao = new JPanel(new BorderLayout());
        tabelaAdicao.setBorder(new TitledBorder("Cadastro de Clientes"));
        tabelaAdicao.setLayout(null);
        tabelaAdicao.setBounds(15, 15, 950, 270);
         tabelaAdicao.add(cliente);
         tabelaAdicao.add(dia);
         tabelaAdicao.add(servico);
         tabelaAdicao.add(valor);
         tabelaAdicao.add(id);
         tabelaAdicao.add(tfCliente);
         tabelaAdicao.add(tfDia);
         tabelaAdicao.add(tfServico);
         tabelaAdicao.add(tfvalor);
         tabelaAdicao.add(tfId);
        //------------Crianndo botões para adicionar os dados ao banco de dados e adicionando ao primeiro painel.
             adicionar = new JButton("Adicionar Dados");
             adicionar.setBounds(750, 225, 150, 25);
             tabelaAdicao.add(adicionar);
             mostrar = new JButton("Mostrar Dados");
             mostrar.setBounds(765, 300, 150, 25);
             atualizar = new JButton("Atualizar");
             atualizar.setBounds(600, 300, 150, 25);
             excluir = new JButton("Excluir");
             excluir.setBounds(435, 300, 150, 25);
             add(tabelaAdicao); 
             add(mostrar);
             add(atualizar);
             add(excluir);
       //----------------------------------------Instância da classe Atribuindo--------------------------------------
       atribuindo = new Atribuindo();
      
            // Estrutura de Decisão para banco de dados caso a conexão não tenha acontecido.
            if(!atribuindo.bd.getConnection()){
            JOptionPane.showMessageDialog(null, "Falha na Conexão , o Sistema sera fechado!");
            System.exit(0);
        }
    }
    
    public void definirEventos() {
        /*
        Criando um evento de click para capturar cada dado das caixas de texto, passar pelo método set,
        passar pela classe Atribuindo e depois adicionados ao banco de dados.
        */
        adicionar.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tfCliente.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "O código não pode ser vazio!");
                    tfCliente.requestFocus();
                    return;
                }
                if(tfDia.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "O código não pode estar vazio!");
                    tfDia.requestFocus();
                    return;
                }
                if(tfServico.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "O código não pode estar vazio!");
                    tfServico.requestFocus();
                    return;
                }
                if(tfvalor.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "O código não pode estar vazio!");
                    tfvalor.requestFocus();
                    return;
                }
                if(tfId.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "O código não pode ser vazio!");
                    tfId.requestFocus();
                    return;
                }
                atribuindo.atributos.setNome(tfCliente.getText());
                atribuindo.atributos.setData(tfDia.getText());
                atribuindo.atributos.setServico(tfServico.getText());
                atribuindo.atributos.setPreco(tfvalor.getText());
                atribuindo.atributos.setId(tfId.getText());
                JOptionPane.showMessageDialog(null, atribuindo.atualizar(Atribuindo.INCLUSAO));
                limparCampos();
            }
        });
        
        atualizar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            
                 if(tfCliente.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "O código não pode ser vazio!");
                    tfCliente.requestFocus();
                    return;
                }
                if(tfDia.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "O código não pode estar vazio!");
                    tfDia.requestFocus();
                    return;
                }
                if(tfServico.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "O código não pode estar vazio!");
                    tfServico.requestFocus();
                    return;
                }
                if(tfvalor.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "O código não pode estar vazio!");
                    tfvalor.requestFocus();
                    return;
                }
                atribuindo.atributos.setNome(tfCliente.getText());
                atribuindo.atributos.setData(tfDia.getText());
                atribuindo.atributos.setServico(tfServico.getText());
                atribuindo.atributos.setPreco(tfvalor.getText());
                JOptionPane.showMessageDialog(null, atribuindo.atualizar(Atribuindo.ALTERACAO));
                limparCampos();
            
            }
        });
        
        excluir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               
                atribuindo.atributos.setId(tfId.getText());
                int n = JOptionPane.showConfirmDialog(null, atribuindo.atributos.getId(),
                        "Excluir a cliente ?", JOptionPane.YES_NO_OPTION);
                if(n == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null, atribuindo.atualizar(Atribuindo.ALTERACAO));
                limparCampos();
                }
            }
        });
        
        mostrar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                frame.setVisible(false);
                DataView.abrir();
                
            }
        });
    }
    
     public void limparCampos(){
    
        tfCliente.setText("");
        tfDia.setText("");
        tfServico.setText("");
        tfvalor.setText("");
        tfId.setText("");
        
      }
    
     public static void voltar(){
    
        JFrame frame = new NailsDesigner();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
     }
    
    public static void main(String args[]){
        
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
            
                frame = new NailsDesigner();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//para fechar o programa.
                Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((tela.width - frame.getSize().width) / 2,
                (tela.height - frame.getSize().height) / 2);//Para centralizar o frame no meio da tela.
                frame.setVisible(true);//Torna o frame visível.
                
            }
        });
    }
    
}
