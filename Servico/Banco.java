package SistemaBancario.Servico;

import SistemaBancario.Classes.Cliente;
import SistemaBancario.Classes.Conta;
import SistemaBancario.Excecao.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Banco {
    private ArrayList<Cliente> clientes;
    private ArrayList<Conta> contas;


    public Banco() {
        this.clientes = new ArrayList<>();
        this.contas = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void cadastrarConta(Conta conta) {
        this.contas.add(conta);
    }


    public void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public void listarContas() {
        for (Conta conta : contas) {
            System.out.println(conta);
        }
    }

    public void depositar(double valor, int numeroDaConta) {
        if (valor <= 0) {
            throw new ValorInvalidoException("Valor invalido");
        }
        Conta conta = procurarConta(numeroDaConta);
        conta.setSaldo(conta.getSaldo() + valor);
        Movimentacao movimentacao = new Movimentacao(TipoMovimentacao.DEPOSITO, valor, conta.getSaldo());
        conta.adicionarMovimentacao(movimentacao);
    }

    public void sacar(double valor, int numeroDaConta){
        if (valor <= 0) {
            throw new ValorInvalidoException("Valor invalido");
        }
        Conta conta = procurarConta(numeroDaConta);
        if (valor > conta.getSaldo()) {
            throw new SaldoInsuficienteException("Saldo Insuficiente");
        }
        conta.setSaldo(conta.getSaldo() - valor);
        Movimentacao movimentacao = new Movimentacao(TipoMovimentacao.SAQUE, valor, conta.getSaldo());
        conta.adicionarMovimentacao(movimentacao);
    }

    public void transferir(int numeroContaOrigem, int numeroContaDestino, double valor){
        if (valor <= 0) {
            throw new ValorInvalidoException("Valor invalido");
        }
        if (numeroContaOrigem == numeroContaDestino){
            throw new ContaInvalidaException("Não é possivel transferir para a mesma conta");
        }
       Conta contaOrigem = procurarConta(numeroContaOrigem);
       Conta contaDestino = procurarConta(numeroContaDestino);
        if (valor > contaOrigem.getSaldo()){
            throw new SaldoInsuficienteException("Saldo Insuficiente");
        }
        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
        contaDestino.setSaldo(contaDestino.getSaldo() + valor);
        Movimentacao saida = new Movimentacao(TipoMovimentacao.TRANSFERENCIA_ENVIADA, valor, contaOrigem.getSaldo());
        contaOrigem.adicionarMovimentacao(saida);

        Movimentacao entrada = new Movimentacao(TipoMovimentacao.TRANSFERENCIA_RECEBIDA, valor, contaDestino.getSaldo());
        contaDestino.adicionarMovimentacao(entrada);
    }

    public Cliente procurarCliente(String cpf){
        Cliente clienteEncontrado = null;
        for(Cliente cliente: clientes){
            if(cliente.getCpf().equals(cpf)){
                clienteEncontrado = cliente;
            }
        }
        if(clienteEncontrado == null){
            throw new ClienteNaoEncontradoException("Cliente não localizado!");
        }
        return clienteEncontrado;
    }

    public Conta procurarConta(int numeroConta){
        Conta contaEncontrada = null;
        for(Conta conta: contas){
            if(conta.getNumeroConta() == numeroConta){
                contaEncontrada = conta;
            }
        }
        if(contaEncontrada == null){
            throw new ContaInexistenteException("Conta não encontrada!");
        }
        return contaEncontrada;
    }

    public void salvarClientes(){
        File dados = new  File("Dados");
        boolean existe = dados.mkdir();
        File file = new File(dados, "Cliente.txt");
        try(FileWriter fw = new FileWriter(file)){
            for(Cliente cliente : clientes){
                fw.write(cliente.getNome() + "," + cliente.getCpf());
                fw.write("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void carregarClientes(){
        File dados = new  File("Dados");
        File file = new File(dados, "Cliente.txt");
        if(!file.exists()){
            return;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            this.clientes.clear();
            String linha;
            while ((linha = br.readLine()) != null){
                String[] parte = linha.split(",");
                Cliente cliente = new Cliente(parte[0], parte[1]);
                this.clientes.add(cliente);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void salvarContas(){
        File dados = new  File("Dados");
        boolean existe = dados.mkdir();
        File file = new File(dados, "Conta.txt");
        try(FileWriter fw = new FileWriter(file)){
            for(Conta conta : contas){
                fw.write(conta.getNumeroConta() + "," + conta.getSaldo() + "," + conta.getCliente().getCpf());
                fw.write("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void carregarContas(){
        File dados = new  File("Dados");
        File file = new File(dados, "Conta.txt");
        if(!file.exists()){
            return;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            this.contas.clear();
            String linha;
            while ((linha = br.readLine()) != null){
                String[] parte = linha.split(",");
                String cpf = parte[2];
                int numeroConta = Integer.parseInt(parte[0]);
                double saldo = Double.parseDouble(parte[1]);
                Cliente cliente = procurarCliente(cpf);
                Conta conta = new Conta(numeroConta, saldo, cliente);
                this.contas.add(conta);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void salvarMovimentacoes(){
        File dados = new  File("Dados");
        boolean existe = dados.mkdir();
        File file = new File(dados, "Movimentacoes.txt");
        try(FileWriter fw = new FileWriter(file)){
            for(Conta conta : contas){
                for(Movimentacao movimentacao : conta.getMovimentacoes()){
                    fw.write(conta.getNumeroConta() + "," + movimentacao.getDataHora() + "," + movimentacao.getTipo() + "," + movimentacao.getValor() + "," + movimentacao.getSaldoAtual());
                    fw.write("\n");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void carregarMovimentacoes(){
        File dados = new  File("Dados");
        File file = new File(dados, "Movimentacoes.txt");
        if(!file.exists()){
            return;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String linha;
            while ((linha = br.readLine()) != null){
                String[] parte = linha.split(",");
                int numeroConta = Integer.parseInt(parte[0]);
                LocalDateTime dataHora = LocalDateTime.parse(parte[1]);
                TipoMovimentacao tipo = TipoMovimentacao.valueOf(parte[2]);
                double valor = Double.parseDouble(parte[3]);
                double saldoAtual = Double.parseDouble(parte[4]);
                Conta conta = procurarConta(numeroConta);
                Movimentacao movimentacao = new Movimentacao(dataHora, tipo, valor, saldoAtual);
                conta.adicionarMovimentacao(movimentacao);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
