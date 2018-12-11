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

    public List<ItemsByCategory> itemsByCategoryCount() {

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
        itemsByCategories.stream()
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
                                                                        while (matcher.find())
                                                                        {
                                                                            lastIndex = matcher.start();
                                                                        }

                                                                    result.append("--- Item Name: ").append(iO.substring(0,lastIndex+1))
                                                                            .append(System.lineSeparator());
                                                                    String price=iO;
                                                                    price=price.replace(iO.substring(0,lastIndex+1),"");
                                                                    result.append("--- Item Price: ").append(price)
                                                                            .append(System.lineSeparator());
                                                                }
                                                        );
                                        }
                                );
        // Map<String, Set<ItemsByCategory>> itemCategory = new LinkedHashMap<>();
//        List<ItemsByCategory> itemsByCategories =itemRepository.itemsByCategoryCount();
//               itemsByCategories.stream().forEach(i -> {
//                            if (!itemCategory.containsKey(i.getCategory()))
//                            {itemCategory.put(i.getCategory(),
//                                        new LinkedHashSet<>(Arrays.asList(new ItemsByCategory(i.getItemName(), i.getItemPrice(),new BigDecimal(0)))));}
//                            else {itemCategory.get(i.getCategory()).add(new ItemsByCategory(i.getItemName(), i.getItemPrice(),new BigDecimal(0)));}
//                        });
//               final BigDecimal sum= new BigDecimal(0);
//        itemCategory.values().stream().forEach(i->{itemCategory.get(i).stream().forEach(item->item.setSumPricebyCatgeory());})
//                        itemCategory.keySet().stream()
//                                .forEach(category ->
//                                        {
//                                            result.append("Category: ");
//                                            result.append(category).append(System.lineSeparator());
//                                            itemCategory.get(category)
//                                                        .forEach(iO ->
//                                                                {
//                                                                    result.append("--- Item Name: ").append(iO.getName())
//                                                                            .append(System.lineSeparator());
//                                                                    result.append("--- Item Price: ").append(iO.getPrice())
//                                                                            .append(System.lineSeparator());
//                                                                }
//                                                        );
//                                        }
//                                );
        return result.toString();
    }
}
