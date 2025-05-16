package ru.usov.followme.domain.payment;

import jakarta.persistence.*;
import lombok.*;
import ru.usov.followme.enums.PaymentStatus;
import ru.usov.followme.util.Money;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Payment {
    @Id
    private String paymentId;

    @Column(nullable = false)
    private UUID subscriptionId;

    private String paymentLink;

    @Embedded
    private Money price;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;
}
