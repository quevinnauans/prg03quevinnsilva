package br.com.ifba.sertaofrut.service;

import br.com.ifba.sertaofrut.dao.ProdutoIDao;
import br.com.ifba.sertaofrut.entity.Produto;
import br.com.ifba.sertaofrut.exception.ProdutoNaoEncontradoException;
import java.util.ArrayList;
import java.util.List;

// DAO falso: guarda os produtos numa lista em memoria, sem banco
public class FakeProdutoDao implements ProdutoIDao {

    private final List<Produto> produtos = new ArrayList<>();
    private long proximoId = 1;

    @Override
    public Produto save(Produto produto) {
        produto.setId(proximoId++);
        produtos.add(produto);
        return produto;
    }

    @Override
    public Produto update(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(produto.getId())) {
                produtos.set(i, produto);
                return produto;
            }
        }
        throw new ProdutoNaoEncontradoException(produto.getId());
    }

    @Override
    public void delete(Produto produto) {
        produtos.removeIf(p -> p.getId().equals(produto.getId()));
    }

    @Override
    public Produto findById(Long id) {
        for (Produto p : produtos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new ProdutoNaoEncontradoException(id);
    }

    @Override
    public List<Produto> findAll() {
        return new ArrayList<>(produtos);
    }

    @Override
    public List<Produto> findByNome(String termo) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getNome().toLowerCase().contains(termo.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    @Override
    public int proximoCodigo() {
        int maior = 0;
        for (Produto p : produtos) {
            if (p.getCodigo() > maior) {
                maior = p.getCodigo();
            }
        }
        return maior + 1;
    }
}