package br.com.dio.warehouse.controller;

import br.com.dio.warehouse.service.IProductService;
import br.com.dio.warehouse.service.mapper.IProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {

    private final IProductService service;
    private final IProductMapper mapper;
}
