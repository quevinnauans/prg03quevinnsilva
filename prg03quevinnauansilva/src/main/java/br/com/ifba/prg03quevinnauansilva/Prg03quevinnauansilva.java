/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.ifba.prg03quevinnauansilva;

import br.com.ifba.usuario.entity.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Quevin Nauan
 */
public class Prg03quevinnauansilva {

    public static void main(String[] args) {
        
        // Cria dois usuarios usando o mesmo login
        Usuario u1 = new Usuario("Quevin", "11111111111", "quevin.silva", "senha123");
        Usuario u2 = new Usuario("Quevin Silva", "22222222222", "quevin.silva", "outraSenha");
        
        // Cria uma lista e adiciona o primeiro usuario
        List<Usuario> lista = new ArrayList<>();
        lista.add(u1);
        
        // Verifica se a lista considera os dois usuarios iguais
        System.out.println(lista.contains(u2));
    }
}
