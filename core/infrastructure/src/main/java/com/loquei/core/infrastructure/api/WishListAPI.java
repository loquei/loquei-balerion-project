package com.loquei.core.infrastructure.api;

import com.loquei.core.infrastructure.item.wishList.model.CreateWishListRequest;
import com.loquei.core.infrastructure.item.wishList.model.DeleteWishListRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "wishlist")
@Tag(name = "Wishlist")
public interface WishListAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a new item to wishlist")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Created successfully"),
                    @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    Object create(@RequestBody CreateWishListRequest input);

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an wishlist by it's identifier")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Wishlist item deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Wishlist item was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    void deleteById(@RequestBody DeleteWishListRequest input);

}
