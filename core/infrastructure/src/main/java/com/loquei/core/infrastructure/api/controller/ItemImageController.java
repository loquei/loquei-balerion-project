package com.loquei.core.infrastructure.api.controller;

import com.loquei.common.validation.handler.Notification;
import com.loquei.core.application.item.image.create.CreateItemImageCommand;
import com.loquei.core.application.item.image.create.CreateItemImageOutput;
import com.loquei.core.application.item.image.create.CreateItemImageUseCase;
import com.loquei.core.application.item.image.delete.DeleteItemImageUseCase;
import com.loquei.core.application.item.image.retrieve.images.RetrieveItemImagesUseCase;
import com.loquei.core.application.item.image.retrieve.view.ViewItemImageUseCase;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.infrastructure.api.ItemImageAPI;
import com.loquei.core.infrastructure.item.image.model.ItemImageLinksResponse;
import com.loquei.core.infrastructure.utils.FileUtils;
import java.net.URI;
import java.util.function.Function;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ItemImageController implements ItemImageAPI {

    private final CreateItemImageUseCase createItemImageUseCase;
    private final DeleteItemImageUseCase deleteItemImageUseCase;
    private final RetrieveItemImagesUseCase retrieveItemImagesUseCase;
    private final ViewItemImageUseCase viewItemImageUseCase;

    public ItemImageController(
            final CreateItemImageUseCase createItemImageUseCase,
            final DeleteItemImageUseCase deleteItemImageUseCase,
            final RetrieveItemImagesUseCase retrieveItemImagesUseCase,
            final ViewItemImageUseCase viewItemImageUseCase) {
        this.createItemImageUseCase = createItemImageUseCase;
        this.deleteItemImageUseCase = deleteItemImageUseCase;
        this.retrieveItemImagesUseCase = retrieveItemImagesUseCase;
        this.viewItemImageUseCase = viewItemImageUseCase;
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
    public ResponseEntity<Resource> visualizeFile(String id) {
        final var output = viewItemImageUseCase.execute(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(output.fileType()))
                .body(new ByteArrayResource(output.data()));
    }

    @Override
    public ResponseEntity<ItemImageLinksResponse> getLinksByItem(String imgId) {
        final var output = this.retrieveItemImagesUseCase.execute(imgId);

        final var response = new ItemImageLinksResponse(output.ids().stream()
                .map(id -> URI.create("/items/images/view/" + id).toString())
                .toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public void deleteById(final String id) {
        this.deleteItemImageUseCase.execute(id);
    }
}
