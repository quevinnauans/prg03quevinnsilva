
package br.com.ifba.sertaofrut.entity;

public class Producao {
    private String data;
    private String fruta;
    private double quant_produzida;
    private Produto produto; // objeto como atributo
    private StatusProducao status;
    
    //Construtor vazio e toda produção nasce com status EM_ANDAMENTO
    public Producao(){
        this.status = StatusProducao.EM_ANDAMENTO;
    }
    
    public Producao(String data, String fruta, double quant_produzida, Produto produto){
        this.data = data;
        this.fruta = fruta;
        this.quant_produzida = quant_produzida;
        this.produto = produto;
        this.status = StatusProducao.EM_ANDAMENTO; // Status padrão ao criar
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFruta() {
        return fruta;
    }

    public void setFruta(String fruta) {
        this.fruta = fruta;
    }

    public double getQuant_produzida() {
        return quant_produzida;
    }

    public void setQuant_produzida(double quant_produzida) {
        this.quant_produzida = quant_produzida;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public StatusProducao getStatus() {
        return status;
    }

    public void setStatus(StatusProducao status) {
        this.status = status;
    }
    
    
}
