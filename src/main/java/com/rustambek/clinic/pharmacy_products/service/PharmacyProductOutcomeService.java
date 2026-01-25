package com.rustambek.clinic.pharmacy_products.service;

import com.rustambek.clinic.billing.invoice.service.InvoiceService;
import com.rustambek.clinic.convertor.mapstruct.PharmacyProductOutcomeMapper;
import com.rustambek.clinic.exception.BadRequestException;
import com.rustambek.clinic.exception.DataNotFoundException;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductIncomeDto;
import com.rustambek.clinic.pharmacy_products.dto.PharmacyProductOutcomeReq;
import com.rustambek.clinic.pharmacy_products.entity.PharmacyProduct;
import com.rustambek.clinic.pharmacy_products.entity.PharmacyProductOutcome;
import com.rustambek.clinic.pharmacy_products.repository.PharmacyProductOutcomeRepository;
import com.rustambek.clinic.specification.PharmacyProductOutcomeSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PharmacyProductOutcomeService {
    private final PharmacyProductOutcomeRepository repository;
    private final PharmacyProductOutcomeMapper  mapper;
    private final PharmacyProductService pharmacyProductService;
    private final InvoiceService invoiceService;

    public PharmacyProductIncomeDto create(PharmacyProductOutcomeReq req) {
        PharmacyProduct product = pharmacyProductService.getModel(req.getPharmacyProductId());
        int quantity = getQuantityDoOutcome(product.getQuantity(),req.getQuantity());
        product.setQuantity(quantity);
        pharmacyProductService.updateByModel(req.getPharmacyProductId(), product);
        PharmacyProductOutcome entity = mapper.toEntity(req);
        entity.setIncomePrice(product.getIncomePrice()*req.getQuantity());
        entity.setSalePrice(product.getSalePrice()*req.getQuantity());
        return mapper.toDto(repository.save(entity));
    }

    private int getQuantityDoOutcome(Integer quantityCurrent, Integer quantityOfOutcome) {
        Integer result = quantityCurrent - quantityOfOutcome;
        if (result < 0) {
            throw new BadRequestException("Quantity out of range");
        }else  {
            return result;
        }
    }

    @Transactional(readOnly = true)
    public PharmacyProductIncomeDto getById(Long id) {
        PharmacyProductOutcome income = getModel(id);
        return mapper.toDto(income);
    }

    private PharmacyProductOutcome getModel(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("PharmacyProductOutcomeReq not found: " + id));
    }

    public PharmacyProductIncomeDto update(Long id, PharmacyProductOutcomeReq req) {
        PharmacyProductOutcome income = getModel(id);
        PharmacyProduct product = pharmacyProductService.getModel(req.getPharmacyProductId());
        int quantity = product.getQuantity() - req.getQuantity() + income.getQuantity();
        product.setQuantity(quantity);
        income.setIncomePrice(product.getIncomePrice()*req.getQuantity());
        income.setSalePrice(product.getSalePrice()*req.getQuantity());
        pharmacyProductService.updateByModel(req.getPharmacyProductId(), product);
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
        return mapper.toDtoPage(repository.findAll(PharmacyProductOutcomeSpecifications.byFilter(pharmacyProductId), pageable));
    }

    public void createMultiple(List<PharmacyProductOutcomeReq> pharmacyProductOutcomes, Long nurseMedicationId) {
        Long visitId = pharmacyProductOutcomes.get(0).getVisitId();
        Map<Long, Integer> pharmacyProductOutcomesMap =
                pharmacyProductOutcomes.stream()
                        .collect(Collectors.toMap(
                                PharmacyProductOutcomeReq::getPharmacyProductId,
                                PharmacyProductOutcomeReq::getQuantity
                        ));
        List<PharmacyProduct> pharmacyProducts = pharmacyProductService.getAllModels(pharmacyProductOutcomesMap.keySet());
        Set<PharmacyProductOutcome> models = new HashSet<>();

        for (PharmacyProduct pharmacyProduct : pharmacyProducts) {
            PharmacyProductOutcome model = new PharmacyProductOutcome();
            Integer currentQuantity = pharmacyProductOutcomesMap.get(pharmacyProduct.getId());
            pharmacyProduct.setQuantity(getQuantityDoOutcome(pharmacyProduct.getQuantity(),currentQuantity));
            model.setQuantity(currentQuantity);
            model.setIncomePrice(pharmacyProduct.getIncomePrice()*currentQuantity);
            model.setSalePrice(pharmacyProduct.getSalePrice()*currentQuantity);
            model.setPharmacyProduct(pharmacyProduct);
            model.setPharmacyProductId(pharmacyProduct.getId());
            model.setVisitId(visitId);
            model.setNurseMedicationId(nurseMedicationId);
            models.add(model);
        }
        pharmacyProductService.saveOrUpdateAllModel(pharmacyProducts);
        List<PharmacyProductOutcome> productOutcomes = repository.saveAllAndFlush(models);
        invoiceService.createInvoiceWithInvoiceItemsForProductOutcome(visitId,productOutcomes);

    }
}
