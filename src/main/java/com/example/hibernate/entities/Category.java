package com.example.hibernate.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.BatchSize;
import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "categories")
@NoArgsConstructor
@Accessors(chain = true)
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue
    int id;
    @Column(name = "title")
    String title;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    List<Product> products;

}