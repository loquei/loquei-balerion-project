package com.loquei.core.infrastructure.security.api;

import com.loquei.core.infrastructure.security.auth.models.SecurityAuthenticateRequest;
import com.loquei.core.infrastructure.security.auth.models.SecurityAuthenticateResponse;
import com.loquei.core.infrastructure.security.auth.models.SecurityGenerateAuthCodeRequest;
import com.loquei.core.infrastructure.security.auth.models.SecurityGenerateAuthCodeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/security/auth")
@Tag(name = "Auth")
public interface SecurityAuthAPI {

    @PostMapping("/generate")
    @Operation(summary = "Request authentication code message on user email")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "Auth code sent successfully"),
                @ApiResponse(responseCode = "404", description = "User was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    SecurityGenerateAuthCodeResponse generateAuthCode(@RequestBody SecurityGenerateAuthCodeRequest requestBody);

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate user and retrieve authentication token")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Token generated successfully"),
                @ApiResponse(responseCode = "404", description = "User was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    SecurityAuthenticateResponse authenticate(@RequestBody SecurityAuthenticateRequest requestBody);
}
