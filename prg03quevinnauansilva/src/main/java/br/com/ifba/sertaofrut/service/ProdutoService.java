package br.com.ifba.sertaofrut.service;

import br.com.ifba.infrastructure.exception.FalhaDePersistenciaException;
import br.com.ifba.sertaofrut.entity.Produto;
import br.com.ifba.sertaofrut.exception.ProdutoInvalidoException;
import br.com.ifba.sertaofrut.exception.ProdutoNaoEncontradoException;
import br.com.ifba.sertaofrut.repository.ProdutoRepository;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService implements ProdutoIService {

    private final ProdutoRepository repository;

    // o repository chega pronto (injecao), o service nao cria nada
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Produto save(Produto produto) {
        validar(produto);
        return executar("salvar produto", () -> {
            String nome = produto.getNome().trim();
            if (repository.existsByNomeIgnoreCase(nome)) {
                throw new ProdutoInvalidoException(mensagemNomeRepetido(nome));
            }
            produto.setCodigo(proximoCodigo());   // o sistema que gera o codigo
            return repository.save(produto);
        });
    }

    @Override
    public Produto update(Produto produto) {
        validar(produto);
        return executar("atualizar produto", () -> {
            String nome = produto.getNome().trim();
            if (repository.existsByNomeIgnoreCaseAndIdNot(nome, produto.getId())) {
                throw new ProdutoInvalidoException(mensagemNomeRepetido(nome));
            }
            return repository.save(produto);
        });
    }

    @Override
    public void delete(Produto produto) {
        executar("remover produto", () -> {
            repository.delete(produto);
            return null;
        });
    }

    @Override
    public Produto findById(Long id) {
        return executar("buscar produto", () -> repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id)));
    }

    @Override
    public List<Produto> findAll() {
        return executar("listar produtos", () -> repository.findAll(Sort.by("codigo")));
    }

    @Override
    public List<Produto> findByNome(String termo) {
        return executar("buscar produtos por nome",
                () -> repository.findByNomeContainingIgnoreCaseOrderByCodigo(termo));
    }

    // regras de negocio: nome obrigatorio e preco maior que zero
    private void validar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new ProdutoInvalidoException("O nome do produto é obrigatório.");
        }
        if (produto.getPreco() <= 0) {
            throw new ProdutoInvalidoException("O preço deve ser maior que zero.");
        }
    }

    private int proximoCodigo() {
        Integer maior = repository.findMaiorCodigo();
        return (maior == null) ? 1 : maior + 1;
    }

    private String mensagemNomeRepetido(String nome) {
        return "Já existe um produto com o nome \"" + nome + "\".";
    }

    private <T> T executar(String operacao, Supplier<T> acao) {
        try {
            return acao.get();
        } catch (DataAccessException e) {
            throw new FalhaDePersistenciaException(operacao, e);
        }
    }
}