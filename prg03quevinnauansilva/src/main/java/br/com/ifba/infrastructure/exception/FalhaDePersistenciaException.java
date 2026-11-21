
package br.com.ifba.infrastructure.exception;

// Lancada quando uma operacao com o banco falha
public class FalhaDePersistenciaException extends RuntimeException {
   
    public FalhaDePersistenciaException(String operacao, Throwable causa){
        super("Falha ao " + operacao + ": " + causa.getMessage(), causa);
    }
}
