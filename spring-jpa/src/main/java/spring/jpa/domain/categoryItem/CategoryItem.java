package spring.jpa.domain.categoryItem;

import jakarta.persistence.*;
import lombok.Setter;
import spring.jpa.domain.category.Category;
import spring.jpa.domain.product.Product;

@Setter
@Entity
public class CategoryItem {
    @EmbeddedId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
