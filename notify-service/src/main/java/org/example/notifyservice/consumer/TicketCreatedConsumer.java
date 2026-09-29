package org.example.notifyservice.consumer;

import lombok.RequiredArgsConstructor;
import org.example.notifyservice.dto.TicketEvent;
import org.example.notifyservice.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketCreatedConsumer {
    private final EmailService emailService;

    @KafkaListener(topics = "ticket-created", groupId = "notify-group")
    public void consume(TicketEvent ticketEvent) {
        // Kiểm tra người nhận nếu null hoặc rỗng khi không gửi email
        if (ticketEvent.payload() == null || ticketEvent.payload().isBlank()) {
            return;
        }
        // Gọi email service để gửi mail
        emailService.sendOrderCreatedEmail(ticketEvent.payload(), "Đặt vé thành công", "Đặt vé thành công với mã vé: " + ticketEvent.ticketId());
    }
}
