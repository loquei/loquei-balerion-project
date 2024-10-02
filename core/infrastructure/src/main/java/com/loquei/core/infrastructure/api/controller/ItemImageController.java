package com.loquei.core.infrastructure.api.controller;

import com.loquei.common.validation.handler.Notification;
import com.loquei.core.application.item.create.CreateItemOutput;
import com.loquei.core.application.item.image.create.CreateItemImageCommand;
import com.loquei.core.application.item.image.create.CreateItemImageOutput;
import com.loquei.core.application.item.image.create.CreateItemImageUseCase;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.item.image.ItemImage;
import com.loquei.core.infrastructure.api.ItemImageAPI;
import com.loquei.core.infrastructure.item.image.persistence.ItemImageJpaEntity;
import com.loquei.core.infrastructure.item.image.persistence.ItemImageRepository;
import com.loquei.core.infrastructure.utils.FileUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.function.Function;

@RestController
public class ItemImageController implements ItemImageAPI {

    private final CreateItemImageUseCase createItemImageUseCase;
    private final ItemImageRepository itemImageRepository;

    public ItemImageController(CreateItemImageUseCase createItemImageUseCase, ItemImageRepository itemImageRepository) {
        this.createItemImageUseCase = createItemImageUseCase;
        this.itemImageRepository = itemImageRepository;
    }

    @Override
    public ResponseEntity<?> create(final String itemId, final MultipartFile file) {
        final var fileName = FileUtils.extractFileName(file);
        final var fileExtension = FileUtils.extractFileType(file);
        final var fileContent = FileUtils.extractFileContent(file);

        final var command = CreateItemImageCommand.with(ItemId.from(itemId), fileName, fileExtension, fileContent);

        final Function<Notification, ResponseEntity<?>> onError =
                notification -> ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateItemImageOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/items/" + output.id())).body(output);

        return this.createItemImageUseCase.execute(command).fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<Resource> visualizeFile(Integer id) {
        ItemImageJpaEntity itemImage = itemImageRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("error"));

        String mimeType = itemImage.getFileType();
        if (!mimeType.contains("/")) {
            mimeType = "application/octet-stream";
        }

        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .body(new ByteArrayResource(itemImage.getData()));
    }
}
