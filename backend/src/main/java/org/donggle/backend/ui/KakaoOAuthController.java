package org.donggle.backend.ui;

import lombok.RequiredArgsConstructor;
import org.donggle.backend.application.service.oauth.kakao.KakaoOAuthService;
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
@RequestMapping(("/oauth/login/kakao"))
@RequiredArgsConstructor
public class KakaoOAuthController {
    private final KakaoOAuthService kakaoOAuthService;

    @GetMapping
    public ResponseEntity<Void> oauthRedirectKakao(@RequestParam final String redirect_uri) {
        final String redirectUri = kakaoOAuthService.createAuthorizeRedirectUri(redirect_uri);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, redirectUri)
                .build();
    }

    @PostMapping
    public ResponseEntity<Void> oauthLoginKakao(@RequestBody final OAuthAccessTokenRequest oAuthAccessTokenRequest) {
        kakaoOAuthService.login(oAuthAccessTokenRequest);
        return ResponseEntity.ok().build();
    }
}
