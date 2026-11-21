
package br.com.ifba.usuario.validar;

import java.util.List;

public class ValidadorUsuario {
    
    private static final List<String> PALAVRAS_PROIBIDAS_PADRAO =
        List.of("admin", "teste", "root", "senha123", "1234", "senha");

public static boolean contemPalavraProibida(String texto) {
    return contemPalavraProibida(texto, PALAVRAS_PROIBIDAS_PADRAO);
}

// quem precisar de outra lista usa esta versao, sem editar o metodo
public static boolean contemPalavraProibida(String texto, List<String> proibidas) {
    for (String palavra : proibidas) {
        if (texto.toLowerCase().contains(palavra)) {
            return true;
        }
    }
    return false;
}
    public static boolean cpfContemOnzeDigitos(String cpf){
        if(cpf == null){
            return false;
        }
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        return cpfLimpo.length() == 11;
    }
    
    public static boolean senhaForte(String senha){
        if(senha == null){
            return false;
        }
        return senha.length() >= 8;
    }
    
    public static boolean camposPreenchidos(String... campos){
        for(String campo : campos){
            if(campo == null || campo.trim().isEmpty()){
                return false;
            }
        }
        return true;
    }
}
