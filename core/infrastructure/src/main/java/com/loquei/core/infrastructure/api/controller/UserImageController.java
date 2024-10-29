package com.loquei.core.infrastructure.api.controller;

import com.loquei.common.validation.handler.Notification;
import com.loquei.core.application.user.image.create.CreateUserImageCommand;
import com.loquei.core.application.user.image.create.CreateUserImageOutput;
import com.loquei.core.application.user.image.create.CreateUserImageUseCase;
import com.loquei.core.application.user.image.delete.DeleteUserImageUseCase;
import com.loquei.core.application.user.image.retrieve.view.ViewUserImageUseCase;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.infrastructure.api.UserImageAPI;
import com.loquei.core.infrastructure.utils.FileUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.function.Function;

@RestController
public class UserImageController implements UserImageAPI {

    private final CreateUserImageUseCase createUserImageUseCase;
    private final ViewUserImageUseCase viewUserImageUseCase;
    private final DeleteUserImageUseCase deleteUserImageUseCase;

    public UserImageController(
            final CreateUserImageUseCase createUserImageUseCase,
            final ViewUserImageUseCase viewUserImageUseCase,
            final DeleteUserImageUseCase deleteUserImageUseCase
    ) {
        this.createUserImageUseCase = createUserImageUseCase;
        this.viewUserImageUseCase = viewUserImageUseCase;
        this.deleteUserImageUseCase = deleteUserImageUseCase;
    }

    @Override
    public ResponseEntity<?> create(final String userId, final MultipartFile file) {
        final var fileName = FileUtils.extractFileName(file);
        final var fileExtension = FileUtils.extractFileType(file);
        final var fileContent = FileUtils.extractFileContent(file);

        final var command = CreateUserImageCommand.with(UserId.from(userId), fileName, fileExtension, fileContent);

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateUserImageOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return this.createUserImageUseCase.execute(command).fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<Resource> visualizeFile(final String id) {
        final var output = viewUserImageUseCase.execute(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(output.fileType()))
                .body(new ByteArrayResource(output.data()));
    }

    @Override
    public void deleteById(final String id) {
        this.deleteUserImageUseCase.execute(id);
    }

}
