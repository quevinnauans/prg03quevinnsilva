# Atividade 23 - Spring Data

## Codigo removido
Saida de `git diff --stat HEAD~1 HEAD` do commit que apagou as classes sem dono:

.../br/com/ifba/infrastructure/dao/GenericDao.java | 144 ---------------------
 .../com/ifba/infrastructure/dao/GenericIDao.java   |  18 ---
 .../infrastructure/util/ConfiguracaoBanco.java     |  52 --------
 .../br/com/ifba/infrastructure/util/JPAUtil.java   |  24 ----
 .../ifba/infrastructure/util/MigrationRunner.java  |  25 ----
 .../br/com/ifba/sertaofrut/dao/ProdutoDao.java     |  76 -----------
 .../br/com/ifba/sertaofrut/dao/ProdutoIDao.java    |  15 ---
 .../resources/META-INF/persistence.exemplo.xml     |  15 ---
 .../ifba/infrastructure/dao/GenericDaoTest.java    | 123 ------------------
 .../br/com/ifba/sertaofrut/dao/ProdutoDaoTest.java |  34 -----
 10 files changed, 526 deletions(-)

Total de linhas removidas: 526
