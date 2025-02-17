package com.testmd4vqa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cId;
    private String name;
    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;
}
