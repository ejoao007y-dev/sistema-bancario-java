package SistemaBancario.Excecao;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException(String mensagem) {
        super(mensagem);
    }
}
