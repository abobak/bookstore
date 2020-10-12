package com.bookstore.backend.mapper;

import com.bookstore.backend.dto.PurchaseDto;
import com.bookstore.backend.model.Purchase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    PurchaseDto purchaseToDto(Purchase purchase);

}
