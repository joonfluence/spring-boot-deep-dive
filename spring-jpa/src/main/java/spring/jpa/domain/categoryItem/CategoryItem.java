package spring.jpa.domain.categoryItem;

import jakarta.persistence.*;
import lombok.*;
import spring.jpa.domain.BaseTimeEntity;
import spring.jpa.domain.category.Category;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Entity
public class CategoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryItem_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
