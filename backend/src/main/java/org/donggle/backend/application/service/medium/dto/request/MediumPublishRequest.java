package org.donggle.backend.application.service.medium.dto.request;

public record MediumPublishRequest(
        MediumRequestHeader header,
        MediumRequestBody body
) {
}
