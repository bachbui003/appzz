package com.appzz.helpdesk.dto;

import com.appzz.helpdesk.model.TicketStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateTicketStatusRequest(@NotNull Long changedById, @NotNull TicketStatus status) {}
