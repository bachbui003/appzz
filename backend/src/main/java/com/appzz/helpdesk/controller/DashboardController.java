package com.appzz.helpdesk.controller;

import com.appzz.helpdesk.model.TicketStatus;
import com.appzz.helpdesk.repository.TicketRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final TicketRepository tickets;
    public DashboardController(TicketRepository tickets) { this.tickets = tickets; }
    @GetMapping("/summary")
    public Map<String, Object> summary() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("totalTickets", tickets.count());
        for (TicketStatus status : TicketStatus.values()) {
            result.put(status.name().toLowerCase(Locale.ROOT), tickets.countByStatus(status));
        }
        return result;
    }
}
