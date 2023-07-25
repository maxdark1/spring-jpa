package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.services.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescripcionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailableTrueAndDescripcionNotContainingIgnoreCase(String description);
    @Query(value = """
            UPDATE pizza set price = :#{#newPizzaPrice.newPrice} 
            WHERE id_pizza= :#{#newPizzaPrice.pizzaId}
            """, nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice")UpdatePizzaPriceDto newPizzaPrice);
}
