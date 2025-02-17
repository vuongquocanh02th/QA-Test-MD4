package com.testmd4vqa.controller;

import com.testmd4vqa.model.Product;
import com.testmd4vqa.service.ICategoryService;
import com.testmd4vqa.service.IProductService;
import com.testmd4vqa.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final IProductService iProductService;
    private final ICategoryService iCategoryService;

    public ProductController(IProductService iProductService, ICategoryService iCategoryService) {
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("/list")
    public String listProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) String status,
            Model model) {

        Pageable pageable = PageRequest.of(page, 5);
        Page<Product> productPage;

        if (name != null || category != null || status != null) {
            productPage = iProductService.searchProducts(name, category, status, pageable);
        } else {
            productPage = iProductService.getAllProducts(pageable); // ✅ Gọi đúng phương thức getAllProducts()
        }


        model.addAttribute("productPage", productPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("categories", iCategoryService.getAllCategories()); // Lấy danh sách loại sản phẩm
        model.addAttribute("name", name);
        model.addAttribute("category", category);
        model.addAttribute("status", status);

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
