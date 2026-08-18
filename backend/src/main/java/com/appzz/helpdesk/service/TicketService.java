package com.appzz.helpdesk.service;

import com.appzz.helpdesk.dto.*;
import com.appzz.helpdesk.model.*;
import com.appzz.helpdesk.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository tickets;
    private final AppUserRepository users;
    private final DepartmentRepository departments;
    private final TicketCommentRepository comments;
    private final TicketAttachmentRepository attachments;
    private final TicketHistoryRepository history;

    public TicketService(TicketRepository tickets, AppUserRepository users, DepartmentRepository departments,
                         TicketCommentRepository comments, TicketAttachmentRepository attachments,
                         TicketHistoryRepository history) {
        this.tickets = tickets; this.users = users; this.departments = departments;
        this.comments = comments; this.attachments = attachments; this.history = history;
    }

    public List<Ticket> listTickets() { return tickets.findAll(); }
    public Ticket getTicket(Long id) { return tickets.findById(id).orElseThrow(() -> new NotFoundException("Ticket not found: " + id)); }

    @Transactional
    public Ticket createTicket(CreateTicketRequest request) {
        Ticket ticket = new Ticket();
        ticket.setTitle(request.title()); ticket.setDescription(request.description()); ticket.setCategory(request.category());
        ticket.setPriority(request.priority() == null ? TicketPriority.MEDIUM : request.priority());
        ticket.setRequester(findUser(request.requesterId()));
        if (request.departmentId() != null) ticket.setDepartment(findDepartment(request.departmentId()));
        return tickets.save(ticket);
    }

    @Transactional
    public Ticket assignTicket(Long ticketId, AssignTicketRequest request) {
        Ticket ticket = getTicket(ticketId);
        AppUser assignee = findUser(request.assigneeId());
        TicketStatus oldStatus = ticket.getStatus();
        ticket.setAssignee(assignee); ticket.setStatus(TicketStatus.ASSIGNED);
        saveHistory(ticket, findUser(request.changedById()), "ASSIGN", String.valueOf(oldStatus), assignee.getEmail());
        return ticket;
    }

    @Transactional
    public Ticket updateStatus(Long ticketId, UpdateTicketStatusRequest request) {
        Ticket ticket = getTicket(ticketId);
        TicketStatus oldStatus = ticket.getStatus();
        ticket.setStatus(request.status());
        saveHistory(ticket, findUser(request.changedById()), "STATUS_CHANGE", oldStatus.name(), request.status().name());
        return ticket;
    }

    @Transactional
    public TicketComment addComment(Long ticketId, AddCommentRequest request) {
        TicketComment comment = new TicketComment();
        comment.setTicket(getTicket(ticketId)); comment.setAuthor(findUser(request.authorId())); comment.setContent(request.content());
        return comments.save(comment);
    }

    @Transactional
    public TicketAttachment addAttachment(Long ticketId, CreateAttachmentRequest request) {
        TicketAttachment attachment = new TicketAttachment();
        attachment.setTicket(getTicket(ticketId)); attachment.setUploadedBy(findUser(request.uploadedById()));
        attachment.setFileName(request.fileName()); attachment.setFileUrl(request.fileUrl()); attachment.setContentType(request.contentType());
        return attachments.save(attachment);
    }

    private AppUser findUser(Long id) { return users.findById(id).orElseThrow(() -> new NotFoundException("User not found: " + id)); }
    private Department findDepartment(Long id) { return departments.findById(id).orElseThrow(() -> new NotFoundException("Department not found: " + id)); }
    private void saveHistory(Ticket ticket, AppUser changedBy, String action, String oldValue, String newValue) {
        TicketHistory item = new TicketHistory();
        item.setTicket(ticket); item.setChangedBy(changedBy); item.setAction(action); item.setOldValue(oldValue); item.setNewValue(newValue);
        history.save(item);
    }
}
