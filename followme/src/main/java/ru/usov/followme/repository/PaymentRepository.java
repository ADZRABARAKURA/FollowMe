package ru.usov.followme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.usov.followme.domain.payment.Payment;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, String> {

}
