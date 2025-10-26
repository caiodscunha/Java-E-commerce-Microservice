package br.com.dio.warehouse.service;

import br.com.dio.warehouse.entity.ProductEntity;

import java.util.UUID;

public interface IProductService {

    ProductEntity save(final ProductEntity product);

    void purchase(final UUID id);
}
