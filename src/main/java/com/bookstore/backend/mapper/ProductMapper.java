package com.bookstore.backend.mapper;

import com.bookstore.backend.dto.ProductDto;
import com.bookstore.backend.dto.PurchasedProductDto;
import com.bookstore.backend.model.Product;
import com.bookstore.backend.model.PurchasedProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product dtoToProduct(ProductDto dto);

    ProductDto productToDto(Product product);

    List<ProductDto> productsListToDtoList(List<Product> products);

    @Mapping(target = "purchasePrice", source="price")
    @Mapping(target = "productId", source = "source.id")
    @Mapping(target = "id", ignore = true)
    PurchasedProduct productToPurchasedProduct(Product source);

    List<PurchasedProduct> productsToPurchasedProducts(List<Product> products);

    @Mapping(target = "purchasePrice", source="price")
    @Mapping(target = "productId", source = "source.id")
    @Mapping(target = "id", ignore = true)
    PurchasedProductDto productToPurchasedProductDto(Product source);

    @Mapping(target = "title", source = "dto.title")
    PurchasedProduct purchasedProductDtoToPurchasedProduct(PurchasedProductDto dto);

    List<PurchasedProduct> purchasedProductDtosToPurchasedProducts(List<PurchasedProductDto> dtos);

}
