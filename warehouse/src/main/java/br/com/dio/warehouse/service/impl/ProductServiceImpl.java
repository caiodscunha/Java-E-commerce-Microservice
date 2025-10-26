package br.com.dio.warehouse.service.impl;

import br.com.dio.warehouse.dto.ProductStorefrontSaveDTO;
import br.com.dio.warehouse.entity.ProductEntity;
import br.com.dio.warehouse.repository.ProductRepository;
import br.com.dio.warehouse.service.IProductQueryService;
import br.com.dio.warehouse.service.IProductService;
import br.com.dio.warehouse.service.IStockService;
import br.com.dio.warehouse.service.mapper.IProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;
    private final IProductQueryService queryService;
    private final IStockService stockService;
    private final RestClient storefrontClient;
    private final IProductMapper mapper;

    @Override
    public ProductEntity save(ProductEntity entity) {
        repository.save(entity);
        var dto = mapper.toDTO(entity);
        saveStorefront(dto);
        return entity;
    }

    private void saveStorefront(ProductStorefrontSaveDTO dto) {
        storefrontClient.post()
                .uri("/products")
                .body(dto)
                .retrieve()
                .body(ProductStorefrontSaveDTO.class);
    }

    @Override
    public void purchase(UUID id) {
        var entity = queryService.findById(id);
        var stock = entity.decStock();
        repository.save(entity);

        if(stock.isUnavailable()){
            stockService.changeStatus(entity.getId(),stock.getStatus());
        }
    }
}
