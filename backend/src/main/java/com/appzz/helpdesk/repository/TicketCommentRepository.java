package com.appzz.helpdesk.repository;

import com.appzz.helpdesk.model.TicketComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCommentRepository extends JpaRepository<TicketComment, Long> {
}
