package start;

import java.util.ArrayList;

public class AtribuindoResposta {

    /**
     * @return the listas
     */
    public ArrayList<String> getListas() {
        return listas;
    }

    /**
     * @param listas the listas to set
     */
    public void setListas(ArrayList<String> listas) {
        this.listas = listas;
    }

public String nome, data, servico, preco;
private ArrayList<String> listas = new ArrayList<>();

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
        this.preco =  preco;
    }
    
}
