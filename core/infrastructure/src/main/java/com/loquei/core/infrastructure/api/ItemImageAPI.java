package com.loquei.core.infrastructure.api;

import com.loquei.core.infrastructure.item.image.model.ItemImageLinksResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "items/images")
@Tag(name = "Item Images")
public interface ItemImageAPI {

    @PostMapping(
            value = "/{itemId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new item image")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Created successfully"),
                @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> create(@PathVariable("itemId") String itemId, @RequestBody MultipartFile file);

    @GetMapping("/view/{itemImageId}")
    ResponseEntity<Resource> visualizeFile(@PathVariable("itemImageId") String id);

    @GetMapping("/links/{itemId}")
    ResponseEntity<ItemImageLinksResponse> getLinksByItem(@PathVariable("itemId") String id);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an item image by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "Item Image deleted successfully"),
                @ApiResponse(responseCode = "404", description = "Item Image was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    void deleteById(@PathVariable(name = "id") String id);
}
