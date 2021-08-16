/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appstore;
import java.io.Serializable;


public class ClienteMembro extends Cliente{
    
    private boolean premium;

    public ClienteMembro(Cliente c,boolean premium) {
        super(c);
        this.premium = premium;
    }

    public ClienteMembro(String Nome, int idade, long nif, String mail, double walletBalance, boolean premium) {
        super(Nome, idade, nif, mail, walletBalance);
        this.premium = premium;
    }

    public boolean getPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    @Override
    public String toString() {
        return "ClienteMembro{" + "premium=" + premium + '}';
    }

   public boolean equals (Object obj ) {
        if (obj != null && this.getClass() == obj.getClass() ){
        // compara as variÃ¡veis de instÃ¢ncia dos dois objectos
            ClienteMembro cm = (ClienteMembro) obj;
            return ( this.premium== cm.premium);
        } else {
            return false;
        }
    }
      
   public Object clone(){
        ClienteMembro cm = new ClienteMembro(this.Nome,this.idade,this.nif,this.mail,this.walletBalance,this.premium);
        return cm;
    }
   
}