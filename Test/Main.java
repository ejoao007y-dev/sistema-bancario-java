package SistemaBancario.Test;

import SistemaBancario.Classes.Cliente;
import SistemaBancario.Classes.Conta;
import SistemaBancario.Excecao.*;
import SistemaBancario.Servico.Banco;
import SistemaBancario.Servico.Movimentacao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void mostrarMenu() {
        System.out.println(" ");
        System.out.println("====== BANCO ======");
        System.out.println(" ");
        System.out.println("1- Cadastrar Cliente");
        System.out.println("2- Cadastrar Conta");
        System.out.println("3- Listar Cliente");
        System.out.println("4- Listar Conta");
        System.out.println("5- Depositar");
        System.out.println("6- Sacar");
        System.out.println("7- Transferir");
        System.out.println("8- Buscar Conta");
        System.out.println("9- Buscar Cliente");
        System.out.println("10- Ver histórico");
        System.out.println("11- Sair");
        System.out.println(" ");
        System.out.println("Escolha uma opção: ");
    }

    public static int lerOpcao(Scanner sc) {
        int opcao;
        try {
            opcao = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Digite uma opção válida!");
            sc.nextLine();
            return -1;
        }
        return opcao;
    }

    public static Cliente lerCliente(Scanner sc) {
        System.out.println("Nome: ");
        String nome = sc.nextLine();

        System.out.println("CPF: ");
        String cpf = sc.nextLine();

        Cliente cliente = new Cliente(nome, cpf);
        return cliente;
    }

    public static Conta lerConta(Scanner sc, Banco banco) {
        System.out.println("Numero Conta: ");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        System.out.println("CPF do cliente: ");
        String cpf = sc.nextLine();

        Cliente cliente = banco.procurarCliente(cpf);
        Conta conta = new Conta(numeroConta, 0, cliente);
        return conta;
    }

    public static void lerTransferencia(Scanner sc, Banco banco) {
        System.out.println("Digite o numero da conta Origem: ");
        int numeroContaOrigem = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o numero da conta Destino: ");
        int numeroContaDestino = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o valor da transferencia: ");
        double valorTransferencia = sc.nextDouble();
        sc.nextLine();

        banco.transferir(numeroContaOrigem, numeroContaDestino, valorTransferencia);
    }

    public static void lerDeposito(Scanner sc, Banco banco) {
        System.out.println("Numero da conta: ");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        System.out.println("Valor do deposito: ");
        double valorDeposito = sc.nextDouble();
        sc.nextLine();

        banco.depositar(valorDeposito, numeroConta);
    }

    public static void lerSaque(Scanner sc, Banco banco) {
        System.out.println("Numero da conta: ");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        System.out.println("Valor do Saque: ");
        double valorSaque = sc.nextDouble();
        sc.nextLine();

        banco.sacar(valorSaque, numeroConta);
    }

    public static void buscarConta(Scanner sc, Banco banco) throws ContaInexistenteException {
        System.out.println("Numero da conta: ");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        Conta conta = banco.procurarConta(numeroConta);
        System.out.println(conta);
    }

    public static void buscarCliente(Scanner sc, Banco banco) throws ClienteNaoEncontradoException {
        System.out.println("CPF  do cliente: ");
        String cpf = sc.nextLine();

        Cliente cliente = banco.procurarCliente(cpf);
        System.out.println(cliente);
    }

    public static void lerExtrato(Scanner sc, Banco banco) {
        System.out.println("Numero da conta: ");
        int numeroConta = sc.nextInt();
        sc.nextLine();

        Conta conta = banco.procurarConta(numeroConta);

        for (Movimentacao movimentacao : conta.getMovimentacoes()) {
            System.out.println(movimentacao);
        }
    }

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();

        banco.carregarClientes();
        banco.carregarContas();
        banco.carregarMovimentacoes();

        boolean continuar = true;

        while (continuar) {

            mostrarMenu();
            int opcao = lerOpcao(sc);
            switch (opcao) {
                case 1:
                    banco.cadastrarCliente(lerCliente(sc));
                    System.out.println("\nCliente cadastrado com sucesso!\n");
                    break;
                case 2:
                    banco.cadastrarConta(lerConta(sc, banco));
                    System.out.println("\nConta cadastrada com sucesso!\n");
                    break;
                case 3:
                    banco.listarClientes();
                    break;
                case 4:
                    banco.listarContas();
                    break;
                case 5:
                    try {
                        lerDeposito(sc, banco);
                        System.out.println("\nDepósito realizado com sucesso!\n");
                    } catch (ValorInvalidoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (ContaInexistenteException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        lerSaque(sc, banco);
                        System.out.println("\nSaque realizado com sucesso!\n");
                    } catch (ValorInvalidoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (ContaInexistenteException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (SaldoInsuficienteException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        lerTransferencia(sc, banco);
                        System.out.println("\nTrasferência realizada com sucesso!\n");
                    } catch (ValorInvalidoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (ContaInvalidaException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (ContaInexistenteException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (SaldoInsuficienteException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        buscarConta(sc, banco);
                    } catch (ContaInexistenteException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 9:
                    try {
                        buscarCliente(sc, banco);
                    } catch (ClienteNaoEncontradoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 10:
                    try {
                        lerExtrato(sc, banco);
                    } catch (ContaInexistenteException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 11:
                    banco.salvarClientes();
                    banco.salvarContas();
                    banco.salvarMovimentacoes();
                    continuar = false;
                    System.out.println("Dados salvos com sucesso!");
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção invalida!");
            }
        }
    }
}
