/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appstore;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author harris
 */
public class Produto implements Serializable {
    
    DecimalFormat df = new DecimalFormat("####0.00");
    private long id;
    private String nome;
    private String descricao;
    private double preco;

    
    //----- CONSTRUTOR -----//
    public Produto(long id,String nome, String descricao, double preco) {
        this.id=id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    //----- GETTERS -----//
    public long getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public double getPreco() {
        return preco;
    }
    
    //----- SETTERS -----//
    public void setId(long id){
        this.id=id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "  ID= " + id +
                ", nome= " + nome +
                ", descricao= " + descricao +
                ", preco= " + df.format(preco) +
                '}';
    }
    
    public boolean equals (Object obj ) {
        if (obj != null && this.getClass() == obj.getClass() ){
        // compara as variáveis de instância dos dois objectos
            Produto p = (Produto) obj;
            return ( this.id==p.id && this.nome.equals(p.nome) && this.descricao.equals(p.descricao) &&  this.preco==p.preco);
        } else {
            return false;
        }
    }
    
    public Object clone(){
        Produto p = new Produto(this.id, this.nome,this.descricao,this.preco);
        return p;
    }
    
}