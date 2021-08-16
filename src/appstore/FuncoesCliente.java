/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appstore;

import static appstore.Cores.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import myinputs.Ler;

/**
 *
 * @author harris
 */
public class FuncoesCliente {
   
    //Ler e escrever ficheiro de clientes
    
    public static Object lerFicheiroClientes(String fname) throws FileNotFoundException, IOException{
        ArrayList<ClienteMembro> Clientes = new ArrayList<ClienteMembro>();
        try{
            FileInputStream is = new FileInputStream (fname);
            ObjectInputStream oIS = new ObjectInputStream(is);
            Clientes= (ArrayList<ClienteMembro>)oIS.readObject();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncoesCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Clientes;
    }
    
    public static void escreverFicheiroClientes(ArrayList<ClienteMembro> Clientes, String fname) throws FileNotFoundException{
        try{
            FileOutputStream os = new FileOutputStream(fname);
            ObjectOutputStream oOS = new ObjectOutputStream(os);
            oOS.writeObject( Clientes );
            oOS.close();
            }
        catch (IOException e){
                System.out.println(e.toString());
            }
    }
    
    // ----- ENCONTRA UM CLINETE SE EXISTIR NA LISTA ------//
    public static boolean findClienteNome(ArrayList<ClienteMembro> Clientes, String nome){
        
        for (int i=0; i<Clientes.size(); i++)
            if(Clientes.get(i).getNome().equalsIgnoreCase(nome))
                return true;
        
        return false;
    }
    
    // ----- ENCONTRA UM CLINETE SE EXISTIR NA LISTA ------//
    public static boolean findClienteID(ArrayList<ClienteMembro> Clientes, long id){
        
        for (int i=0; i<Clientes.size(); i++)
            if(id == Clientes.get(i).getId())
                return true;
        
        return false;
    }
    
    // ----- LISTAR OS CLIENTES EXISTENTES -----//
    public static void listaClientes(ArrayList<ClienteMembro> Clientes) {

        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_GREEN + " LISTA DE CLIENTES " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
        
        for (int i = 0; i < Clientes.size(); i++) {
            System.out.println(ANSI_BLUE + (i + 1) + ". " + ANSI_RESET + Clientes.get(i).getNome());
        }
    }
    
    // ----- INSERIR NOVO CLIENTE NA LISTA -----//
    public static void inserirNovoCliente(ArrayList<ClienteMembro> Clientes){
        
        int flag = 1;
        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_GREEN + " INSERIR NOVO CLIENTES " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Introduza o nome do cliente: " + ANSI_RESET);
        String nome = Ler.umaString();
        do{
            if(findClienteNome(Clientes,nome)){
                System.out.println(ANSI_RED + "Já existe um utilizador com esse nome!" + ANSI_RESET);
                nome = Ler.umaString();
            }
        }while(findClienteNome(Clientes,nome) == true);
        
        System.out.println(ANSI_BLUE + "Introduza a idade do cliente: " + ANSI_RESET);
        int idade = Ler.umInt();
        System.out.println(ANSI_BLUE + "Introduza o NIF do cliente: " + ANSI_RESET);
        long nif = Ler.umLong();
        System.out.println(ANSI_BLUE + "Introduza o e-mail do cliente: " + ANSI_RESET);
        String mail = Ler.umaString();
        while(flag == 1){
            if( !mail.contains("@") && (!mail.contains(".com") || !mail.contains(".pt")) ){
                System.out.println(ANSI_RED + "O e-mail que inseriu não é valido!\nPor favor insira o e-mail novamente" + ANSI_RESET);
                mail = Ler.umaString();
                continue;
            }
            flag=0;
        }
        
        ClienteMembro c = new ClienteMembro(nome,idade,nif,mail,0.00, false);
        Clientes.add(c);
        System.out.println(ANSI_PURPLE + "Cliente guardado com sucesso!" + ANSI_RESET + "\n-----");

    }
    
    // -----MODIFICAR A CONTA DO CLIENTE ATUAL -----//
    public static void modificarContaCliente(ClienteMembro c){
        
        
        System.out.println(ANSI_BLUE + "Nome: " + ANSI_RESET + c.getNome());
        System.out.println(ANSI_BLUE + "NIF: " + ANSI_RESET + c.getNif());
        System.out.println(ANSI_BLUE + "Idade: " + ANSI_RESET + c.getIdade());
        System.out.println(ANSI_BLUE + "E-mail: " + ANSI_RESET + c.getMail());
        System.out.println(ANSI_BLUE + "ID: " + ANSI_RESET + c.getId());
        
        int in;
        
        do {
            System.out.println("-------------------------------");
            System.out.println(ANSI_BLUE + "Escolha o campo a alterar:" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "1." + ANSI_RESET + " Alterar o nome");
            System.out.println(ANSI_BLUE + "2." + ANSI_RESET + " Alterar o NIF");
            System.out.println(ANSI_BLUE + "3." + ANSI_RESET + " Alterar a idade");
            System.out.println(ANSI_BLUE + "4." + ANSI_RESET + " Alterar o e-mail");
            //System.out.println(ANSI_BLUE + "5." + ANSI_RESET + " Alterar o ID");
            
            System.out.println(ANSI_BLUE + "0." + ANSI_RESET + ANSI_RED + " Sair" + ANSI_RESET);
       
            in = Ler.umInt();
            switch (in) {
                case 1: {
                    System.out.print(ANSI_BLUE + "Novo Nome: " + ANSI_RESET);
                    c.setNome(Ler.umaString());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
                case 2: {
                    System.out.print(ANSI_BLUE + "Novo NIF: " + ANSI_RESET);
                    c.setNif(Ler.umLong());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
                case 3: {
                    System.out.print(ANSI_BLUE + "Nova idade: " + ANSI_RESET);
                    c.setIdade(Ler.umInt());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
                case 4: {
                    System.out.print(ANSI_BLUE + "Novo e-mail: " + ANSI_RESET);
                    c.setMail(Ler.umaString());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
                /*
                case 5: {
                    System.out.println(ANSI_BLUE + "Novo ID:" + ANSI_RESET);
                    c.setId(Ler.umLong());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
                */
                case 0:
                    break;
                default:
                    System.out.println(ANSI_RED + "Escolha inválida!" + ANSI_RESET);
            }
        } while (in != 0);
    }
    
    // ----- MODIFICAR UM CLINETE CASO EXISTA NA LISTA -----//
    public static void modificarCliente(ArrayList<ClienteMembro> Clientes){
         
        int in, j;
        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_GREEN + " MODIFICAR CLIENTES " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Escolha o cliente a alterar" + ANSI_RESET);
        listaClientes(Clientes);
        do {
            j = Ler.umInt();
            if (j > Clientes.size() || j <= 0) {
                System.out.println(ANSI_RED + "Introduza um número válido!" + ANSI_RESET);
            }
        } while (j > Clientes.size() || j <= 0);
        do {
            System.out.println(ANSI_BLUE + "Escolha o campo a alterar:" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "1." + ANSI_RESET + " Alterar o nome do cliente");
            System.out.println(ANSI_BLUE + "2." + ANSI_RESET + " Alterar o NIF");
            System.out.println(ANSI_BLUE + "3." + ANSI_RESET + " Alterar a idade");
            System.out.println(ANSI_BLUE + "4." + ANSI_RESET + " Alterar o e-mail");
            //System.out.println(ANSI_BLUE + "5." + ANSI_RESET + " Alterar o ID");
            
            System.out.println(ANSI_BLUE + "0." + ANSI_RESET + ANSI_RED + " Sair" + ANSI_RESET);
       
            in = Ler.umInt();
            switch (in) {
                case 1: {
                    System.out.print(ANSI_BLUE + "Novo Nome: " + ANSI_RESET);
                    Clientes.get(j - 1).setNome(Ler.umaString());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
                case 2: {
                    System.out.print(ANSI_BLUE + "Novo NIF: " + ANSI_RESET);
                    Clientes.get(j - 1).setNif(Ler.umLong());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
                case 3: {
                    System.out.print(ANSI_BLUE + "Nova idade: " + ANSI_RESET);
                    Clientes.get(j-1).setIdade(Ler.umInt());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
                case 4: {
                    System.out.print(ANSI_BLUE + "Novo e-mail: " + ANSI_RESET);
                    Clientes.get(j-1).setMail(Ler.umaString());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
                /*
                case 5: {
                    System.out.println(ANSI_BLUE + "Novo ID:" + ANSI_RESET);
                    Clientes.get(j-1).setId(Ler.umLong());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
                */
                case 0:
                    break;
                default:
                    System.out.println(ANSI_RED + "Escolha inválida!" + ANSI_RESET);
            }
        } while (in != 0);
    }
    
    //----- APAGA CLIENTE CASO EXISTA NA LISTA -----//
    public static void apagarCliente(ArrayList<ClienteMembro> Clientes){
        
        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_GREEN + " REMOVER CLIENTE " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
        
        System.out.println(ANSI_BLUE + "Escolha o cliente que quer eliminar" + ANSI_RESET);
        listaClientes(Clientes);
        int in = Ler.umInt();
        while (in <= 0 || in > Clientes.size()) {
            System.out.println(ANSI_RED + "Introduza um número válido!" + ANSI_RESET);
            in = Ler.umInt();
        }
        System.out.println(ANSI_RED + "Tem a certeza que pretende eliminar o cliente " + ANSI_RESET + Clientes.get(in - 1).getNome() + ANSI_RED + " ?" + ANSI_RESET);
        System.out.println(ANSI_RED + "Introduza 'S' para eliminar, outro valor para cancelar." + ANSI_RESET);
        char c = Ler.umChar();
        if (c != 'S' && c != 's') {
            return;
        }
        
        Clientes.remove(in - 1);
        System.out.println(ANSI_PURPLE + "Cliente eliminado com sucesso!" + ANSI_RESET + "\n-----");
    }
    
    //----- CONSULTAR INFORMAÇÃO DE UM CLIENTE CASO EXISTA NA LISTA -----//
    public static void clientesInfo(ArrayList<ClienteMembro> Clientes) {
        
        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_GREEN + " CONSULTAR CLIENTE " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
        
        if (Clientes.isEmpty()) {
            System.out.println(ANSI_RED + "Não há clientes a mostrar.\n-----" + ANSI_RESET);
            return;
        }
        System.out.println(ANSI_BLUE + "Escolha o cliente que pretende ver." + ANSI_RESET);
        listaClientes(Clientes);
        int in;
        do {
            in = Ler.umInt();
            if (in > Clientes.size() || in < 0) {
                System.out.println(ANSI_RED + "Introduza um número válido." + ANSI_RESET);
            }
        } while (in > Clientes.size() || in <= 0);
        System.out.println(Clientes.get(in - 1).toString());
    }
    
    //----- IMPRIME O NOME DO CLIENTE QUE EFETUOU MAIS COMPRAS NA LOJA -----//
    public static void clienteComMaisCompras(ArrayList<Fatura> Faturas, ArrayList<ClienteMembro> Clientes){
        
        int max = 0;
        int len = Clientes.size();
        int[] c = new int[len];
        
        for( int i=0; i<Clientes.size(); i++){
            for( int j=0; j<Faturas.size(); j++){
                if(Clientes.get(i).getNome().equalsIgnoreCase(Faturas.get(j).getNome()))
                    c[i]++;
            }
        }
        
        for( int i=0; i<c.length; i++){
            if(c[i] > max)
                max = i;
        }
        
        System.out.println(ANSI_PURPLE + "Cliente que efetuou mais compras: " + ANSI_RESET + Clientes.get(max).getNome());
    }
    
    //----- IMPRIME O NOME DO CLIENTE QUE GASTOU MAIS DINHEIRO NA LOJA -----//
    public static void clienteGastouMaisDinheiro(ArrayList<Fatura> Faturas, ArrayList<ClienteMembro> Clientes) {
        
        double maxPreco = 0.0;
        int maxIndex = 0;
        int len = Clientes.size();
        double[] c = new double[len];
        
        for( int i=0; i<Clientes.size(); i++){
            for( int j=0; j<Faturas.size(); j++){
                if(Clientes.get(i).getNome().equalsIgnoreCase(Faturas.get(j).getNome()))
                    c[i] = c[i] + Faturas.get(j).getTotPreco();
            }
        }
        
        for( int i=0; i<c.length; i++){
            if(c[i] > maxPreco)
                maxIndex = i;
        }
        
        System.out.println(ANSI_PURPLE + "Cliente que gastou mais dinheiro na loja: " + ANSI_RESET + Clientes.get(maxIndex+1).getNome());
    }
    
    
    public static String isPremium(ArrayList<ClienteMembro> Clientes, int id){

        if(Clientes.get(id).getPremium()==true)
            return "Premium";
        return "Normal";
    }
    
    
    public static void historicoComprasCliente(ClienteMembro c, ArrayList<Fatura> Faturas){
        
        ArrayList<Produto> prods = new ArrayList<Produto>();
        
        System.out.println("Aplicações da loja instaladas:");
        for(int i=0; i<Faturas.size(); i++){
            if(Faturas.get(i).getNome().equalsIgnoreCase(c.getNome())){
               for(int j=0; j<Faturas.get(i).getProdutos().size(); j++){
                    if(!prods.contains(Faturas.get(i).getProdutos().get(j))){
                        System.out.println((j + 1) + ". " + Faturas.get(i).getProdutos().get(j).getNome() + ", Preço: " + Faturas.get(i).getProdutos().get(j).getPreco());
                        prods.add(Faturas.get(i).getProdutos().get(j));
                    }
                }
            }
        }
    }
}