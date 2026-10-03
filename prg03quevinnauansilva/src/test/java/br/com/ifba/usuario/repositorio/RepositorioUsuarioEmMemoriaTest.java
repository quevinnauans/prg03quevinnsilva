package br.com.ifba.usuario.repositorio;

import br.com.ifba.usuario.entity.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RepositorioUsuarioEmMemoriaTest {

    @Test
    public void cadastrar_deveAparecerEmListarTodos() {
        RepositorioUsuarioEmMemoria repositorio = new RepositorioUsuarioEmMemoria();
        Usuario usuario = new Usuario("Quevin", "11111111111", "quevin.silva", "senha123");
        repositorio.cadastrar(usuario);
        assertTrue(repositorio.listarTodos().contains(usuario));
    }

    @Test
    public void buscarPorLogin_comDoisUsuarios_deveDevolverOCorreto() {
        RepositorioUsuarioEmMemoria repositorio = new RepositorioUsuarioEmMemoria();
        Usuario quevin = new Usuario("Quevin", "11111111111", "quevin.silva", "senha123");
        Usuario maria = new Usuario("Maria", "22222222222", "maria.souza", "senha456");
        repositorio.cadastrar(quevin);
        repositorio.cadastrar(maria);
        assertEquals(maria, repositorio.buscarPorLogin("maria.souza"));
    }

    @Test
    public void buscarPorLogin_comLoginInexistente_deveDevolverNull() {
        RepositorioUsuarioEmMemoria repositorio = new RepositorioUsuarioEmMemoria();
        assertNull(repositorio.buscarPorLogin("naoexiste"));
    }

    @Test
    public void cadastrar_comLoginDuplicado_deveLancarExcecao() {
        RepositorioUsuarioEmMemoria repositorio = new RepositorioUsuarioEmMemoria();
        Usuario u1 = new Usuario("Quevin", "11111111111", "quevin.silva", "senha123");
        Usuario u2 = new Usuario("Quevin Nauan", "22222222222", "quevin.silva", "outraSenha");
        repositorio.cadastrar(u1);
        assertThrows(IllegalArgumentException.class, () -> repositorio.cadastrar(u2));
    }

    @Test
    public void equals_doisObjetosDiferentesComMesmoLogin_devemSerIguaisParaALista() {
        Usuario u1 = new Usuario("Quevin", "11111111111", "quevin.silva", "senha123");
        Usuario u2 = new Usuario("Quevin Nauan", "22222222222", "quevin.silva", "outraSenha");
        assertEquals(u1, u2);
    }
}