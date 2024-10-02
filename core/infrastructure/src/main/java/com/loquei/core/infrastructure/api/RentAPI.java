package com.loquei.core.infrastructure.api;

import com.loquei.common.pagination.Pagination;
import com.loquei.core.infrastructure.rent.persistence.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "rentals")
@Tag(name = "Users")
public interface RentAPI {


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new rent")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Created successfully"),
                    @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> rent (@RequestBody CreateRentRequest input);

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all rentals paginated")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Listed successfully"),
                    @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    Pagination<RentListResponse> listAllRentals(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "username") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction,
            @RequestParam(name = "userId")final String userId);
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a rent by it's identifier")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "User was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    RentResponse getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "accept a rent")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully"),
                    @ApiResponse(responseCode = "404", description = "User was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> acceptRent(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "refuse a rent")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully"),
                    @ApiResponse(responseCode = "404", description = "User was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> refuseRent(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cancel a rent")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully"),
                    @ApiResponse(responseCode = "404", description = "User was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> cancelRent(@RequestBody CancelRentRequest input);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a rental date")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully"),
                    @ApiResponse(responseCode = "404", description = "User was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> UpdateRentalDate(@RequestBody UpdateRentalDateRequest input);




}
