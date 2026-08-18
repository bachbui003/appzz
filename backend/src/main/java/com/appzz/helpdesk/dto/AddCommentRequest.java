package com.appzz.helpdesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddCommentRequest(@NotNull Long authorId, @NotBlank String content) {}
