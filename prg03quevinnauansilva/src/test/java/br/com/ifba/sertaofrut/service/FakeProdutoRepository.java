package br.com.ifba.sertaofrut.service;

import br.com.ifba.sertaofrut.entity.Produto;
import br.com.ifba.sertaofrut.repository.ProdutoRepository;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// repository de mentira
public class FakeProdutoRepository {

    private final List<Produto> produtos = new ArrayList<>();
    private long proximoId = 1;

    public ProdutoRepository criar() {
        return (ProdutoRepository) Proxy.newProxyInstance(
                ProdutoRepository.class.getClassLoader(),
                new Class<?>[]{ProdutoRepository.class},
                (proxy, metodo, args) -> {
                    if (metodo.getName().equals("equals")) {
                        return proxy == args[0];
                    }
                    return tratar(metodo.getName(), args);
                });
    }

    // responde so aos metodos que o ProdutoService usa
    private Object tratar(String metodo, Object[] args) {
        switch (metodo) {
            case "save":
                return salvar((Produto) args[0]);
            case "findAll":
                return new ArrayList<>(produtos);
            case "findById":
                return Optional.ofNullable(buscar((Long) args[0]));
            case "delete": {
                Produto removido = (Produto) args[0];
                produtos.removeIf(p -> p.getId().equals(removido.getId()));
                return null;
            }
            case "existsByNomeIgnoreCase":
                return existeNome((String) args[0], null);
            case "existsByNomeIgnoreCaseAndIdNot":
                return existeNome((String) args[0], (Long) args[1]);
            case "findByNomeContainingIgnoreCaseOrderByCodigo":
                return filtrarPorNome((String) args[0]);
            case "findMaiorCodigo":
                return maiorCodigo();
            case "toString":
                return "FakeProdutoRepository";
            case "hashCode":
                return System.identityHashCode(this);
            default:
                throw new UnsupportedOperationException("O fake nao implementa: " + metodo);
        }
    }

    private Produto salvar(Produto produto) {
        if (produto.getId() == null) {
            produto.setId(proximoId++);
            produtos.add(produto);
            return produto;
        }
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(produto.getId())) {
                produtos.set(i, produto);
                return produto;
            }
        }
        produtos.add(produto);
        return produto;
    }

    private Produto buscar(Long id) {
        for (Produto p : produtos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    private boolean existeNome(String nome, Long idIgnorado) {
        for (Produto p : produtos) {
            boolean mesmoNome = p.getNome().equalsIgnoreCase(nome);
            boolean outroProduto = idIgnorado == null || !p.getId().equals(idIgnorado);
            if (mesmoNome && outroProduto) {
                return true;
            }
        }
        return false;
    }

    private List<Produto> filtrarPorNome(String termo) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getNome().toLowerCase().contains(termo.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    private Integer maiorCodigo() {
        Integer maior = null;
        for (Produto p : produtos) {
            if (maior == null || p.getCodigo() > maior) {
                maior = p.getCodigo();
            }
        }
        return maior;
    }
}