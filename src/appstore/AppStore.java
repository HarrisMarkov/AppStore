/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appstore;


import static appstore.Cores.*;
import static appstore.FuncoesCliente.*;
import static appstore.FuncoesFatura.*;
import static appstore.FuncoesProduto.*;
import static appstore.Login.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import myinputs.Ler;

/**
 *
 * @author harris
 */
public class AppStore{    
    
      
   
    public static void main(String[] args) throws IOException{
        
        
        // Lê os ficheiros para os ArrayLists
        ArrayList<ClienteMembro> Clientes = (ArrayList<ClienteMembro>) lerFicheiroClientes("Clientes.txt");
        ArrayList<Produto> Produtos = (ArrayList<Produto>) lerFicheiroProdutos("Produtos.txt");
        ArrayList<Fatura> Faturas = (ArrayList<Fatura>) lerFicheiroFaturas("Faturas.txt"); 
        
        //  Guarda a data atual para preencher na Fatura
        LocalDateTime data = LocalDateTime.now();
        
        // Se Clientes estiver vazio significa que não existe o ficheiro
        // de clientes ou então está corrumpido e temos de criá-lo novamente
        if(Clientes.isEmpty()){
            
            File fclien = new File("Clientes.txt");
            
            //  Inicializar Clientes
            ClienteMembro c_vasco = new ClienteMembro("Vasco Vieira",24,123456,"vasco@pooAppStore.pt",10.00, false);
            ClienteMembro c_pedro = new ClienteMembro("Pedro Marreiros",21,124859,"pedro@pooAppStore.pt",10.00, false);
            ClienteMembro c_goncalo = new ClienteMembro("Goncalo Delgadinho",24,148956,"goncalo@pooAppStore.pt",10.00, false);
            ClienteMembro c_harris = new ClienteMembro("Harris Markov",21,745896,"boss@pooAppStore.pt",10.00, false);
            
            // Adicionar clinetes ao ArrayList
            Clientes.add(c_pedro);
            Clientes.add(c_vasco);
            Clientes.add(c_harris);
            Clientes.add(c_goncalo);
            
            escreverFicheiroClientes(Clientes, "Clientes.txt");
        }
        
        // Se Produtos estiver vazio significa que não existe o ficheiro
        // de produtos ou então está corrumpido e temos de criá-lo novamente
        if(Produtos.isEmpty()){
           
            File fprods = new File("Produtos.txt");
            
            //  Inicializar Produtos (Aplicações)
            Produto p1 = new Produto(1,"Facetruques","Rede Social",0.00);
            Produto p2 = new Produto(2,"VideoTube","Plataforma online de partilha de vídeos",0.99);
            Produto p3 = new Produto(3,"iMail","Aplicação de e-mail",1.99);
            Produto p4 = new Produto(4,"WhatsUp","Aplicação de mensagens",0.99);
            Produto p5 = new Produto(5,"SmartNote","Aplicação que permite guardar notas (ex: lista de compras,etc)",5.99);
 
            // Adicionar Produtos ao ArrayList
            Produtos.add(p1);
            Produtos.add(p2);
            Produtos.add(p3);
            Produtos.add(p4);
            Produtos.add(p5);
            
            escreverFicheiroProdutos(Produtos, "Produtos.txt");
        }
        
        // Se Faturas estiver vazio significa que não existe o ficheiro
        // de Faturas ou então está corrumpido e temos de criá-lo novamente
        if(Faturas.isEmpty()){
            
            File ffat = new File("Faturas.txt");
            
            // Inicializar listas de produtos
            // As diferentes listas de produtos vão ser usadas para
            // inicializar as faturas, caso não exista o ficheiro de Faturas
            ArrayList<Produto> lista1 = new ArrayList<Produto>();
            lista1.add(Produtos.get(4));
            lista1.add(Produtos.get(2));
            lista1.add(Produtos.get(0)); 
        
            ArrayList<Produto> lista2 = new ArrayList<Produto>();
            lista2.add(Produtos.get(0)); 
            lista2.add(Produtos.get(3)); 
            lista2.add(Produtos.get(2)); 
        
            ArrayList<Produto> lista3 = Produtos;
        
            // Inicializar Faturas
            Fatura f1 = new Fatura("Cliente Desconhecido",999999,lista1,data);
            Fatura f2 = new Fatura("Harris Markov",745896,lista2,data);
            Fatura f3 = new Fatura("Vasco Vieira",123456,lista3,data);
            
            f1.setTotPreco(lista1);
            f2.setTotPreco(lista2);
            f3.setTotPreco(lista3);
        
            Faturas.add(f1);
            Faturas.add(f2);
            Faturas.add(f3);
            
            escreverFicheiroFaturas(Faturas, "Faturas.txt");
        }
        
        //Cliente User = new Cliente();
        ArrayList<Produto> prods = new ArrayList<Produto>();
        int in=0,j=0, flag=0, login, id=0;
        DecimalFormat df = new DecimalFormat("####0.00");
        
        //   LOGIN   //
        do{
            System.out.println(ANSI_BLUE + "################################################" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_RED +" BEM VINDO À NOSSA LOJA DE APLICAÇÕES " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "################################################"  + ANSI_RESET);
            System.out.println(ANSI_BLUE + "1. " + ANSI_RESET + "Login");
            System.out.println(ANSI_BLUE + "2. " + ANSI_RESET + "Registo");
            System.out.println("");
            System.out.println(ANSI_BLUE + "0. " + ANSI_RESET + ANSI_RED + "Sair" + ANSI_RESET);
            System.out.println("-------------------------------");
            do{
                login = Ler.umInt();
                if (login > 2 || login < 0) {
                    System.out.println(ANSI_RED + "Introduza um número válido." + ANSI_RESET);
                }
            }while (login > 2 || login < 0);
            
            String user="";
            String pass="";
            
            //  Se tem conta vamos fazer login
            if(login == 1){
                
                // Esta verificação de login=0? serve para sair da aplicação
                // caso o utilizador não tenha conta e não deseje criar uma
                if(login == 0){
                    System.out.println("Obrigado e volte sempre!");
                    return;
                }
                
                int op = 0;
                System.out.println("-------------------------------");
                System.out.print(ANSI_BLUE + "Introduza o seu username: " + ANSI_RESET);
                user = Ler.umaString();
                if(!findClienteNome(Clientes,user)){
                    System.out.println(ANSI_RED + "O username que introduziu não está registrado!" + ANSI_RESET);
                    do{
                        System.out.println(ANSI_BLUE + "--- Quer criar uma conta? ---" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "1. " + ANSI_RESET + "Sim");
                        System.out.println(ANSI_BLUE + "2. " + ANSI_RESET + "Não");
                        System.out.println("");
                        System.out.println(ANSI_BLUE + "0. " + ANSI_RESET + ANSI_RED + "Sair" + ANSI_RESET);
                        
                    
                        op = Ler.umInt();
                        if (op > 2 && op < 1) {
                            System.out.println(ANSI_RED + "Introduza um número válido." + ANSI_RESET);
                        }
                        if(op == 2){
                            System.out.println(ANSI_RED + "TEM DE CRIAR UMA CONTA ANTES DE ENTRAR NA APP STORE!" + ANSI_RESET);
                        }
                        if(op == 1){
                            login = 2;
                        }
                        
                    }while (op != 1);
                }
                
                if(login != 2){
                    System.out.print(ANSI_BLUE + "Intorduza a sua password: " + ANSI_RESET);
                    pass = Ler.umaString();
                
                    //Username e pass corretos
                    if(loginCliente(Clientes,user,pass)){ 
                        System.out.println(ANSI_PURPLE + "Login efetuado com sucesso!" + ANSI_RESET);
                        
                        for(int i=0; i<Clientes.size(); i++){
                            if(Clientes.get(i).getNome().equalsIgnoreCase(user)){
                                id=i;
                                break;
                            }  
                        }
                        login = 0;
                    }
                }
            }
            
            //  Se não tem conta cria-se uma antes de entrar
            //  Quando um novo utilizador cria uma conta são lhe 
            //  adicionados 10 pontos à Wallet, para poder fazer
            //  compras na AppStore
            if(login == 2){ 
                inserirNovoCliente(Clientes);
                int size = Clientes.size();
                Clientes.get(size-1).setWallet(10.0);
                id=size-1;
                login = 0;
            }
        
        }while(login != 0);
        
        do{
           
            System.out.println(ANSI_BLUE + "################################################" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_RED +" BEM VINDO À NOSSA LOJA DE APLICAÇÕES " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "################################################"  + ANSI_RESET);
            System.out.println("-------------------------------");
            System.out.println("| Utilizador: " + Clientes.get(id).getNome() + "    User: " +ANSI_RED + isPremium(Clientes,id) + ANSI_RED);
            System.out.println("| Wallet: " + df.format(Clientes.get(id).getWallet()));
            System.out.println("| Aplicações instaladas nesta sessão: " + prods.size());
            System.out.println("-------------------------------");
            System.out.println(ANSI_BLUE + "1." + ANSI_RESET + " Fazer compras");
            System.out.println(ANSI_BLUE + "2." + ANSI_RESET + " Aceder ao registo de faturas");
            System.out.println(ANSI_BLUE + "3." + ANSI_RESET + " Aceder ao registo de clientes");
            System.out.println(ANSI_BLUE + "4." + ANSI_RESET + " Aceder ao registo de produtos");
            System.out.println(ANSI_BLUE + "5." + ANSI_RESET + " Gestão da conta");
            //System.out.println(ANSI_BLUE + "6." + ANSI_RESET + " ");
            System.out.println(ANSI_BLUE + "0." + ANSI_RESET + ANSI_RED + " Sair" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "################################################" + ANSI_RESET);
            
            in = Ler.umInt();
            switch(in){
                
                case 1: //----- COMPRAS -----//
                    do{ 
                        int size = Produtos.size();
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_CYAN + " LISTA DE APLICAÇÕES " + ANSI_RESET + ANSI_BLUE + "#####");
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET); 
                        listaProdutosComPrecos2(Produtos, id, Clientes.get(id).getPremium());
                        System.out.println("");
                        System.out.println(ANSI_BLUE + (size + 1) + ANSI_RESET + ". Ver aplicações que instalou nesta sessão");
                        System.out.println(ANSI_BLUE + "0." + ANSI_RESET + ANSI_RED + " Sair" + ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "\nEscolha uma Aplicação para instalar" + ANSI_RESET);
                                              
                        do {
                            j = Ler.umInt();
                            if (j > Produtos.size() + 1 || j < 0) {
                                System.out.println(ANSI_RED + "Introduza um número válido." + ANSI_RESET);
                            }
                        } while (j > Produtos.size() + 1 || j < 0);
                        
                        if(j != 0 && j != size+1){
                            if(Clientes.get(id).getWallet() > Produtos.get(j-1).getPreco()){
                                if(!prods.contains(Produtos.get(j-1))){
                                    
                                    Produto prod = Produtos.get(j-1);
                                    if(isPremium(Clientes, id).equalsIgnoreCase("Premium")){
                                        prod.setPreco(prod.getPreco()/2);
                                        prods.add(prod);
                                    }
                                    if(isPremium(Clientes, id).equalsIgnoreCase("Normal"))
                                        prods.add(prod);
                                    
                                    if(Clientes.get(id).getPremium()==true)
                                        Clientes.get(id).setWallet(Clientes.get(id).getWallet() - prod.getPreco()/2);
                                    else
                                        Clientes.get(id).setWallet(Clientes.get(id).getWallet() - prod.getPreco());
                                    
                                    System.out.println("Downloading " + prod.getNome() + "...");
                                    if(!prods.isEmpty()){
                                        flag=1;
                                    }
                                }
                                
                            }
                            else{
                                System.out.println(ANSI_RED + "Não tem fundos suficientes para efetuar a compra!" + ANSI_RESET);
                                System.out.println("Dirija-se ao menu de" + ANSI_RED + "Gestão da conta" + ANSI_RESET + "para adicionar fundos à sua Wallet!");
                            }
                            
                        }
                            
                        if(j == size+1){
                            System.out.println("Aplicações instaladas nesta sessão");
                            System.out.println("-------------------------------");
                            listaProdutos(prods);
                        }
                        
                    }while(j!=0 && j<Produtos.size()+1);
                    break;    
                case 2: //----- FATURAS -----//
                    
                    System.out.print(ANSI_RED + "Password: " + ANSI_RESET);
                    String pass2 = Ler.umaString();
                    if ( ! pass2.equalsIgnoreCase("admin") ){
                        System.out.println(ANSI_RED + "PASSWORD INVÁLIDA!!!" + ANSI_RESET);
                        break;
                    }
                    
                    do{
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###########" + ANSI_RESET + ANSI_CYAN + " FATURAS " + ANSI_RESET + ANSI_BLUE + "###########" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "1." + ANSI_RESET + " Ver todas as faturas");
                        System.out.println(ANSI_BLUE + "2." + ANSI_RESET + " Pesquisar faturas");
                        System.out.println(ANSI_BLUE + "0." + ANSI_RESET + ANSI_RED + " Sair" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        
                        do {
                            j = Ler.umInt();
                            if (j > 2 || j < 0) {
                                System.out.println(ANSI_RED + "Introduza um número válido." + ANSI_RESET);
                            }
                        } while (j > 2 || j < 0);
                        
                        if(j == 1){
                            System.out.println("-------------------------------");
                            listaFaturas(Faturas);
                        }
                        
                        if(j == 2){
                            System.out.println("-------------------------------");
                            PesquisaFatura(Faturas);
                        }
                    }while(j!=0);
                    break;
                        
                case 3: //----- CLIENTES -----//
                    
                    System.out.print(ANSI_RED + "Password: " + ANSI_RESET);
                    String pass3 = Ler.umaString();
                    if ( ! pass3.equalsIgnoreCase("admin") ){
                        System.out.println(ANSI_RED + "PASSWORD INVÁLIDA!!!" + ANSI_RESET);
                        break;
                    }
                    
                    do{
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "##########" + ANSI_RESET + ANSI_CYAN + " CLIENTES " + ANSI_RESET + ANSI_BLUE + "###########" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "1." + ANSI_RESET + " Listar todos os clientes");
                        System.out.println(ANSI_BLUE + "2." + ANSI_RESET + " Inserir novo clinete");
                        System.out.println(ANSI_BLUE + "3." + ANSI_RESET + " Remover cliente");
                        System.out.println(ANSI_BLUE + "4." + ANSI_RESET + " Modificar clinete");
                        System.out.println(ANSI_BLUE + "5." + ANSI_RESET + " Consultar informação de um cliente");
                        System.out.println(ANSI_BLUE + "6." + ANSI_RESET + " Cliente que efetuou mais compras na loja");
                        System.out.println(ANSI_BLUE + "7." + ANSI_RESET + " Cliente que gastou mais dinheiro na loja");
                        System.out.println(ANSI_BLUE + "0." + ANSI_RESET + ANSI_RED + " Sair" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        
                        do {
                            j = Ler.umInt();
                            if (j > 7 || j < 0) {
                                System.out.println(ANSI_RED + "Introduza um número válido." + ANSI_RESET);
                            }
                        } while (j > 7 || j < 0);
                        
                        if(j == 1){
                            System.out.println("-------------------------------");
                            listaClientes(Clientes);
                        }
                        
                        if(j == 2){
                            System.out.println("-------------------------------");
                            inserirNovoCliente(Clientes);
                        }
                        
                        if(j == 3){
                            System.out.println("-------------------------------");
                            apagarCliente(Clientes);
                        }
                            
                        if(j == 4){
                            System.out.println("-------------------------------");
                            modificarCliente(Clientes);
                        }
                            
                        if(j == 5){
                            System.out.println("-------------------------------");
                            clientesInfo(Clientes);
                        }
                        
                        if(j == 6){
                            System.out.println("-------------------------------");
                            clienteComMaisCompras(Faturas,Clientes);
                        }
                        
                        if(j == 7){
                            System.out.println("-------------------------------");
                            clienteGastouMaisDinheiro(Faturas,Clientes);
                        }
                    }while(j!=0);
                    break;
                        
                case 4: //---- PRODUTOS -----//
                    
                    System.out.print(ANSI_RED + "Password: " + ANSI_RESET);
                    String pass4 = Ler.umaString();
                    if ( ! pass4.equalsIgnoreCase("admin") ){
                        System.out.println(ANSI_RED + "PASSWORD INVÁLIDA!!!" + ANSI_RESET);
                        break;
                    }
                    
                    do{
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "##########" + ANSI_RESET + ANSI_CYAN + " PRODUTOS " + ANSI_RESET + ANSI_BLUE + "###########" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "1." + ANSI_RESET + " Listar todos os produtos");
                        System.out.println(ANSI_BLUE + "2." + ANSI_RESET + " Inserir novo produto");
                        System.out.println(ANSI_BLUE + "3." + ANSI_RESET + " Remover produto");
                        System.out.println(ANSI_BLUE + "4." + ANSI_RESET + " Modificar produto");
                        System.out.println(ANSI_BLUE + "5." + ANSI_RESET + " Consultar informação de um produto");
                        System.out.println(ANSI_BLUE + "6." + ANSI_RESET + " Ver qual o produto mais vendido");
                        System.out.println(ANSI_BLUE + "7." + ANSI_RESET + " Ver qual o produto menos vendido");
                        System.out.println(ANSI_BLUE + "8." + ANSI_RESET + " Ver qual o produto mais caro");
                        System.out.println(ANSI_BLUE + "9." + ANSI_RESET + " Ver qual o produto mais barato");
                        System.out.println(ANSI_BLUE + "0." + ANSI_RESET + ANSI_RED + " Sair" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###############################" + ANSI_RESET);
                        
                        do {
                            j = Ler.umInt();
                            if (j >  9 || j < 0) {
                                System.out.println(ANSI_RED + "Introduza um número válido." + ANSI_RESET);
                            }
                        } while (j > 9 || j < 0);
                              
                        if(j == 1){
                            System.out.println("-------------------------------");
                            listaProdutos(Produtos);
                        }
                        
                        if(j == 2){
                            System.out.println("-------------------------------");
                            adicionarProduto(Produtos);
                        }
                        
                        if(j == 3){
                            System.out.println("-------------------------------");
                            apagarProduto(Produtos);
                        }
                        
                        if(j == 4){
                            System.out.println("-------------------------------");
                            modificarProduto(Produtos);
                        }
                        if(j == 5){
                            System.out.println("-------------------------------");
                            ProdutosInfo(Produtos);
                        }
                        
                        if(j == 6){
                            System.out.println("-------------------------------");
                            produtoMaisComprado(Produtos,Faturas);
                        }
                        
                        if(j == 7){
                            System.out.println("-------------------------------");
                            produtoMenosComprado(Produtos,Faturas);
                        }
                        
                        if(j == 8){
                            System.out.println("-------------------------------");
                            produtoMaisCaro(Produtos);
                        }
                        
                        if(j == 9){
                            System.out.println("-------------------------------");
                            produtoMaisBarato(Produtos);
                        }
                    }while(j!=0);
                    break;
                          
                case 5: //----- GESTÃO DA CONTA -----//
                    do{
                        System.out.println(ANSI_BLUE + "###################################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "##########" + ANSI_RESET + ANSI_CYAN + " GESTÃO DA CONTA " + ANSI_RESET + ANSI_BLUE + "###########" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###################################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "1." + ANSI_RESET + " Alterar informação da conta.");
                        System.out.println(ANSI_BLUE + "2." + ANSI_RESET + " Histórico de compras na loja.");
                        System.out.println(ANSI_BLUE + "3." + ANSI_RESET + " Adicionar fundos à Wallet.");
                        System.out.println(ANSI_BLUE + "0." + ANSI_RESET + ANSI_RED + " Sair" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###################################" + ANSI_RESET);
                        System.out.println(ANSI_BLUE + "###################################" + ANSI_RESET);
                        
                        do {
                            j = Ler.umInt();
                            if (j >  3 || j < 0) {
                                System.out.println(ANSI_RED + "Introduza um número válido." + ANSI_RESET);
                            }
                        } while (j > 3 || j < 0);
                         
                        if(j==1){
                            System.out.println("-------------------------------");
                            modificarContaCliente(Clientes.get(id));
                        }
                        
                        if(j==2){
                            System.out.println("-------------------------------");
                            historicoComprasCliente(Clientes.get(id),Faturas);
                        }
                        
                        if(j==3){
                            System.out.println("-------------------------------");
                            System.out.println(ANSI_BLUE + "Wallet antes de adicionar fundos : " + ANSI_RESET + df.format(Clientes.get(id).getWallet()));
                            System.out.println(ANSI_BLUE + "Introduza a quantidade de fundos que quer adicionar à sua Wallet:" + ANSI_RESET);
                            double valor = 0.0;
                            do {
                                valor = Ler.umDouble();
                                if (valor < 0.0) {
                                    System.out.println(ANSI_RED + "Introduza um número válido." + ANSI_RESET);
                                }
                                if (valor > 99.0){
                                    System.out.println(ANSI_RED + "NÃO PODE ADICIONAR MAIS DE 99 FUNDOS DE CADA VEZ" + ANSI_RESET);
                                }
                                if(valor >20){
                                    valor = valor +5.0;
                                    System.out.println(ANSI_RED + "Foi adicionado um bónus à sua wallet\n" + ANSI_RESET);
                                    Clientes.get(id).setPremium(true);
                                 } 
                            } while (valor > 99.0 || valor < 0.0);
                            Clientes.get(id).setWallet(Clientes.get(id).getWallet() + valor);
                            System.out.println(ANSI_BLUE + "Wallet depois de adicionar fundos : " + ANSI_RESET + df.format(Clientes.get(id).getWallet()));
                        }
                        
                    }while(j!=0);
                    break;
                    
                case 0:
                    break;
                    
                default:
                    System.out.println(ANSI_RED + "Escolha inválida" + ANSI_RESET);
            }
        }while(in != 0);
        
        if (flag == 1){ //  O UTILIZADOR EFETUOU COMPRAS E TEMOS DE REGISTAR A FATURA
            
            System.out.println(ANSI_RED + "Só falta registar a Fatura antes de sair!" + ANSI_RESET);
            data = LocalDateTime.now();
            Fatura f = new Fatura(Clientes.get(id).getNome(),Clientes.get(id).getNif(),prods,data);
            f.setTotPreco(prods);      
            Faturas.add(f);
            System.out.println(f.toString());
            System.out.println(ANSI_PURPLE + "Fatura guardada com sucesso!" + ANSI_RESET);
        }
        
        escreverFicheiroProdutos(Produtos, "Produtos.txt");
        escreverFicheiroFaturas(Faturas, "Faturas.txt");
        escreverFicheiroClientes(Clientes, "Clientes.txt");
    }
    
}