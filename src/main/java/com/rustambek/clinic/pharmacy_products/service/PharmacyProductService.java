package com.rustambek.clinic.pharmacy_products.service;
import com.rustambek.clinic.convertor.mapstruct.PharmacyProductMapper;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductDto;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductReq;
import com.rustambek.clinic.pharmacy_products.entity.PharmacyProduct;
import com.rustambek.clinic.pharmacy_products.entity.PharmacyProductIncome;
import com.rustambek.clinic.pharmacy_products.model.UnitType;
import com.rustambek.clinic.pharmacy_products.repository.PharmacyProductIncomeRepository;
import com.rustambek.clinic.pharmacy_products.repository.PharmacyProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.rustambek.clinic.specification.PharmacyProductSpecifications.byFilter;


@Service
@RequiredArgsConstructor
@Transactional
public class PharmacyProductService {

    private final PharmacyProductRepository repository;
    private final PharmacyProductMapper mapper;
    private final PharmacyProductIncomeRepository pharmacyProductIncomeRepository;


    public PharmacyProductDto create(PharmacyProductReq req) {
        PharmacyProduct entity = mapper.toEntity(req);
        entity.setLastDateTime(LocalDateTime.now());
        PharmacyProduct model = repository.save(entity);
        pharmacyProductIncomeRepository.save( PharmacyProductIncome.builsModel(model.getId(),req.getQuantity(),req.getSalePrice(),req.getIncomePrice()));
        return mapper.toDto(model);
    }

    @Transactional(readOnly = true)
    public PharmacyProductDto getById(Long id) {
        PharmacyProduct product = getModel(id);
        return mapper.toDto(product);
    }

    public PharmacyProduct getModel(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("PharmacyProduct not found: " + id));
    }

    public PharmacyProductDto update(Long id, PharmacyProductReq req) {
        PharmacyProduct product = getModel(id);
        mapper.setModel(product, req);
        product.setLastDateTime(LocalDateTime.now());
        return mapper.toDto(repository.save(product));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("PharmacyProduct not found: " + id);
        }
        repository.deleteById(id); // soft delete if BaseEntity has @SQLDelete
    }

    @Transactional(readOnly = true)
    public Page<PharmacyProductDto> pageable(String name, UnitType unit, Pageable pageable) {
        return mapper.toDtoPage(repository.findAll(byFilter(name, unit), pageable));
    }

    public void updateByModel(Long pharmacyProductId, PharmacyProduct product) {
        PharmacyProduct model = getModel(pharmacyProductId);
        model.setLastDateTime(LocalDateTime.now());
        repository.save(model);
    }

    public List<PharmacyProduct> getAllModels(Set<Long> ids) {
        return repository.findAllById(ids);
    }

    public void saveOrUpdateAllModel(List<PharmacyProduct> pharmacyProducts) {
        repository.saveAll(pharmacyProducts);
    }
}
