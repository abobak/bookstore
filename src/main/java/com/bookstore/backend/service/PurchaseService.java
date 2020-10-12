package com.bookstore.backend.service;

import com.bookstore.backend.mapper.PurchaseMapper;
import com.bookstore.backend.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final PurchaseMapper purchaseMapper;

}
