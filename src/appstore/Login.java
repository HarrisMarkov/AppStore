/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appstore;

import static appstore.Cores.*;
import static appstore.FuncoesCliente.*;
import java.util.ArrayList;

/**
 *
 * @author harri
 */
public class Login {
    
    public static boolean findCliente(ArrayList<ClienteMembro> Clientes, String nome){
        
        for (int i=0; i<Clientes.size(); i++)
            if(Clientes.get(i).getNome().equalsIgnoreCase(nome))
                return true;
        
        return false;
    }
    
    //----- A password é gerada automaticamente pela AppStore,
    //----- sendo constituida pela contatenação das Strings
    //----- "user" + "(...)", onde (...) é o primeiro nome
    //----- que o utilizador inserir
    public static boolean loginCliente(ArrayList<ClienteMembro> Clientes, String nome, String pass){
        
        String[] splits = nome.split(" ");
        String password = "user" + splits[0];
        
        if(findClienteNome(Clientes,nome)){
            if(pass.equalsIgnoreCase(password))
                return true;
            else{
                System.out.println(ANSI_RED + "Palavra-passe está errada!" + ANSI_RESET);
                return false;
            }
        } else
            return false;
    }
    
}
