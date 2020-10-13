package com.bookstore.backend.mapper;

import com.bookstore.backend.dto.ProductDto;
import com.bookstore.backend.dto.PurchasedProductDto;
import com.bookstore.backend.model.Product;
import com.bookstore.backend.model.PurchasedProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product dtoToProduct(ProductDto dto);

    ProductDto productToDto(Product product);

    List<ProductDto> productsListToDtoList(List<Product> products);

    PurchasedProduct productToPurchasedProduct(Product source);

    PurchasedProduct purchasedProductDtoToPurchasedProduct(PurchasedProductDto dto);

    List<PurchasedProduct> purchasedProductDtosToPurchasedProducts(List<PurchasedProductDto> dtos);

}
