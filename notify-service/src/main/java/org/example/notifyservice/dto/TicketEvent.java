package org.example.notifyservice.dto;

public record TicketEvent(
        Long ticketId,
        String payload
) {
}
