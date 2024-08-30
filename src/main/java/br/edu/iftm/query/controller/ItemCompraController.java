package br.edu.iftm.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.query.model.ItemCompra;
import br.edu.iftm.query.service.ItemCompraService;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
@Tag(name = "ItemCompra", description = "API para consulta de itens de compras")
public class ItemCompraController {

    @Autowired
    private ItemCompraService service;

    @GetMapping("/sem-desconto")
    @Operation(summary = "Retorna todos os itens sem desconto", description = "Retorna todos os itens de compra que não possuem desconto", tags = {"ItemCompra"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Itens retornados com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public List<ItemCompra> getItensSemDesconto() {
        return service.getItensSemDesconto();
    }

    @GetMapping("/com-desconto")
    @Operation(summary = "Retorna todos os itens com desconto", description = "Retorna todos os itens de compra que possuem desconto", tags = {"ItemCompra"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Itens retornados com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public List<ItemCompra> getItensComDesconto() {
        return service.getItensComDesconto();
    }

    @GetMapping("/ordenados-por-valor")
    @Operation(summary = "Retorna todos os itens ordenados por valor unitário", description = "Retorna todos os itens de compra ordenados por valor unitário", tags = {"ItemCompra"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Itens retornados com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public List<ItemCompra> getAllItensOrderByValorUnitDesc() {
        return service.getAllItensOrderByValorUnitDesc();
    }

    @GetMapping("/produto-mais-vendido")
    @Operation(summary = "Retorna o produto mais vendido", description = "Retorna o código do produto mais vendido", tags = {"ItemCompra"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Item retornado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public Integer getProdutoMaisVendido() {
        return service.getProdutoMaisVendido();
    }

    @GetMapping("/nfs-mais-de-10-unidades")
    @Operation(summary = "Retorna as notas fiscais com mais de 10 unidades vendidas", description = "Retorna os códigos das notas fiscais com mais de 10 unidades vendidas", tags = {"ItemCompra"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Registros retornados com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhuma registro encontrado")
    })
    public List<Integer> getItemCompraWithMoreThan10Units() {
        return service.getNfWithMoreThan10Units();
    }

    @GetMapping("/nfs-valor-maior-500")
    @Operation(summary = "Retorna as notas fiscais com valor total maior que 500", description = "Retorna os códigos das notas fiscais que possuem valor total maior que 500", tags = {"ItemCompra"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Registros retornados com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Nenhuma registro encontrado")
    })
    public List<Object[]> getItemCompraWithTotalValueGreaterThan500() {
        return service.getNfWithTotalValueGreaterThan500();
    }
}
