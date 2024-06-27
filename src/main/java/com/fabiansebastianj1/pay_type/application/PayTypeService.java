package com.fabiansebastianj1.pay_type.application;

import com.fabiansebastianj1.pay_type.domain.models.PayType;
import com.fabiansebastianj1.pay_type.infraestructure.PayTypeRepository;

import java.util.List;
import java.util.Optional;

public class PayTypeService {

    private final PayTypeRepository payTypeRepository;

    public PayTypeService(PayTypeRepository payTypeRepository) {
        this.payTypeRepository = payTypeRepository;
    }

    public Optional<PayType> getPayTypeById(int id) {
        return payTypeRepository.findById(id);
    }

    public List<PayType> getAllPayTypes() {
        return payTypeRepository.findAll();
    }
}
