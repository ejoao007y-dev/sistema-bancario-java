package SistemaBancario.Excecao;

public class ContaInvalidaException extends RuntimeException {
    public ContaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
