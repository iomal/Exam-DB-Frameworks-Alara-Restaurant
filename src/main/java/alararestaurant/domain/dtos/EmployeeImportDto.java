package alararestaurant.domain.dtos;

import alararestaurant.mappings.IHaveCustomMappings;
import alararestaurant.domain.entities.Employee;
import com.google.gson.annotations.Expose;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeImportDto{

    @Expose
    @Size(min = 3, max = 30)
    @NotNull
    private String name;
    @Expose
    @Min(15)
    @Max(80)
    @NotNull
    private Integer age;
    @Expose
    @NotNull
    @Size(min = 3, max = 30)
    private String position;

    public EmployeeImportDto() {
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getPosition() {
        return this.position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
