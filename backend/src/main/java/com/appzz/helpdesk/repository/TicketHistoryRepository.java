package com.appzz.helpdesk.repository;

import com.appzz.helpdesk.model.TicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Long> {
}
