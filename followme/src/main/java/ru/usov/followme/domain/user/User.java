package ru.usov.followme.domain.user;

import jakarta.persistence.*;
import lombok.*;
import ru.usov.followme.domain.publication.Publication;
import ru.usov.followme.domain.subscription.Subscription;
import ru.usov.followme.domain.subscription.SubscriptionPlan;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private UUID keycloakSub;

    private String username;

    private String avatarFileId;

    //----------------User creations--------------------

    @OneToMany
    List<SubscriptionPlan> subscriptionsPlans;

    @OneToMany
    List<Publication> publications;

    //----------------User actions--------------------

    @OneToMany
    List<Subscription> subscriptions;

    @OneToMany
    List<Follow> follows;
}
