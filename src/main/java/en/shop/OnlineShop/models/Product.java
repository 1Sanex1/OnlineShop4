package en.shop.OnlineShop.models;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should be 2 to 30 chars")
    private String name;

    @Column(name = "info")
    @NotEmpty(message = "Info shouldn't be empty")
    @Size(min = 2, max = 30, message = "Info should be 2 to 30 chars")
    private String info;

    @Column(name = "cost")
    @Positive
    private double cost;

    @Column(name = "count")
    @PositiveOrZero
    private int count;


    public Product(){}

    public Product(Product product){
        id = product.id;
        name = product.name;
        info = product.info;
        cost = product.cost;
        count = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
