
package br.com.ifba.infrastructure.exception;

// Lancada quando uma operacao com o banco falha
public class FalhaDePersistenciaException extends RuntimeException {
    
    // falha de persistencia
    public FalhaDePersistenciaException(String operacao, Throwable causa){
        super("Falha ao " + operacao + ": " + causa.getMessage(), causa);
    }
}
