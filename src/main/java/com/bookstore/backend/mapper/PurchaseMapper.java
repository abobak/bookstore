package com.bookstore.backend.mapper;

import com.bookstore.backend.dto.PurchaseDto;
import com.bookstore.backend.model.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseMapper {

    @Mapping(target = "purchasedProductDtos", source = "purchasedProducts")
    PurchaseDto purchaseToDto(Purchase purchase);

    List<PurchaseDto> purchasesToDtos(List<Purchase> purchaseList);
}
