package alararestaurant.domain.dtos;

import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.mappings.IHaveCustomMappings;
import com.google.gson.annotations.Expose;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ItemImportDto  {
    @Expose
    @Size(min = 3, max = 30)
    @Column(nullable = false, unique = true)
    private String name;
    @Expose
    @DecimalMin("0.01")
    @Column(nullable = false)
    private BigDecimal price;
    @Expose
    @NotNull
    private String category;

    public ItemImportDto() {
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getCategory() {
        return this.category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
