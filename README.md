# Sistema Bancário em Java

Sistema bancário desenvolvido em **Java** com foco na prática de **Programação Orientada a Objetos (POO)**, persistência de dados em arquivo e tratamento de exceções.

## 📖 Sobre o Projeto
O sistema permite o gerenciamento de clientes, contas bancárias e movimentações financeiras, simulando operações comuns de um banco.
Todos os dados são armazenados em arquivos `.txt`, permitindo que as informações sejam preservadas entre execuções do programa.

---

## 🚀 Funcionalidades
### 🙍‍♂️ Clientes
- Cadastro de clientes
- Busca de cliente por CPF
- Listagem de clientes
### 🏛 Contas
- Cadastro de contas bancárias
- Busca de conta pelo número
- Listagem de contas
- Associação entre clientes e contas
### 💰 Operações Bancárias
- Depósito
- Saque
- Trasnferência entre contas
### 📄 Extrato
- Histórico completo das movimentações
- Registro de:
  - Depósitos
  - Saques
  - Transferências enviadas
  - Transferências recebidas
- Exibição do saldo atualizado após cada operação
### 💾 Persistência de Dados
O sistema salva e carrega automaticamente: 

- Clientes
- Contas
- Movimentações

---
## ⚙ Tecnologias utilizadas

- Java
- Programação Orientada a Objetos
- Collections (`ArrayList`)
- Enum
- Exceções Personalizadas
- LocalDateTime
- DateTimeFormatter
- NumberFormat
- Persistência utilizando arquivos `.txt`

---
## 📂 Estrutura do projeto
```text
src
  classe
    Cliente
    Conta
  Exceção
    ClienteNaoEncontradoException
    ContaInexistenteException
    ContaInvalidaException
    SaldoInsuficienteException
    ValorInvalidoException
  Serviço
    Banco
    Formatador
    Movimentação
    TipoMovimentação
  Test
    Main
```

---
## 📁 Arquivos Gerados
Durante a execução do sistema são criados os arquivos:

```text
clientes.txt
contas.txt
movimentacoes.txt
```

Esses arquivos armazenam todos os dados do sistema.

---
## ▶ Como executar
1. Clone o repositório
```bash
git clone <https://github.com/ejoao007y-dev/sistema-bancario-java>
```
2. Abra o projeto em uma IDE Java
3. Execute a classe `Main`.

 ---
 ## 📚 Conceitos praticados

 - Encapsulamento
 - Associação entre classes
 - Sobrecarga de construtores
 - Tratamento de exceções
 - Persistência em arquivos
 - Organização em camadas
 - Manipulação de datas
 - Formatação de moeda
 - Enum
 - Coleções (`ArrayList`)
 - Leitura e escrita de arquivos

---
## Melhorias Futuras

- Conta Corrente e Conta Poupança (Herança)
- Polimorfismo

---
## 👨‍💻 Autor

Desenvolvido por **João Evangelista** como projeto de estudos em Java e Programação Orientada a Objetos.

