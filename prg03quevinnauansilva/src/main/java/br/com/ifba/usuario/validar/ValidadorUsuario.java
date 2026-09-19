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
        String[] palavras_proibida = {"admin", "teste", "root", "senha123"};
        
        // Percorre o array verificando o texto
        for(String palavra : palavras_proibida){
            if(text.contains(palavra)){
                return true;
            }
        }
        return false;
    }
    // Verifica se o CPF tem o formato válido
    public static boolean cpfValido(String cpf){
        if(cpf == null){
            return false;
        }
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        return cpfLimpo.length() == 11;
    }
    
    // Verifica se a senha tem o tamanho minimo (8 caracteres)
    public static boolean senhaForte(String senha){
        if(senha == null){
            return false;
        }
        return senha.length() >= 8;
    }
    
    // Verifica se todos os campos foram preechidos
    public static boolean camposPreenchidos(String... campos){
        for(String campo : campos){
            if(campo == null || campo.trim().isEmpty()){
                return false;
            }
        }
        return true;
    }
}
