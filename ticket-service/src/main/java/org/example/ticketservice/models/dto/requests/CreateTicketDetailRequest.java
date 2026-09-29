package org.example.ticketservice.models.dto.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateTicketDetailRequest(
        @NotNull(message = "Trip id is required")
        @Min(value = 1, message = "Trip id must be greater than 0")
        Long tripId,

        @NotNull(message = "Seat is required")
        @Min(value = 1, message = "Seat must be greater than 0")
        Integer seat
) {
}
