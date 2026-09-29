package org.example.ticketservice.models.dto.requests;

public record TicketEvent(
        Long ticketId,
        String payload
) {
}
