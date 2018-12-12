package alararestaurant.repository;

import alararestaurant.domain.dtos.ItemsByCategory;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public interface ItemRepository extends JpaRepository <Item,Integer> {

    Optional<Item> findByName(String name);
}
