package com.loquei.core.application.user.address.delete;

import com.loquei.common.UseCase;
import com.loquei.common.validation.handler.Notification;
import java.util.Optional;

public abstract class DeleteAddressUseCase extends UseCase<String, Optional<Notification>> {}
