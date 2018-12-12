package alararestaurant.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity (name="category")
@Table (name="categories")
public class Category extends BaseEntity {
    private String name;
    private Set<Item> items;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    @OneToMany(mappedBy = "category")
    public Set<Item> getItems() {
        return this.items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
