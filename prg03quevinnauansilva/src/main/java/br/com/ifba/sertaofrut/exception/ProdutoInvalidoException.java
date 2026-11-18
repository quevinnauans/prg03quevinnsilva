
package br.com.ifba.sertaofrut.exception;

// Lançada quando o produto não atende a uma regra de negócio
public class ProdutoInvalidoException extends RuntimeException {
    
    public ProdutoInvalidoException(String motivo){
        super(motivo);
    }
}
