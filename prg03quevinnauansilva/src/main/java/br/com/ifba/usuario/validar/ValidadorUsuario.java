
package br.com.ifba.usuario.validar;

import java.util.Locale;

public class ValidadorUsuario {
    
    // Verifica se o texto que foi informado contem alguma palavra proibida
    public static boolean contemPalavraProibida(String text){
    // Palavras proibidas
    String[] palavras_proibida = {"admin", "teste", "root", "senha123"};

    // Deixa o texto em minúsculo para a comparação
    String textoNormalizado = text.toLowerCase(Locale.ROOT);

    // Percorre o array verificando o texto
    for(String palavra : palavras_proibida){
        if(textoNormalizado.contains(palavra)){
            return true;
        }
    }
    return false;
}
    // Verifica se o CPF tem a quantidade de digitos correta
    public static boolean cpfContemOnzeDigitos(String cpf){
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
