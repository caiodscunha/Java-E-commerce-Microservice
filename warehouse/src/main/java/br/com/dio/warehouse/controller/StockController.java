package br.com.dio.warehouse.controller;

import br.com.dio.warehouse.service.IProductService;
import br.com.dio.warehouse.service.IStockService;
import br.com.dio.warehouse.service.mapper.IProductMapper;
import br.com.dio.warehouse.service.mapper.IStockMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stocks")
@AllArgsConstructor
public class StockController {

    private final IStockService service;
    private final IStockMapper mapper;
}
