package com.fabiansebastianj1.payment.infraestructure;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.payment.domain.models.Payment;

public interface PaymentRepository {
    void save(Payment payment);
    void update(Payment payment);
    void delete(int id);
    Optional<Payment> findById(int id);
    List<Payment> findAll();
}
