# Retrospectiva - Checkpoint 1

1. A revisão de código ajudou a identificar alguns pontos de melhoria nas validações e no encapsulamento do projeto.
2. Corrigi a validação de quantidade para impedir valores inválidos e saídas maiores que o estoque disponível.
3. Também ajustei a validação de palavras proibidas para não depender de letras maiúsculas ou minúsculas.
4. No CPF, mantive a validação apenas dos 11 dígitos, mas deixei o nome do método mais claro.
5. Na revisão do código do colega, aprendi a observar melhor as regras de negócio, validações e o encapsulamento.

## Atividade 16 - Pergunta do DTO

Numa tela de listagem não deveriam aparecer o `id` a chave interna do banco, sem significado para quem usa o sistema e também a lista `movimentacoes` histórico grande, que não cabe numa linha da tabela e deixaria a listagem mais pesada.