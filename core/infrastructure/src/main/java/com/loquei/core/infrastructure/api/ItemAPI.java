package com.loquei.core.infrastructure.api;

import com.loquei.common.pagination.Pagination;
import com.loquei.core.infrastructure.item.models.CreateItemRequest;
import com.loquei.core.infrastructure.item.models.ItemListResponse;
import com.loquei.core.infrastructure.item.models.ItemResponse;
import com.loquei.core.infrastructure.item.models.UpdateItemRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "items")
@Tag(name = "Items")
public interface ItemAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new item")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Created successfully"),
                @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> create(@RequestBody CreateItemRequest input);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all items paginated")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Listed successfully"),
                @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    Pagination<ItemListResponse> list(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction,
            @RequestParam(name = "recentlyViewed", required = false, defaultValue = "false")
                    final Boolean recentlyViewed,
            @RequestParam(name = "userEmail", required = false, defaultValue = "email") final String userEmail);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a items by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Item retrieved successfully"),
                @ApiResponse(responseCode = "404", description = "User was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ItemResponse getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update an item by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "User updated successfully"),
                @ApiResponse(responseCode = "404", description = "User was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> update(@PathVariable(name = "id") String id, @RequestBody UpdateItemRequest input);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an item by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "User deleted successfully"),
                @ApiResponse(responseCode = "404", description = "User was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    void deleteById(@PathVariable(name = "id") String id);
}
