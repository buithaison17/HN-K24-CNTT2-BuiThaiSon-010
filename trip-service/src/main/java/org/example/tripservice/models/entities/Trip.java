package org.example.tripservice.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trips")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure")
    private String departure;

    @Column(name = "destination")
    private String destination;

    @Column(name = "ticket_price")
    private Double ticketPrice;

    @Column(name = "available_seats")
    private Integer availableSeats;

}
