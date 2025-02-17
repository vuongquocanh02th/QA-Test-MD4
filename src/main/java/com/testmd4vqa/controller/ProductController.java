package com.testmd4vqa.controller;

import com.testmd4vqa.model.Product;
import com.testmd4vqa.service.ICategoryService;
import com.testmd4vqa.service.IProductService;
import com.testmd4vqa.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final IProductService iProductService;
    private final ICategoryService iCategoryService;

    public ProductController(IProductService iProductService, ICategoryService iCategoryService){
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("/list")
    public String listProduct(Model model){
        List<Product> products = iProductService.getAllProducts();
        model.addAttribute("products", products);
        return "/list";
    }
    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("products", new Product());
        model.addAttribute("categories", iCategoryService.getAllCategories());
        return "../templates/addForm";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        iProductService.saveProduct(product);
        return "redirect:/products/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        iProductService.deleteProduct(id);
        return "redirect:/products/list";
    }
}
