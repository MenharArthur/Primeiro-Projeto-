package start;

public class Atributos {
private String nome, data, servico, preco, id;
//private int id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Atributos(){
    
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        
        this.nome = nome;
        
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        
        this.data = data;
        
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        
        this.servico = servico;
        
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {        
       
       this.preco = preco;
    }
}