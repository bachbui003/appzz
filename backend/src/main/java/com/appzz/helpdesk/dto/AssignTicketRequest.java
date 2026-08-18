package com.appzz.helpdesk.dto;

import jakarta.validation.constraints.NotNull;

public record AssignTicketRequest(@NotNull Long assigneeId, @NotNull Long changedById) {}
