/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.ifba.usuario.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void autenticar_comCredenciaisCorretas_deveRetornarTrue() {
        Usuario usuario = new Usuario("Quevin", "12345678900", "quevin.silva", "SENHA123");
        assertTrue(usuario.autenticar("quevin.silva", "SENHA123"));
    }

    @Test
    public void autenticar_comSenhaIncorreta_deveRetornarFalse() {
        Usuario usuario = new Usuario("Quevin", "12345678900", "quevin.silva", "SENHA123");
        assertFalse(usuario.autenticar("quevin.silva", "senhaErrada"));
    }
}
