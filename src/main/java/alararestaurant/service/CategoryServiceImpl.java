package alararestaurant.service;

import alararestaurant.domain.dtos.ItemsByCategory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CategoryServiceImpl implements CategoryService {
    @PersistenceContext
    private EntityManager em;

    private List<ItemsByCategory> itemsByCategoryCount() {
        @SuppressWarnings("unchecked")
        List<ItemsByCategory> itemsByCategories = em
                .createQuery(
                        "select c.name as category, group_concat(i.name,i.price) as itemsC from item as i " +
                                "join i.category as c group by i.category " +
                                "order by count(i.category) desc,sum(i.price) desc")
                .unwrap(Query.class)
                .setResultTransformer(
                        Transformers.aliasToBean(ItemsByCategory.class)
                ).getResultList();
        return itemsByCategories;
    }


    @Override
    public String exportCategoriesByCountOfItems() {
        StringBuilder result = new StringBuilder();
        List<ItemsByCategory> itemsByCategories = itemsByCategoryCount();
        itemsByCategories
                .forEach(item ->
                        {
                            result.append("Category: ");
                            result.append(item.getCategory()).append(System.lineSeparator());
                            Arrays.stream(item.getItemsC().split(","))
                                    .forEach(iO ->
                                            {
                                                Pattern pattern = Pattern.compile("[a-zA-Z]");
                                                Matcher matcher = pattern.matcher(iO);
                                                int lastIndex = -1;
                                                while (matcher.find()) {
                                                    lastIndex = matcher.start();
                                                }

                                                result.append("--- Item Name: ").append(iO.substring(0, lastIndex + 1))
                                                        .append(System.lineSeparator());
                                                String price = iO;
                                                price = price.replace(iO.substring(0, lastIndex + 1), "");
                                                result.append("--- Item Price: ").append(price)
                                                        .append(System.lineSeparator());
                                            }
                                    );
                        }
                );

        return result.toString();
    }
}
