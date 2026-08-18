package com.appzz.helpdesk.controller;

import com.appzz.helpdesk.dto.*;
import com.appzz.helpdesk.model.*;
import com.appzz.helpdesk.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;
    public TicketController(TicketService ticketService) { this.ticketService = ticketService; }

    @GetMapping
    public List<Ticket> listTickets() { return ticketService.listTickets(); }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Long id) { return ticketService.getTicket(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket createTicket(@Valid @RequestBody CreateTicketRequest request) { return ticketService.createTicket(request); }

    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketComment addComment(@PathVariable Long id, @Valid @RequestBody AddCommentRequest request) {
        return ticketService.addComment(id, request);
    }

    @PostMapping("/{id}/attachments")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketAttachment addAttachment(@PathVariable Long id, @Valid @RequestBody CreateAttachmentRequest request) {
        return ticketService.addAttachment(id, request);
    }

    @PatchMapping("/{id}/assign")
    public Ticket assignTicket(@PathVariable Long id, @Valid @RequestBody AssignTicketRequest request) {
        return ticketService.assignTicket(id, request);
    }

    @PatchMapping("/{id}/status")
    public Ticket updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateTicketStatusRequest request) {
        return ticketService.updateStatus(id, request);
    }
}
