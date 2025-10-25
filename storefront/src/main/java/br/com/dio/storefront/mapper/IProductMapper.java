package br.com.dio.storefront.mapper;

import br.com.dio.storefront.dto.;
import br.com.dio.storefront.entity.ProductEntity;
import org.mapstruct.Mapper;

import javax.swing.*;

import java.math.BigDecimal;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IProductMapper {

    ProductInfoDTO toDTO(final ProductEntity entity, final BigDecimal price);



}
