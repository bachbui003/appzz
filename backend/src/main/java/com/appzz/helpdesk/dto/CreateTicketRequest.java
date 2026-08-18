package com.appzz.helpdesk.dto;

import com.appzz.helpdesk.model.TicketPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTicketRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String category,
        TicketPriority priority,
        @NotNull Long requesterId,
        Long departmentId
) {}
