package com.appzz.helpdesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAttachmentRequest(@NotNull Long uploadedById, @NotBlank String fileName, @NotBlank String fileUrl, String contentType) {}
