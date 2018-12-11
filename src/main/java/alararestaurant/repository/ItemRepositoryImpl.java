package alararestaurant.repository;

import alararestaurant.domain.dtos.ItemsByCategory;
import alararestaurant.domain.entities.Item;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public abstract class ItemRepositoryImpl implements ItemRepository {
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public List<ItemsByCategory> itemsByCategoryCount() {
//
//        List<ItemsByCategory> itemsByCategories = em
//                .createQuery(
//                        "select c.name as category, group_concat(i.name,i.price) as itemsC from item as i " +
//                                "join i.category as c group by i.category " +
//                                "order by count(i.category) desc,sum(i.price) desc")
//                .unwrap(Query.class)
//                .setResultTransformer(
//                       Transformers.aliasToBean(ItemsByCategory.class)
//                ).getResultList();
//        return itemsByCategories;
//    }

}
