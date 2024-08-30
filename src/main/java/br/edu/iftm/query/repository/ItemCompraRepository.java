package br.edu.iftm.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.iftm.query.model.ItemCompra;

import java.util.List;

public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long> {

    // Todos os itens que foram vendidos sem desconto.
    @Query("SELECT i FROM ItemCompra i WHERE i.desconto = 0")
    List<ItemCompra> findItemsWithNoDiscount();

    // Todos os itens que foram vendidos com desconto.
    @Query("SELECT i FROM ItemCompra i WHERE i.desconto > 0")
    List<ItemCompra> findItemsWithDiscount();

    // Todos os itens ordenando resultado do maior valor para o menor.
    @Query("SELECT i FROM ItemCompra i ORDER BY i.valorUnit DESC")
    List<ItemCompra> findAllOrderedByValueDesc();

    // O produto mais vendido
    @Query("SELECT codProd FROM ItemCompra GROUP BY codProd ORDER BY SUM(quantidade) DESC LIMIT 1")
    Integer findTopByOrderByQuantidadeDesc();

    // Os itens de compra que foram vendidos com mais de 10 unidades.
    @Query("SELECT DISTINCT i.idNf FROM ItemCompra i WHERE i.quantidade > 10")
    List<Integer> findNfWithMoreThan10Units();
    
    // Itens de compra com valor total maior que 500.
    @Query("SELECT i.idNf, ROUND(SUM(i.valorUnit * i.quantidade * (1 - i.desconto / 100.0)), 2) AS total " +
            "FROM ItemCompra i " +
            "GROUP BY i.idNf " +
            "HAVING ROUND(SUM(i.valorUnit * i.quantidade * (1 - i.desconto / 100.0)), 2) > 500 " +
            "ORDER BY total DESC")
    List<Object[]> findNfWithTotalValueGreaterThan500();


}
