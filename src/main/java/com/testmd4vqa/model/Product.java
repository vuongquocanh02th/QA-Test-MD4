package com.testmd4vqa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String status;

    @ManyToOne
    @JoinColumn(name = "product_category")
    private Category category;
}
