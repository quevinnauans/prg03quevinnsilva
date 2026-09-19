/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.ifba.usuario.validar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidadorUsuarioTest {

    @Test
    public void cpfValidoComCpfCorretoDeveRetornarTrue() {
        assertTrue(ValidadorUsuario.cpfValido("12345678900"));
    }

    @Test
    public void cpfValidoComCpfVazioDeveRetornarFalse() {
        assertFalse(ValidadorUsuario.cpfValido(""));
    }

    @Test
    public void cpfValidoComLetrasDeveRetornarFalse() {
        assertFalse(ValidadorUsuario.cpfValido("abcdefghijk"));
    }

    @Test
    public void cpfValidoComCpfNuloDeveRetornarFalse() {
        assertFalse(ValidadorUsuario.cpfValido(null));
    }

    @Test
    public void senhaForteComSenhaValidaDeveRetornarTrue() {
        assertTrue(ValidadorUsuario.senhaForte("abc12345"));
    }

    @Test
    public void senhaForteComSenhaCurtaDeveRetornarFalse() {
        assertFalse(ValidadorUsuario.senhaForte("1234567"));
    }

    @Test
    public void senhaForteComSenhaNulaDeveRetornarFalse() {
        assertFalse(ValidadorUsuario.senhaForte(null));
    }

    @Test
    public void camposPreenchidosComTodosPreenchidosDeveRetornarTrue() {
        assertTrue(ValidadorUsuario.camposPreenchidos("Maria", "12345678900"));
    }

    @Test
    public void camposPreenchidosComCampoVazioDeveRetornarFalse() {
        assertFalse(ValidadorUsuario.camposPreenchidos("Maria", ""));
    }

    @Test
    public void camposPreenchidosComCampoNulodeveRetornarFalse() {
        assertFalse(ValidadorUsuario.camposPreenchidos("Maria", null));
    }
}