/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appstore;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.time.LocalDateTime;

import myinputs.Ler;

/**
 *
 * @author harris
 */
public class Fatura implements Serializable{
    
    DecimalFormat df = new DecimalFormat("####0.00");
    private static int ultimo = 0;

    static Object get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id;
    private String nome;
    private long nif;
    private ArrayList<Produto> Produtos;
    private LocalDateTime data;
    private double totPreco;

    
    //----- CONSTRUTORES -----//
    public Fatura(String nome, long nif, ArrayList<Produto> Produtos, LocalDateTime data) {
        this.id=ultimo;
        this.nome = nome;
        this.nif = nif;
        this.Produtos = new ArrayList<Produto> ();
        for(int i=0; i<Produtos.size(); i++)
            this.Produtos.add(Produtos.get(i));
        this.data = data;
        this.totPreco = 0.0;
        ultimo++;
    }
    
    public Fatura(ArrayList<Produto> Produtos){
        this.nif = 0;
        this.Produtos = new ArrayList<Produto> ();
        for(int i=0; i<Produtos.size(); i++)
            this.Produtos.add(Produtos.get(i));
        this.data = data;
        this.nome = "";
        this.id = 0;
        this.totPreco = 0.0;
        
    }

    //----- GETTERS -----//
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public long getNif() {
        return nif;
    }
    
    public ArrayList<Produto> getProdutos() {
        return Produtos;
    }
    
    public LocalDateTime getData(){
        return data;
    }
    
    public double getTotPreco(){   
        return totPreco;
    }
    
    //----- SETTERS -----//
    
    public void setId(int id){
        this.id=id;
    }
    
    public void setNomeC(String nome) {
        this.nome = nome;
    }
    
    public void setNifC(long nif) {
        this.nif = nif;
    }

    public void setProdutos(ArrayList<Produto> Produtos) {
        Produtos = (ArrayList<Produto>) Produtos.clone();
    }
    
    public void setData(LocalDateTime data){
        this.data = data;
    }
    
    public void setTotPreco(ArrayList<Produto> Produtos) {
        for (int i=0; i<Produtos.size(); i++)
            totPreco = totPreco + Produtos.get(i).getPreco();
    }

    
    //----- OUTRAS FUNÇÕES ÚTEIS -----//
    
    @Override
    public String toString(){
        return "Fatura{" +
                "\n\tNIF: " + nif +
                "\n\tID: " + id +
                "\n\tNome: " + nome +
                "\n\tProdutos: " + Produtos + 
                "\n\tPreço Total: " + df.format(totPreco) +
                "\n\tData: " + data.toString() +
                '}';
    }
    
    public boolean equals(Object obj){
        
        if (obj != null && this.getClass() == obj.getClass() ){
            Fatura f = (Fatura) obj;
            return (this.Produtos.equals(f.Produtos) && this.data.equals(f.data) && this.nif==f.nif && this.nome.equals(f.nome) && this.totPreco==f.totPreco);
        } else{
            return false;
        }
    }
    
    public Object clone(){
        Fatura f = new Fatura(this.nome, this.nif, this.Produtos, this.data);
        return f;
    }
}
