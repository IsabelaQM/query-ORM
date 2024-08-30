package br.edu.iftm.query.service;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.iftm.query.exception.ItemNaoEncontradoException;
import br.edu.iftm.query.model.ItemCompra;
import br.edu.iftm.query.repository.ItemCompraRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemCompraServiceTest {

    @InjectMocks
    private ItemCompraService itemCompraService;

    @Mock
    private ItemCompraRepository itemCompraRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Teste para itens sem desconto")
    @Test
    public void testGetItensSemDescontoWhenItemsExist() {
        ItemCompra item = new ItemCompra();
        item.setDesconto(0);
        when(itemCompraRepository.findItemsWithNoDiscount()).thenReturn(List.of(item));
        
        List<ItemCompra> result = itemCompraService.getItensSemDesconto();
        assertEquals(1, result.size());
        assertEquals(0, result.get(0).getDesconto());
    }

    @DisplayName("Teste para não existirem itens sem desconto")
    @Test
    public void testGetItensSemDescontoWhenNoItemsExist() {
        when(itemCompraRepository.findItemsWithNoDiscount()).thenReturn(Collections.emptyList());
        
        assertThrows(ItemNaoEncontradoException.class, () -> {
            itemCompraService.getItensSemDesconto();
        });
    }

    @DisplayName("Teste para todos os itens ordenados por valor")
    @Test
    public void testGetAllItensOrderByValorUnitDesc() {
        ItemCompra item = new ItemCompra();
        item.setValorUnit(100.00);
        when(itemCompraRepository.findAllOrderedByValueDesc()).thenReturn(List.of(item));
        
        List<ItemCompra> result = itemCompraService.getAllItensOrderByValorUnitDesc();
        assertEquals(1, result.size());
        assertEquals(100.00, result.get(0).getValorUnit());
    }

    //Testes para itens com desconto
    @Test
    public void testGetItensComDescontoWhenItemsExist() {
        ItemCompra item = new ItemCompra();
        item.setDesconto(10);
        when(itemCompraRepository.findItemsWithDiscount()).thenReturn(List.of(item));
        
        List<ItemCompra> result = itemCompraService.getItensComDesconto();
        assertEquals(1, result.size());
        assertEquals(10, result.get(0).getDesconto());
    }

    @DisplayName("Teste para não existirem itens com desconto")
    @Test
    public void testGetItensComDescontoWhenNoItemsExist() {
        when(itemCompraRepository.findItemsWithDiscount()).thenReturn(Collections.emptyList());
        
        assertThrows(ItemNaoEncontradoException.class, () -> {
            itemCompraService.getItensComDesconto();
        });
    }

    @DisplayName("Teste para o produto mais vendido")
    @Test
    public void testGetProdutoMaisVendidoWhenProductExists() {
        when(itemCompraRepository.findTopByOrderByQuantidadeDesc()).thenReturn(1);
        
        Integer result = itemCompraService.getProdutoMaisVendido();
        assertEquals(1, result);
    }

    @DisplayName("Teste para não existir um produto mais vendido")
    @Test
    public void testGetProdutoMaisVendidoWhenNoProductExists() {
        when(itemCompraRepository.findTopByOrderByQuantidadeDesc()).thenReturn(null);
        
        assertThrows(ItemNaoEncontradoException.class, () -> {
            itemCompraService.getProdutoMaisVendido();
        });
    }

    @DisplayName("Teste para notas fiscais com mais de 10 unidades vendidas em uma único registro")
    @Test
    public void testGetNfWithMoreThan10UnitsWhenItemCompraExists() {
        when(itemCompraRepository.findNfWithMoreThan10Units()).thenReturn(List.of(1));
        
        List<Integer> result = itemCompraService.getNfWithMoreThan10Units();
        assertEquals(1, result.size());
        assertEquals(1, result.get(0));
    }

    @DisplayName("Teste para notas fiscais com mais de 10 unidades vendidas em uma única compra")
    @Test
    public void testGetNfWithMoreThan10UnitsWhenNoItemCompraExists() {
        when(itemCompraRepository.findnNfWithMoreThan10Units()).thenReturn(Collections.emptyList());
        
        assertThrows(ItemNaoEncontradoException.class, () -> {
            itemCompraService.getNfWithMoreThan10Units();
        });
    }

    @DisplayName("Teste para itens de compra com valor total maior que 500")
    @Test
    public void testNfWithTotalValueGreaterThan500WhenNoItemCompraExists() {
        when(itemCompraRepository.findNfWithTotalValueGreaterThan500()).thenReturn(Collections.emptyList());
        
        assertThrows(ItemNaoEncontradoException.class, () -> {
            itemCompraService.getNfWithTotalValueGreaterThan500();
        });
    }
}
