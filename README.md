## Estrutura do Projeto

```
ATIVIDADES/
├── ATIVIDADE 01/
│   ├── documento-requisitos-sertaofrut.md
│   └── diagrama-casos-de-uso.svg
├── ATIVIDADE 2 - LEARNGIT/
│   └── print.png
└── ATIVIDADE 03 - LEARNGIT/
    └── print.png
```

---

# Documento de Especificação de Requisitos

**Projeto:** Sistema de Controle de Produção e Estoque — SertãoFrut
**Autor:** Quevin
**Disciplina:** PRG03 — POO
**Revisão:** 02

---

## 1. Introdução

### 1.1 Objetivo
Este documento tem como objetivo apresentar os requisitos funcionais e não funcionais do sistema de controle de produção e estoque da SertãoFrut, indústria de polpas de frutas. Atualmente esse controle é feito manualmente em planilha; o sistema visa reduzir o trabalho manual do proprietário, evitando retrabalho e liberando tempo para que ele possa se dedicar mais às vendas e aos clientes.

### 1.2 Escopo do produto
O escopo deste documento abrange o cadastro de produtos (polpas), o registro de produções (fruta utilizada, polpa gerada e perdas) e o controle de estoque (entradas geradas pela produção e saídas por quantidade).

Este documento **não** contempla controle de vendas, clientes ou valores financeiros — a saída de estoque é registrada apenas por quantidade, produto e data. Também não fazem parte do escopo o controle de fornecedores/compra de matéria-prima e o estoque de embalagens.

> O proprietário relatou que a parte de vendas também é uma dificuldade em seu dia a dia. Essa necessidade fica registrada como um possível **próximo incremento** do sistema, a ser avaliado em versões futuras do documento.

### 1.3 Definições, Acrônimos e Abreviações
| Nº | Sigla | Definição |
|----|-------|-----------|
| 01 | RF | Requisito Funcional |
| 02 | RNF | Requisito Não-Funcional |
| 03 | RN | Regra de Negócio |

---

## 2. Descrição geral

### 2.1 Modo de Operação
O sistema opera como uma ferramenta de apoio à rotina de produção da SertãoFrut. O processo real, do recebimento da fruta ao produto pronto, segue as etapas: recebimento da fruta → lavagem → higienização → corte/preparo → despolpamento → envaze → selagem → congelamento. A partir da produção realizada, o estoque de polpas é atualizado; e o estoque é reduzido a cada saída registrada (venda ou amostra para divulgação).

### 2.2 Funções do produto
| Nº | Função | Descrição |
|----|--------|-----------|
| 01 | Cadastrar produto | Cadastrar as polpas produzidas pela indústria. |
| 02 | Registrar produção | Registrar uma produção, informando fruta utilizada, quantidade processada, quantidade produzida e perda. |
| 03 | Atualizar estoque | Atualizar a quantidade em estoque de uma polpa a partir de uma produção registrada. |
| 04 | Registrar saída de estoque | Registrar uma saída de estoque, informando produto, quantidade e data. |
| 05 | Consultar estoque | Consultar a quantidade em estoque de cada polpa. |
| 06 | Definir estoque mínimo | Permitir configurar uma quantidade mínima por produto. |
| 07 | Consultar histórico de produções | Permitir visualizar as produções já registradas. |
| 08 | Listar estoque baixo | Permitir visualizar os produtos com estoque abaixo do mínimo. |

### 2.3 Usuários e sistemas externos
| Nº | Usuário | Descrição |
|----|---------|-----------|
| 01 | Proprietário | Único operador do sistema atualmente; registra produção, atualiza estoque e registra saídas. |

### 2.4 Restrições
| Nº | Tipo | Descrição |
|----|------|-----------|
| 01 | Processo | A quantidade de fruta processada já é registrada líquida de perda, que é descontada na etapa de chegada da fruta, antes da produção. |
| 02 | Processo | Uma produção normalmente utiliza apenas uma fruta por vez, devido ao número reduzido de funcionários. |
| 03 | Produto | Todas as polpas são padronizadas em pacotes de 100g, com validade igual entre as frutas. |

### 2.5 Requisitos Adiados
| Nº | Referência | Detalhes |
|----|------------|----------|
| 01 | Módulo de Vendas e Clientes | Registro de vendas com cliente e valor — dificuldade real relatada pelo proprietário, mas fora do escopo desta versão. |
| 02 | Estoque de Embalagens | As embalagens possuem estoque próprio, separado do estoque de polpas; não contemplado nesta versão. |
| 03 | Produção com múltiplas frutas | Hoje não ocorre, mas pode ser necessário caso a equipe e a demanda cresçam. |

---

## 3. Requisitos específicos

