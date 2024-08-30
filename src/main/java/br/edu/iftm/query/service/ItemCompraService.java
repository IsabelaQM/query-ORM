package br.edu.iftm.query.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.query.exception.ItemNaoEncontradoException;
import br.edu.iftm.query.model.ItemCompra;
import br.edu.iftm.query.repository.ItemCompraRepository;

import java.util.List;

@Service
public class ItemCompraService {

    @Autowired
    private ItemCompraRepository repository;

    public List<ItemCompra> getItensSemDesconto() {
        List<ItemCompra> items = repository.findItemsWithNoDiscount();
        if (items.isEmpty()) {
            throw new ItemNaoEncontradoException("Não foram encontrados itens sem desconto.");
        }
        return items;
    }

    public List<ItemCompra> getItensComDesconto() {
        List<ItemCompra> items = repository.findItemsWithDiscount();
        if (items.isEmpty()) {
            throw new ItemNaoEncontradoException("Não foram encontrados itens com desconto.");
        }
        return items;
    }

    public List<ItemCompra> getAllItensOrderByValorUnitDesc() {
        return repository.findAllOrderedByValueDesc();
    }

    public Integer getProdutoMaisVendido() {
        Integer produto = repository.findTopByOrderByQuantidadeDesc();
        if (produto == null) {
            throw new ItemNaoEncontradoException("Nenhum produto encontrado.");
        }
        return produto;
    }

    public List<Integer> getNfWithMoreThan10Units() {
        List<Integer> nfIds = repository.findNfWithMoreThan10Units();
        if (nfIds.isEmpty()) {
            throw new ItemNaoEncontradoException("Nenhuma nota fiscal com mais de 10 unidades vendidas por compra.");
        }
        return nfIds;
    }


    public List<Object[]> getNfWithTotalValueGreaterThan500() {
        List<Object[]> nfTotals = repository.findNfWithTotalValueGreaterThan500();
        if (nfTotals.isEmpty()) {
            throw new ItemNaoEncontradoException("Nenhuma nota fiscal com valor total maior que 500.");
        }
        return nfTotals;
    }
}
