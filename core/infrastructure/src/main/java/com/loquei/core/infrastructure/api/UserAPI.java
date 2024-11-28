package com.loquei.core.infrastructure.api;

import com.loquei.common.pagination.Pagination;
import com.loquei.core.infrastructure.user.models.CreateUserRequest;
import com.loquei.core.infrastructure.user.models.UpdateUserRequest;
import com.loquei.core.infrastructure.user.models.UserListResponse;
import com.loquei.core.infrastructure.user.models.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "users")
@Tag(name = "Users")
public interface UserAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new user")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Created successfully"),
                @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> create(@RequestBody CreateUserRequest input);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all users paginated")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Listed successfully"),
                @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    Pagination<UserListResponse> list(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "username") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a user by identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
                @ApiResponse(responseCode = "404", description = "User was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    UserResponse getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update an user by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "User updated successfully"),
                @ApiResponse(responseCode = "404", description = "User was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> update(@PathVariable(name = "id") String id, @RequestBody UpdateUserRequest input);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an user by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "User deleted successfully"),
                @ApiResponse(responseCode = "404", description = "User was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    void deleteById(@PathVariable(name = "id") String id);
}
