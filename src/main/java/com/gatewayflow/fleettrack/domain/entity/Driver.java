package com.gatewayflow.fleettrack.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "drivers")
public class Driver extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 30)
    private String phone;

    @Column(nullable = false, unique = true, length = 50)
    private String licenseNumber;

    private LocalDate licenseExpiryDate;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Vehicle> vehicles = new ArrayList<>();
}