/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appstore;

import java.io.Serializable;

/**
 *
 * @author harris
 */

public class Cliente implements Serializable{
    
    private static int ultimo = 0;
    private int id;
    public String Nome;
    public int idade;
    public long nif;
    public String mail;
    public double walletBalance;

    
    //----- CONSTRUTOR -----//
    public Cliente( String Nome, int idade, long nif, String mail, double walletBalance){
        this.id=ultimo;
        this.Nome = Nome;
        this.idade = idade;
        this.nif = nif;
        this.mail = mail;
        this.walletBalance = walletBalance;
        ultimo++;
    }
    
    public Cliente(Cliente c){
        this.id=c.id;
        this.Nome=c.Nome;
        this.idade=c.idade;
        this.nif=c.nif;
        this.mail=c.mail;
        this.walletBalance=c.walletBalance;
              
    }

    //----- GETTERS -----//
    public int getId(){
        return id;
    }
    public String getNome() {
        return Nome;
    }
    
    public int getIdade() {
        return idade;
    }
    
    public long getNif() {
        return nif;
    }

    
    public String getMail() {
        return mail;
    }
    
    public double getWallet(){
        return walletBalance;
    }
     
    //---- SETTERS -----//
    public void setId(int id){
        this.id=id;
    }
  
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public void setWallet(double n){
        this.walletBalance = n;
    }

    //----- OUTRAS FUNÇÕES ÚTEIS -----//
    @Override
    public String toString() {
        return "Cliente{" +
                " Nome: " + Nome +
                "\n\t ID: " + id +
                "\n\t Idade: " + idade +
                "\n\t Nif: " + nif +
                "\n\t E-Mail: " + mail +
                "\n\t Wallet: " + walletBalance +
                '}';
    }
    
    public boolean equals (Object obj ) {
        if (obj != null && this.getClass() == obj.getClass() ){
        // compara as variáveis de instância dos dois objectos
            Cliente c = (Cliente) obj;
            return ( this.id==id && this.Nome.equals(c.Nome) && this.idade==c.idade && this.nif==c.nif && this.mail.equals(c.mail) && this.idade==id && this.walletBalance==walletBalance);
        } else {
            return false;
        }
    }
    
    public Object clone(){
        Cliente c = new Cliente(this.Nome,this.idade,this.nif,this.mail,this.walletBalance);
        return c;
    }

    }

    




