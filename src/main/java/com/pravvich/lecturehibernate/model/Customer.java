package com.pravvich.lecturehibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "customer.products",
                attributeNodes = {
                        @NamedAttributeNode("products")
                }
        )
})
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Product> products;

}
