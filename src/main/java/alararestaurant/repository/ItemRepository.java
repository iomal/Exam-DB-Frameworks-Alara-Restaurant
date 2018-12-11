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

//    List<ItemsByCategory> itemsByCategoryCount();

    Optional<Item> findByName(String name);

//    @Query (value = "select new alararestaurant.domain.dtos.ItemsByCategory(i.category.name,i.name, i.price," +
//            "sum(i.price )) from " +
//            "alararestaurant.domain.entities.Item as i join i.category group by i.name,i.price, i.category.name " +
//            "order by count(i.category),sum(i.price) desc")


   // List<ItemsByCategory> itemsByCategoryCount();
}
