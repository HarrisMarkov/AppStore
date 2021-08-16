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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import myinputs.Ler;

/**
 *
 * @author harris
 */
public class FuncoesProduto {
    
    private static DecimalFormat df2 = new DecimalFormat(".##");
    
    //Ler e escrever ficheiro de Produtos
    
    public static Object lerFicheiroProdutos(String fname) throws FileNotFoundException, IOException{
        ArrayList<Produto> Produtos = new ArrayList<Produto>();
        try{
            FileInputStream is = new FileInputStream (fname);
            ObjectInputStream oIS = new ObjectInputStream(is);
            Produtos= (ArrayList<Produto>)oIS.readObject();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncoesProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Produtos;
    }
    
    public static void escreverFicheiroProdutos(ArrayList<Produto> Produtos, String fname) throws FileNotFoundException{
        try{
            FileOutputStream os = new FileOutputStream(fname);
            ObjectOutputStream oOS = new ObjectOutputStream(os);
            oOS.writeObject( Produtos );
            oOS.close();
            }
            catch (IOException e){
                System.out.println(e.toString());
            }
    }
    
    //----- LISTA OS PRODUTOS EXISTENTES-----//
    public static void listaProdutos(ArrayList<Produto> Produtos) {

        for (int i = 0; i < Produtos.size(); i++) {
            System.out.println(ANSI_BLUE + (i + 1) + ". " + ANSI_RESET + Produtos.get(i).getNome());
        }
    }
    
    //----- LISTA OS PRODUTOS EXISTENTES E OS RESPETIVOS PREÇOS -----//
    public static void listaProdutosComPrecos(ArrayList<Produto> Produtos) {

        for (int i = 0; i < Produtos.size(); i++) {
            System.out.println(ANSI_BLUE + (i + 1) + ". " + ANSI_RESET + Produtos.get(i).getNome() + ", Preço: " + Produtos.get(i).getPreco());
        }
    }
    
    public static void listaProdutosComPrecos2(ArrayList<Produto> Produtos, int id, boolean ver) {
        
        if(ver==true){
            for (int i = 0; i < Produtos.size(); i++) 
                System.out.println(ANSI_BLUE + (i + 1) + ". " + ANSI_RESET + Produtos.get(i).getNome() + ", Preço: " + df2.format(Produtos.get(i).getPreco()/2));
            return;   
        }
        for (int i = 0; i < Produtos.size(); i++) 
                System.out.println(ANSI_BLUE + (i + 1) + ". " + ANSI_RESET + Produtos.get(i).getNome() + ", Preço: " + Produtos.get(i).getPreco());
    }
    
    // ----- ENCONTRA UM PRODUTO SE EXISTIR NA LISTA ------//
    public static boolean findProduto(ArrayList<Produto> Produtos, long id){
        
        for (int i=0; i<Produtos.size(); i++)
            if(id == Produtos.get(i).getId())
                return true;
        
        return false;
    }
    
    //----- INSERIR UM PRODUTO NA LISTA -----//
    public static void adicionarProduto(ArrayList<Produto> Produtos) {

        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_GREEN + " INSERIR NOVO PRODUTO " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
        ArrayList<String> f = new ArrayList<String>();
        System.out.println(ANSI_BLUE + "Introduza o nome do produto:" + ANSI_RESET);
        String nome = Ler.umaString();
        System.out.println(ANSI_BLUE + "Introduza o preço do produto:" + ANSI_RESET);
        double preco = Ler.umDouble();
        System.out.println(ANSI_BLUE + "Introduza a descrição do produto:" + ANSI_RESET);
        String desc = Ler.umaString();
        System.out.println(ANSI_BLUE + "Introduza o ID produto:" + ANSI_RESET);
        long id = Ler.umLong();
        do{
            if(findProduto(Produtos,id)){
                System.out.println(ANSI_RED + "Introduza um ID válido!" + ANSI_RESET);
                id = Ler.umLong();
            }
        }while(findProduto(Produtos,id) == true);
        
        Produto novo = new Produto(id,nome,desc,preco);
        Produtos.add(novo);
        System.out.println(ANSI_PURPLE + "Produto guardado com sucesso!" + ANSI_RESET + "\n-----");
    }
    
    //----- MODIFICAR UM PRODUTO CASO EXISTA NA LISTA -----//
    public static void modificarProduto(ArrayList<Produto> Produtos){
        
        int in, j;
        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_GREEN + " MODIFICAR PRODUTO " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Escolha o produto a alterar" + ANSI_RESET);
        listaProdutos(Produtos);
        do {
            j = Ler.umInt();
            if (j > Produtos.size() || j <= 0) {
                System.out.println(ANSI_RED + "Introduza um número válido!" + ANSI_RESET);
            }
        } while (j > Produtos.size() || j <= 0);
        do {
            System.out.println(ANSI_BLUE + "Escolha o campo a alterar:" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "1." + ANSI_RESET + " Alterar o nome do produto");
            System.out.println(ANSI_BLUE + "2." + ANSI_RESET + " Alterar a descrição do produto");
            System.out.println(ANSI_BLUE + "3." + ANSI_RESET + " Alterar o preço do produto");
            
            System.out.println(ANSI_BLUE + "0." + ANSI_RESET + ANSI_RED + " Sair" + ANSI_RESET);
       
            in = Ler.umInt();
            switch (in) {
                case 1: {
                    System.out.println(ANSI_BLUE + "Novo nome:" + ANSI_RESET);
                    Produtos.get(j - 1).setNome(Ler.umaString());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
                case 2: {
                    System.out.println(ANSI_BLUE + "Nova descrição:" + ANSI_RESET);
                    Produtos.get(j - 1).setDescricao(Ler.umaString());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
                case 3: {
                    System.out.println(ANSI_BLUE + "Novo preço:" + ANSI_RESET);
                    Produtos.get(j-1).setPreco(Ler.umDouble());
                    System.out.println(ANSI_PURPLE + "Alteração feita com sucesso!" + ANSI_RESET + "\n-----");
                    break;
                }
               
                case 0:
                    break;
                default:
                    System.out.println(ANSI_RED + "Escolha inválida" + ANSI_RESET);
            }
        } while (in != 0);
    }
    
    
    //----- APAGAR UM PRODUTO CASO EXISA NA LISTA -----//
    public static void apagarProduto(ArrayList<Produto> Produtos){
        
        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_GREEN + " REMOVER PRODUTO " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
        System.out.println("Escolha o cliente que quer eliminar");
        listaProdutos(Produtos);
        int in = Ler.umInt();
        while (in <= 0 || in > Produtos.size()) {
            System.out.println(ANSI_RED + "Introduza um número válido!" + ANSI_RESET);
            in = Ler.umInt();
        }
        System.out.println(ANSI_RED + "Tem a certeza que pretende eliminar o produto " + ANSI_RESET + Produtos.get(in - 1).getNome() + ANSI_RED + " ?" + ANSI_RESET);
        System.out.println(ANSI_RED + "Introduza 'S' para eliminar, outro valor para cancelar." + ANSI_RESET);
        char c = Ler.umChar();
        if (c != 'S' && c != 's') {
            return;
        }
        
        Produtos.remove(in - 1);
        System.out.println(ANSI_PURPLE + "Produto eliminado com sucesso!" + ANSI_RESET + "\n-----");
    }
    
    //----- CONSULTAR INFORMAÇÃO DE UM CLIENTE CASO EXISTA NA LISTA -----//
    public static void ProdutosInfo(ArrayList<Produto> Produtos) {
        
        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_GREEN + " CONSULTAR PRODUTO " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
        
        if (Produtos.isEmpty()) {
            System.out.println(ANSI_RED + "Não há produtos a mostrar." + ANSI_RESET + "\n-----");
            return;
        }
        System.out.println(ANSI_BLUE + "Escolha o produto que pretende ver." + ANSI_RESET);
        listaProdutos(Produtos);
        int in;
        do {
            in = Ler.umInt();
            if (in > Produtos.size() || in < 0) {
                System.out.println(ANSI_RED + "Introduza um número válido!" + ANSI_RESET);
            }
        } while (in > Produtos.size() || in <= 0);
        System.out.println(Produtos.get(in - 1).toString());
    }
    
    //----- IMPRIME O NOME DO PRODUTO MAIS COMPRADO -----//
    public static void produtoMaisComprado(ArrayList<Produto> Produtos, ArrayList<Fatura> Faturas){
        
        int max = 0;
        int size = Produtos.size();
        int[] prods = new int [size];
        
        for(int i=0; i<Produtos.size(); i++){
            for(int j=0; j<Faturas.size(); j++){
                if( Produtos.get(i).getNome().equalsIgnoreCase(Faturas.get(j).getNome()) ){
                    prods[i] = prods[i] + 1;
                }
                    
            }
        }
        
        for(int i=0; i<prods.length; i++){
            if( prods[i] > max)
                max = i;
        }
        
        System.out.println(ANSI_PURPLE + "Produto mais vendido: " + ANSI_RESET + Produtos.get(max).getNome());
    }
    
    //----- IMPRIME O NOME DO PRODUTO MENOS COMPRADO -----//
    public static void produtoMenosComprado(ArrayList<Produto> Produtos, ArrayList<Fatura> Faturas){
        
        int min = 1;
        int size = Produtos.size();
        int[] prods = new int [size];
        
        for(int i=0; i<Produtos.size(); i++){
            for(int j=0; j<Faturas.size(); j++){
                if( Produtos.get(i).getNome().equalsIgnoreCase(Faturas.get(j).getNome()) ){
                    prods[i] = prods[i] + 1;
                }   
            }
        }
        
        for(int i=0; i<prods.length; i++){
            if( prods[i] == 0)
                min = i;
        }
        
        System.out.println(ANSI_PURPLE + "Produto menos vendido: " + ANSI_RESET + Produtos.get(min).getNome());
    }
    
    //----- IMPRIME O NOME DO PRODUTO MAIS CARO -----//
    public static void produtoMaisCaro(ArrayList<Produto> Produtos){
        
        int maxIndex = 0;
        double maxPrice = 0.0;
        
        for(int i=0; i<Produtos.size(); i++){
            if( Produtos.get(i).getPreco() > maxPrice){
                maxPrice = Produtos.get(i).getPreco();
                maxIndex = i;
            }
        }
        
        System.out.println(ANSI_PURPLE + "Produto mais caro: " + ANSI_RESET + Produtos.get(maxIndex).getNome());
    }
    
    //----- IMPRIME O NOME DO PRODUTO MAIS BARATO -----//
    public static void produtoMaisBarato(ArrayList<Produto> Produtos){
        
        int minIndex = 0;
        double minPrice = Produtos.get(minIndex).getPreco();
        
        for(int i=0; i<Produtos.size(); i++){
            if( Produtos.get(i).getPreco() < minPrice){
                minPrice = Produtos.get(i).getPreco();
                minIndex = i;
            }
        }
        
        System.out.println(ANSI_PURPLE + "Produto mais barato: " + ANSI_RESET + Produtos.get(minIndex).getNome());
    }

   
    
}
