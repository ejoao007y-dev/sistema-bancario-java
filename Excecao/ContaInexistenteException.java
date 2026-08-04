package SistemaBancario.Excecao;

public class ContaInexistenteException extends RuntimeException {
    public ContaInexistenteException(String mensagem) {
        super(mensagem);
    }
}
