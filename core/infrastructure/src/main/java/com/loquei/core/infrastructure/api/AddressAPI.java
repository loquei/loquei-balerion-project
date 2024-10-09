package com.loquei.core.infrastructure.api;

import com.loquei.core.infrastructure.user.address.models.AddressResponse;
import com.loquei.core.infrastructure.user.address.models.CreateAddressRequest;
import com.loquei.core.infrastructure.user.address.models.UpdateAddressRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "addresses")
@Tag(name = "Addresses")
public interface AddressAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new Address")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Created successfully"),
                @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> create(@RequestBody CreateAddressRequest input);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a Address by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Address retrieved successfully"),
                @ApiResponse(responseCode = "404", description = "Address was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    AddressResponse getById(@PathVariable(name = "id") String id);

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a Address by it's user identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Address retrieved successfully"),
                @ApiResponse(responseCode = "404", description = "Address was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    List<AddressResponse> findAddressByUserId(@PathVariable(name = "userId") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update an Address by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Address updated successfully"),
                @ApiResponse(responseCode = "404", description = "Address was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> update(@PathVariable(name = "id") String id, @RequestBody UpdateAddressRequest input);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an Address by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
                @ApiResponse(responseCode = "404", description = "Address was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    void deleteById(@PathVariable(name = "id") String id);
}
