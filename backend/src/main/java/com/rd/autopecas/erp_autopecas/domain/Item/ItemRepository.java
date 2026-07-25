package com.rd.autopecas.erp_autopecas.domain.Item;

import com.rd.autopecas.erp_autopecas.domain.carro.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Boolean existsByIdAndCarros_Id(Long idItem, Long idCarro);

    @Query(value = """
        select c.*
        from carro c
        inner join carro_items ci
        ON c.id = ci.id_carro
        WHERE ci.id_item = :itemId
    """, nativeQuery = true)
    List<Carro> findAllCarsByItemId(Long itemId);
}