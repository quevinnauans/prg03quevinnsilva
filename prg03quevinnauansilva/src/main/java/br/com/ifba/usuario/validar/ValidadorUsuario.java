/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.validar;

/**
 *
 * @author Quevin Nauan
 */
public class ValidadorUsuario {
    
    // Verifica se o texto que foi informado contem alguma palavra proibida
    public static boolean contemPalavraProibida(String text){
        //Palavras proibidas
        String[] palavras_proibida = {"admin", "teste", "root", "senha123"
         ,"1234", "senha"};
        
        // Percorre o array verificando o texto
        for(String palavra : palavras_proibida){
            if(text.contains(palavra)){
                return true;
            }
        }
        return false;
    }
}
