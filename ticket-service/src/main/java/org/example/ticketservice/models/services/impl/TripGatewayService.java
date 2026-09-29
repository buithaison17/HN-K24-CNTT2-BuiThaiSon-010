package org.example.ticketservice.models.services.impl;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.example.ticketservice.clients.TripClient;
import org.example.ticketservice.exceptions.TripNotFoundException;
import org.example.ticketservice.exceptions.TripServiceException;
import org.example.ticketservice.models.dto.responses.TripResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripGatewayService {
    private final TripClient tripClient;

    @CircuitBreaker(name = "ticketService", fallbackMethod = "getTripByIdFallback")
    public TripResponse getTripById(Long tripId) {
        TripResponse tripResponse = tripClient.getTripById(tripId);
        if (tripResponse == null) {
            throw new TripNotFoundException(tripId);
        }
        return tripResponse;
    }

    public TripResponse getTripByIdFallback(Long tripId, Throwable throwable) {
        // Trường hợp không tìm thấy
        if (throwable instanceof FeignException.NotFound) {
            throw new TripNotFoundException(tripId);
        }
        // Trường hợp trip service lỗi
        throw new TripServiceException("Đã xảy ra lỗi với trip service");
    }
}
