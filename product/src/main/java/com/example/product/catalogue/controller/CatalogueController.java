package com.example.product.catalogue.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogues")
public class CatalogueController {

    @GetMapping("/all")
    public ResponseEntity<List<String>> getCatalogues(){
        return ResponseEntity.ok(List.of("Catalogue1", "Catalogue2", "Catalogue3"));
    }
}
