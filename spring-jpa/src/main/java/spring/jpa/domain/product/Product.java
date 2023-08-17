package spring.jpa.domain.product;


import jakarta.persistence.*;
import lombok.*;
import spring.jpa.domain.BaseTimeEntity;
import spring.jpa.domain.category.Category;
import spring.jpa.domain.categoryItem.CategoryItem;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Builder
@Entity
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private Integer price;
    private Integer stockQuantity;

    @OneToMany(mappedBy = "product")
    private List<CategoryItem> categoryItem = new ArrayList<>();

    public void addCategoryItem(CategoryItem categoryItem) {
        this.categoryItem.add(categoryItem);
        categoryItem.setProduct(this);
    }

    /**
     * stock 증가
     * @param quantity
     */

    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     * @param quantity
     * @throws NotEnoughStockException
     */
    public void removeStock(int quantity) throws NotEnoughStockException {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
