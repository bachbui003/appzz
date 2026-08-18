package com.appzz.helpdesk.repository;

import com.appzz.helpdesk.model.Ticket;
import com.appzz.helpdesk.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    long countByStatus(TicketStatus status);
}
