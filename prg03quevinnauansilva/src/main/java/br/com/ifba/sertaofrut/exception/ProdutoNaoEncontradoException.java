
package br.com.ifba.sertaofrut.exception;

// lançada quando busca um produto que não existe no banco
public class ProdutoNaoEncontradoException extends RuntimeException {
    
    public ProdutoNaoEncontradoException(Long id){
        super("Produto com id " + id + "não foi encontrado.");
    }
}
