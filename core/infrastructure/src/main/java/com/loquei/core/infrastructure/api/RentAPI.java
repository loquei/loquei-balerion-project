package com.loquei.core.infrastructure.api;

import com.loquei.common.pagination.Pagination;
import com.loquei.core.infrastructure.rent.models.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping(value = "rentals")
@Tag(name = "Users")
public interface RentAPI {


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new rent")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Rent created successfully"),
                    @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> rent (@RequestBody CreateRentRequest input);

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all rentals paginated by user id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Listed successfully"),
                    @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    Pagination<RentListResponse> listAllRentalsByUserId(@PathVariable(name = "id") String id);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a rent by it's identifier")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Rent retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Rent was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    RentResponse getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "/accept/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "accept a rent")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Rent accepted successfully"),
                    @ApiResponse(responseCode = "404", description = "Rent was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> acceptRent(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "/refuse/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "refuse a rent")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Rent refused successfully"),
                    @ApiResponse(responseCode = "404", description = "Rent was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> refuseRent(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "/cancel/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cancel a rent")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Rent cancelled successfully"),
                    @ApiResponse(responseCode = "404", description = "Rent was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> cancelRent(@RequestBody CancelRentRequest input);

    @PutMapping(
            value = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a rental date")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Rent updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Rent was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> UpdateRentalDate(@RequestBody UpdateRentalDateRequest input);

    @GetMapping(value = "/availability/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Check if an item is available for rent")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Item is available"),
                    @ApiResponse(responseCode = "409", description = "Item is not available"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            }
    )
    ResponseEntity<?> isItemAvailableForRent(@PathVariable(name = "itemId") String itemId,
                                             @RequestParam(name = "startDate") LocalDateTime startDate,
                                             @RequestParam(name = "endDate") LocalDateTime endDate);





}
