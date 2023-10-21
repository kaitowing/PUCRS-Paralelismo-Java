# Concorrência com Semáforos em Java

Este repositório contém as implementações dos problemas clássicos de concorrência dos Leitores e Escritores e da Barbearia, usando semáforos em Java. As implementações foram feitas como parte de um trabalho da faculdade para demonstrar o uso de semáforos na resolução de problemas de concorrência.

## Problema dos Leitores e Escritores

O problema dos Leitores e Escritores é um exemplo clássico de concorrência em que várias threads tentam acessar um recurso compartilhado. Existem duas categorias de threads: Leitores (que apenas leem) e Escritores (que escrevem no recurso). A implementação usa semáforos para garantir a exclusão mútua entre leitores e escritores.

### Classes Relevantes

- `LeitoresEscritoresDemo`: Implementação do problema dos Leitores e Escritores em Java.
- `LeitoresEscritores`: Classe que fornece a exclusão mútua categórica para leitores e escritores.

## Problema da Barbearia

O problema da Barbearia é outro exemplo clássico de concorrência em que um barbeiro atende vários clientes em uma barbearia. O barbeiro espera que os clientes entrem, corte o cabelo de um cliente por vez e os clientes entram na barbearia ou partem após o corte de cabelo. O uso de semáforos garante a sincronização adequada entre o barbeiro e os clientes.

### Classes Relevantes

- `Barbearia`: Implementação do problema da Barbearia em Java.

## Como Executar

Para executar os exemplos, você precisará de um ambiente Java configurado em seu computador. Você pode compilar e executar os arquivos Java como faria com qualquer aplicação Java. Certifique-se de que a classe principal seja definida corretamente para cada problema.