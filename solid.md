# Auditoria SOLID

## 1. Exemplos de cada princípio

### S - Responsabilidade única
- Arquivo: ProdutoService.java, (linhas 10, 55, 66)
- Por quê: A classe é responsável por cuidar das regras de negócio do produto, não fala com a tela e nem com o banco.

### O - Aberto/fechado
- Arquivo: MovimentacaoEstoque.java (linhas 9 e 47)
EntradaEstoque.java, (linha 13)
SaidaEstoque, (linha 13)
GerenciadorEstoque.java (linha 9)
- Por quê: o sistema permite adicionar novos tipos de movimentação, como por exemplo (PerdaEstoque), por meio da criação de uma nova subclasse, sem precisar modificar o método responsável pelo processamento.

### L - Substituição de Liskov
- Arquivo: FakeProdutoDao.java, (Linhas 10, 16, 66)
- Por quê: O FakeProdutoDao pode substituir a implementação real do DAO nos testes, mantendo o funcionamento esperado pelo ProdutoService.

### I - Segregação de interfaces
- Arquivo: Autenticavel.java (linha 5)
ProdutoIDao.java (linhas 11 e 14)
- Por quê: As interfaces possuem responsabilidades específicas e disponiblizando apenas os métodos necessários para cada finalidade.

### D - Inversão de dependência
- Arquivo: ProdutoService.java, (linhas 12 e 15)
- Por quê: O ProdutoService recebe uma implementação de ProdutoIDao por meio do construtor, dependendo de uma abstração.

## 2. Violações encontradas

### Violação 1: JPAUtil (princípio S)
- Arquivo: JPAUtil.java, (linhas 21, 53)
- O que acontece: a classe lê o persistence.xml, roda as migrations e fornece o EntityManager.
- Por que viola: A classe concentra diferentes responsabilidades em um único lugar. Cada uma dessas tarefas pode precisar de alterações por motivos diferentes.

### Violação 2: ValidadorUsuario (princípio O)
- Arquivo: ValidadorUsuario.java (linhas 9 e 11)
- O que acontece: O método contemPalavraProibida() possui uma lista fixa de palavras proibidas dentro de sua implementação.
- Por que viola: Para adicionar ou remover alguma das palavras proibidas, é preciso modificar diretamente o método.