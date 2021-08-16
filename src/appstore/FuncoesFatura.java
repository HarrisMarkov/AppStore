/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appstore;

import java.io.File;
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
import static appstore.Cores.*;
import java.time.LocalDateTime;

/**
 *
 * @author harris
 */
public class FuncoesFatura {


    
    //Ler e escrever ficheiro de faturas
    
    public static Object lerFicheiroFaturas(String fname) throws FileNotFoundException, IOException{
        ArrayList<Fatura> Faturas = new ArrayList<Fatura>();
        try{
            FileInputStream is = new FileInputStream (fname);
            ObjectInputStream oIS = new ObjectInputStream(is);
            Faturas= (ArrayList<Fatura>)oIS.readObject();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncoesFatura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Faturas;
    }
    
    public static void escreverFicheiroFaturas(ArrayList<Fatura> Faturas, String fname) throws FileNotFoundException{
        try{
            FileOutputStream os = new FileOutputStream(fname);
            ObjectOutputStream oOS = new ObjectOutputStream(os);
            oOS.writeObject( Faturas );
            oOS.close();
            }
        catch (IOException e){
                System.out.println(e.toString());
            }
    }
    
    //----- INSERIR NOVO PRODUTO NA FATURA -----//
    public static void inserirNovoProdutoFatura(Fatura f, Produto p){
        
        f.getProdutos().add(p);
    }
    
    //---- ENCONTRA UM PRODUTO SE EXISTIR NA FATURA -----//
    public boolean findProduto(Fatura f, int ID) {
        
        for (int i = 0; i < f.getProdutos().size(); i++)
            if (f.getProdutos().get(i).getId() == ID)
                return true;
            
        return false;
    }

    //----- LISTA OS PRODUTOS NA FATURA -----//
    public static void listaProdutos(Fatura f) {

        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_GREEN + " LISTA DE FATURAS " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
        
        
        for (int i = 0; i < f.getProdutos().size(); i++) {
            System.out.println(ANSI_BLUE + (i + 1) + ". "  + ANSI_RESET + f.getProdutos().get(i).getNome());
        }
    }
    
    //----- LISTA TODAS AS FATURAS EXISTENTES -----//
    public static void listaFaturas(ArrayList<Fatura> Faturas) {

        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_GREEN + " LISTA DE FATURAS " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
        
        for (int i = 0; i < Faturas.size() ; i++) {
            System.out.println(ANSI_BLUE + (i + 1) + ". "  + ANSI_RESET + Faturas.get(i).toString());
        }
    }
    
    //----- APAGA UM PRODUTO CASO ESTE EXISTA NA FATURA -----//
    public void apagarProduto(Fatura f) {
        
        System.out.println(ANSI_BLUE + "#####" + ANSI_RESET + ANSI_GREEN + " REMOVER PRODUTO DA FATURA " + ANSI_RESET + ANSI_BLUE + "#####" + ANSI_RESET);
        
        System.out.println(ANSI_BLUE + "Escolha o produto a remover. ('0' para cancelar)" + ANSI_RESET);
        for (int i = 0; i < f.getProdutos().size(); i++) {
            System.out.println(ANSI_BLUE + (i + 1) + ". " + ANSI_RESET + f.getProdutos().get(i));
        }
        int in;
        do {
            in = Ler.umInt();
            if (in == 0) {
                return;
            }
            if (in < 0 || in > f.getProdutos().size()) {
                System.out.println(ANSI_RED + "Escolha uma opção válida." + ANSI_RESET);
            }
        } while (in < 0 || in > f.getProdutos().size());
        System.out.println(ANSI_RED + "Tem a certeza que pretende eliminar o produto " + ANSI_RESET + f.getProdutos().get(in - 1).getNome() + " ?");
        System.out.println(ANSI_RED + "Introduza 'S' para eliminar, outro valor para cancelar." + ANSI_RESET);
        char c = Ler.umChar();
        if (c != 'S' && c != 's') {
            return;
        }
        
        f.getProdutos().remove(in - 1);
        System.out.println(ANSI_PURPLE + "Produto eliminado com sucesso!" + ANSI_RESET + "\n-----");
    }
    
    public static void PesquisaFatura(ArrayList<Fatura> Faturas){
            int in,i, id, flag=0;
            long nif;
            do {
                System.out.println(ANSI_BLUE + "Pesquisa de Faturas:" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "1." + ANSI_RESET + " Pesquisa por ID" );
                System.out.println(ANSI_BLUE + "2." + ANSI_RESET + " Pesquisa por NIF" );
                
                System.out.println(ANSI_RED + "0. Sair" + ANSI_RESET);
                do{
                    in=Ler.umInt();
                    if (in>=Faturas.size() || in<0)
                         System.out.println(ANSI_RED + "Introduza um número válido." + ANSI_RESET);
                }while(in>3 || in<0);
                
                switch (in) {
                    case 1:
                        System.out.print(ANSI_BLUE +"ID a pesquisar:" + ANSI_RESET);
                        id=Ler.umInt();
                    
                        for(i=0;i<Faturas.size();i++){
                            if(Faturas.get(i).getId() == id){
                                System.out.println(Faturas.get(i));
                                flag=1;
                            }
                        }
                        if(flag == 0)
                            System.out.println(ANSI_RED + "Não existe nenhuma Fatura com ID = " + id + ANSI_RESET);
                        
                        break;
                
                    case 2:
                        System.out.print(ANSI_BLUE +"NIF a pesquisar:" + ANSI_RESET);
                        nif = Ler.umInt();
                        
                        for(i=0;i<Faturas.size();i++){
                            if(Faturas.get(i).getNif() == nif){
                                System.out.println(Faturas.get(i));
                                flag=1;
                            }
                        }
                        if(flag == 0)
                            System.out.println(ANSI_RED + "Não existe nenhuma Fatura com NIF = " + nif + ANSI_RESET);
                        
                        break;
                
                    case 0:
                        break;
                    default:
                        System.out.println(ANSI_RED + "Escolha uma opção válida." + ANSI_RESET);
                }
            } while (in !=0);
    
    }
    
    
    
}
