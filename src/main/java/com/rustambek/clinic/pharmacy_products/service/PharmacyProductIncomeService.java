package com.rustambek.clinic.pharmacy_products.service;

import com.rustambek.clinic.convertor.mapstruct.PharmacyProductIncomeMapper;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductIncomeDto;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductIncomeReq;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductReq;
import com.rustambek.clinic.pharmacy_products.entity.PharmacyProduct;
import com.rustambek.clinic.pharmacy_products.entity.PharmacyProductIncome;
import com.rustambek.clinic.pharmacy_products.repository.PharmacyProductIncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.rustambek.clinic.specification.PharmacyProductIncomeSpecifications.byFilter;


@Service
@RequiredArgsConstructor
@Transactional
public class PharmacyProductIncomeService {

    private final PharmacyProductIncomeRepository repository;
    private final PharmacyProductIncomeMapper mapper;
    private final PharmacyProductService pharmacyProductService;

    public PharmacyProductIncomeDto create(PharmacyProductIncomeReq req) {
        PharmacyProduct product = pharmacyProductService.getModel(req.getPharmacyProductId());
        int quantity = product.getQuantity() + req.getQuantity();
        product.setQuantity(quantity);
        product.setIncomePrice(product.getIncomePrice());
        product.setSalePrice(product.getSalePrice());
        pharmacyProductService.updateByModel(req.getPharmacyProductId(),product);
        PharmacyProductIncome entity = mapper.toEntity(req);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public PharmacyProductIncomeDto getById(Long id) {
        PharmacyProductIncome income = getModel(id);
        return mapper.toDto(income);
    }

    private PharmacyProductIncome getModel(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("PharmacyProductIncome not found: " + id));
    }

    public PharmacyProductIncomeDto update(Long id, PharmacyProductIncomeReq req) {
        PharmacyProductIncome income = getModel(id);
        PharmacyProduct product = pharmacyProductService.getModel(req.getPharmacyProductId());
        int quantity = product.getQuantity() + req.getQuantity() - income.getQuantity();
        product.setQuantity(quantity);
        product.setIncomePrice(req.getIncomePrice());
        product.setSalePrice(req.getSalePrice());
        pharmacyProductService.updateByModel(req.getPharmacyProductId(),product);
        mapper.setModel(income, req);
        return mapper.toDto(repository.save(income));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("PharmacyProductIncome not found: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<PharmacyProductIncomeDto> pageable(Long pharmacyProductId, Pageable pageable) {
        return mapper.toDtoPage(repository.findAll(byFilter(pharmacyProductId), pageable));
    }
}
