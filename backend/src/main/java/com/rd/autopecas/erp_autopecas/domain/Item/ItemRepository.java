package com.rd.autopecas.erp_autopecas.domain.Item;

import com.rd.autopecas.erp_autopecas.domain.carro.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Boolean existsByIdAndCarros_Id(Long idItem, Long idCarro);

    //usando slq native
    @Query(value = """
        select c.*
        from carro c
        inner join carro_items ci
        ON c.id = ci.id_carro
        WHERE ci.id_item = :itemId
    """, nativeQuery = true)
    Page<Carro> findAllCarsByItemId(Long itemId, Pageable pageable);

}