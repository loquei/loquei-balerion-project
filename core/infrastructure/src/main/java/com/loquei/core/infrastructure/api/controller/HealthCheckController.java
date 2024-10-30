package com.loquei.core.infrastructure.api.controller;

import com.loquei.core.infrastructure.api.HealthAPI;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController implements HealthAPI {

    @Override
    public void healthCheck() {}
}
