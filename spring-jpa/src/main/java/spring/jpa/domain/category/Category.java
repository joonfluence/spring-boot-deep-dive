package spring.jpa.domain.category;

import jakarta.persistence.*;
import lombok.*;
import spring.jpa.domain.BaseTimeEntity;
import spring.jpa.domain.categoryItem.CategoryItem;
import spring.jpa.domain.product.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> items = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent__id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public void addCategoryItem(CategoryItem categoryItem){
        this.items.add(categoryItem);
        categoryItem.setCategory(this);
    }
}
