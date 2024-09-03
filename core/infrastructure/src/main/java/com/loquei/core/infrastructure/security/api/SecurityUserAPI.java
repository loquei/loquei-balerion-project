package com.loquei.core.infrastructure.security.api;

import com.loquei.core.infrastructure.security.user.models.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/security/users")
@Tag(name = "Users")
public interface SecurityUserAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new user")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Created successfully"),
                @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    SecurityCreateUserResponse create(@RequestBody SecurityCreateUserRequest input);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a category by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
                @ApiResponse(responseCode = "404", description = "User was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    SecurityGetUserResponse getById(@PathVariable(name = "id") String id);

    @GetMapping(value = "{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a category by it's email")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
                @ApiResponse(responseCode = "404", description = "User was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    SecurityGetUserResponse getByEmail(@PathVariable(name = "email") String email);

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
    SecurityUpdateUserResponse update(
            @PathVariable(name = "id") String id, @RequestBody SecurityUpdateUserRequest input);

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
