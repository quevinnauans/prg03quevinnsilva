
package br.com.ifba.usuario.repositorio;

import br.com.ifba.usuario.entity.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// guarda os usuarios cadastrados em memoria
public class RepositorioUsuarioEmMemoria {
    // Lista para os usuários cadastrados durante execução
    private final List<Usuario> usuarios = new ArrayList<>();
    
    // Cadastra um usuários no repositorio
    public void cadastrar(Usuario usuario){
        usuarios.add(usuario);
    }
    
    // Retorna a lista dos usuarios cadastrados
    public List<Usuario> listarTodos(){
        return Collections.unmodifiableList(usuarios);
    }
}