### 3.1 Requisitos Funcionais
| Código | Nome | Descrição |
|--------|------|-----------|
| RF01 | Cadastro de Produto | O sistema deve permitir cadastrar uma polpa, informando nome (fruta), sabor (opcional), preço e peso do pacote (100g fixo). O sistema deve gerar um código para cada polpa, facilitando sua localização no estoque. |
| RF02 | Registro de Produção | O sistema deve permitir registrar uma produção, informando data, fruta utilizada, quantidade de fruta processada (já líquida de perda) e quantidade de polpa produzida. |
| RF03 | Atualização de Estoque | O sistema deve permitir atualizar a quantidade em estoque de uma polpa a partir dos dados de uma produção registrada. |
| RF04 | Registro de Saída de Estoque | O sistema deve permitir registrar uma saída de estoque, informando produto, quantidade e data. Opcionalmente, o motivo da saída pode ser informado (ex.: venda ou amostra para divulgação), sem detalhar cliente ou valor. |
| RF05 | Consulta de Estoque | O sistema deve permitir consultar a quantidade em estoque de cada polpa. |
| RF06 | Definição de Estoque Mínimo | O sistema deve permitir que o usuário configure uma quantidade mínima para cada polpa e deve sinalizar quando o estoque atingir esse valor. |
| RF07 | Histórico de Produções | O sistema deve permitir consultar o histórico de produções já registradas, com data, fruta, quantidade produzida e perda. |
| RF08 | Listagem de Estoque Baixo | O sistema deve permitir listar todos os produtos cujo estoque esteja abaixo da quantidade mínima definida. |

### 3.2 Requisitos Não-Funcionais

**Usabilidade**
| Código | Requisito | Descrição |
|--------|-----------|-----------|
| RNF01 | Facilidade de uso | O sistema deve ser mais simples e rápido de operar do que o preenchimento manual da planilha atual, reduzindo o trabalho repetitivo do proprietário. |

**Desempenho**
| Código | Requisito | Descrição |
|--------|-----------|-----------|
| RNF02 | Consulta rápida | A consulta do estoque deve retornar o resultado de forma imediata. |

**Confiabilidade**
| Código | Requisito | Descrição |
|--------|-----------|-----------|
| RNF03 | Consistência dos dados | O estoque exibido deve sempre refletir corretamente as produções e saídas já registradas. |

---

## 4. Casos de Uso

### UC01 — Cadastrar Produto
- **Ator principal:** Proprietário
- **Pré-condição:** Nenhuma
- **Fluxo principal:**
  1. O proprietário informa o nome (fruta) e o sabor da polpa.
  2. O proprietário informa o preço.
  3. O sistema gera um código para o produto e o cadastra.
- **Pós-condição:** Produto cadastrado no sistema.

### UC02 — Registrar Produção
- **Ator principal:** Proprietário
- **Pré-condição:** Produto já cadastrado no sistema
- **Fluxo principal:**
  1. O proprietário informa a data, a fruta utilizada e a quantidade de fruta processada.
  2. O proprietário informa a quantidade de polpa produzida.
  3. O sistema registra a produção.
- **Pós-condição:** Produção registrada.
- **Relação:** `<<include>>` Atualizar Estoque (UC03)

### UC03 — Atualizar Estoque
- **Ator principal:** Proprietário
- **Pré-condição:** Produção registrada
- **Fluxo principal:**
  1. O proprietário informa a quantidade produzida a ser adicionada ao estoque do produto.
  2. O sistema atualiza a quantidade em estoque.
- **Pós-condição:** Estoque do produto atualizado.

### UC04 — Registrar Saída de Estoque
- **Ator principal:** Proprietário
- **Pré-condição:** Produto com quantidade disponível em estoque
- **Fluxo principal:**
  1. O proprietário seleciona o produto.
  2. O proprietário informa a quantidade e a data da saída.
  3. O sistema atualiza o estoque, subtraindo a quantidade informada.
- **Fluxo alternativo:** O proprietário pode informar o motivo da saída (venda ou amostra).
- **Pós-condição:** Estoque do produto atualizado.

### UC05 — Consultar Estoque
- **Ator principal:** Proprietário
- **Pré-condição:** Nenhuma
- **Fluxo principal:**
  1. O proprietário solicita a consulta do estoque.
  2. O sistema exibe a quantidade disponível de cada polpa.
- **Pós-condição:** Nenhuma alteração de dados.

---

## 5. Regras de Negócio

- **RN01:** A saída de estoque não deve ultrapassar a quantidade disponível do produto.
- **RN02:** A saída de estoque é registrada apenas por quantidade, produto e data — sem vínculo com cliente ou venda.
- **RN03:** O estoque mínimo é definido pelo próprio usuário, individualmente por produto; ao atingir esse valor, o sistema deve sinalizar a necessidade de uma nova produção.
- **RN04:** A perda é descontada antes da produção, na etapa de chegada da fruta — a quantidade de fruta registrada na produção já é líquida de perda.
- **RN05:** Toda polpa é padronizada em pacotes de 100g, com validade igual entre todas as frutas.
- **RN06:** O preço de cada polpa varia conforme a fruta utilizada.
- **RN07:** Uma produção normalmente utiliza apenas uma fruta por vez.

---

## 6. Fluxo do Sistema (resumo)

```
Produção → Estoque → Saída de Estoque
```

---

## 7. Glossário

| Termo | Definição |
|-------|-----------|
| Polpa | Produto final, resultante do processamento da fruta, embalado em pacotes de 100g. |
| Produção | Registro do processamento de uma fruta em uma data específica, gerando polpa. |
| Perda | Quantidade de fruta descartada durante o processo, descontada antes da produção. |
| Estoque mínimo | Quantidade configurável abaixo da qual o sistema sinaliza a necessidade de nova produção. |
