
package br.com.ifba.usuario.repositorio;

import br.com.ifba.usuario.entity.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// guarda os usuarios cadastrados em memoria
public class RepositorioUsuarioEmMemoria {
    // Lista para os usuários cadastrados durante execução
    private final List<Usuario> usuarios = new ArrayList<>();
    private final Map<String, Usuario> porLogin = new HashMap<>();
    
    // Cadastra um usuários no repositorio
    public void cadastrar(Usuario usuario){
        
        // bloqueia o login duplicado
        if(porLogin.containsKey(usuario.getLogin())){
            throw new IllegalArgumentException("Já existe um usuário com esse login");
        }
        
        usuarios.add(usuario);
        porLogin.put(usuario.getLogin(), usuario);
    }
    
    // Retorna a lista dos usuarios cadastrados
    public List<Usuario> listarTodos(){
        return Collections.unmodifiableList(usuarios);
    }
    
    // busca percorrendo a lista
    public Usuario buscarPorLoginUsandoFor(String login){
        for(Usuario usuario : usuarios){
            if(usuario.getLogin().equals(login)){
                return usuario;
            }
        }
        return null;
    }
    
    // busca usando o Map
    public Usuario buscarPorLogin(String login){
        return porLogin.get(login);
    }
}
