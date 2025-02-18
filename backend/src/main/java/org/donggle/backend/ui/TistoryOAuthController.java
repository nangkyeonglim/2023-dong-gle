package org.donggle.backend.ui;

import lombok.RequiredArgsConstructor;
import org.donggle.backend.application.service.oauth.tistory.TistoryOAuthService;
import org.donggle.backend.application.service.request.OAuthAccessTokenRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connections/tistory")
@RequiredArgsConstructor
public class TistoryOAuthController {
    private final TistoryOAuthService tistoryOAuthService;

    @GetMapping
    public ResponseEntity<Void> connectionsRedirectTistory(@RequestParam final String redirect_uri) {
        final String redirectUri = tistoryOAuthService.createAuthorizeRedirectUri(redirect_uri);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, redirectUri)
                .build();
    }

    @PostMapping
    public ResponseEntity<Void> connectionsAddTistory(@RequestBody final OAuthAccessTokenRequest oAuthAccessTokenRequest) {
        final String accessToken = tistoryOAuthService.getAccessToken(oAuthAccessTokenRequest);
        return ResponseEntity.ok().build();
    }
}
