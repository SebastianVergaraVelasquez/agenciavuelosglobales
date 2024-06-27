package com.fabiansebastianj1.pay_type.infraestructure;

import com.fabiansebastianj1.pay_type.domain.models.PayType;

import java.util.List;
import java.util.Optional;

public interface PayTypeRepository {
    Optional<PayType> findById(int id);
    List<PayType> findAll();
}
