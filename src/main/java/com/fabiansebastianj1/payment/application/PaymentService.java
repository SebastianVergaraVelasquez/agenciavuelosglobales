package com.fabiansebastianj1.payment.application;

import java.util.List;
import java.util.Optional;

import com.fabiansebastianj1.payment.domain.models.Payment;
import com.fabiansebastianj1.payment.infraestructure.PaymentRepository;

public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void createPayment(Payment payment){
        paymentRepository.save(payment);
    }

    public void updatePayment(Payment payment){
        paymentRepository.update(payment);
    }

    public void deletePayment(int id){
        paymentRepository.delete(id);
    }

    public Optional<Payment> getPaymentById(int id){
        return paymentRepository.findById(id);
    }

    public List<Payment> getAllPaymnents(){
        return paymentRepository.findAll();
    }

}
