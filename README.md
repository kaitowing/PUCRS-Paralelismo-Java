# Concorrência com Semáforos em Java

Este repositório contém as implementações dos problemas clássicos de concorrência dos Leitores e Escritores, da Barbearia e dos Filósofos, usando semáforos em Java. As implementações foram feitas como parte de um trabalho da faculdade para demonstrar o uso de semáforos na resolução de problemas de concorrência.

## Problema dos Leitores e Escritores

O problema dos Leitores e Escritores é um exemplo clássico de concorrência em que várias threads tentam acessar um recurso compartilhado. Existem duas categorias de threads: Leitores (que apenas leem) e Escritores (que escrevem no recurso). A implementação usa semáforos para garantir a exclusão mútua entre leitores e escritores.

### Classes Relevantes

- `LeitoresEscritoresDemo`: Implementação do problema dos Leitores e Escritores em Java.
- `LeitoresEscritores`: Classe que fornece a exclusão mútua categórica para leitores e escritores.

## Problema da Barbearia

O problema da Barbearia é outro exemplo clássico de concorrência em que um barbeiro atende vários clientes em uma barbearia. O barbeiro espera que os clientes entrem, corte o cabelo de um cliente por vez e os clientes entram na barbearia ou partem após o corte de cabelo. O uso de semáforos garante a sincronização adequada entre o barbeiro e os clientes.

### Classes Relevantes

- `Barbearia`: Implementação do problema da Barbearia em Java.

## Problema dos Filósofos

O problema dos Filósofos é um exemplo clássico de concorrência em que vários filósofos se sentam em uma mesa redonda e tentam pegar garfos para comer, evitando inanição (starvation). A implementação utiliza semáforos para garantir uma distribuição justa dos garfos entre os filósofos.

### Classes Relevantes

- `Filosofos`: Implementação do problema dos Filósofos em Java.

## Database

O problema do Search-Insert-Delete é um problema de banco de dados que três tipos diferentes de threads têm acesso a uma lista encadeada simples: os "searchers", os "inserters" e os "deleters". Os searchers apenas examinam a lista, portanto, eles podem executar suas operações ao mesmo tempo que outros searchers. Os inserters adicionam novos elementos ao final da lista; essas inserções devem ser exclusivas para evitar que dois inserters adicionem itens ao mesmo tempo. No entanto, um único inserter pode adicionar itens em paralelo com qualquer número de operações de busca. Por fim, os deleters removem elementos de qualquer lugar da lista. Apenas um processo de deletion pode acessar a lista de cada vez, e a operação de deletion também deve ser exclusiva em relação às operações de busca e inserção.

## Como Executar

Para executar os exemplos, você precisará de um ambiente Java configurado em seu computador. Você pode compilar e executar os arquivos Java como faria com qualquer aplicação Java. Certifique-se de que a classe principal seja definida corretamente para cada problema.
