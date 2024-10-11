package com.loquei.core.infrastructure.api;

import com.loquei.common.pagination.Pagination;
import com.loquei.core.infrastructure.rating.models.CreateRatingRequest;
import com.loquei.core.infrastructure.rating.models.RatingListResponse;
import com.loquei.core.infrastructure.rating.models.RatingResponse;
import com.loquei.core.infrastructure.rating.models.UpdateRatingRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "ratings")
@Tag(name = "Ratings")
public interface RatingAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new rating")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Created successfully"),
                @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> create(@RequestBody CreateRatingRequest input);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all ratings paginated")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Listed successfully"),
                @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    Pagination<RatingListResponse> list(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "description") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction);

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a rating by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Rating retrieved successfully"),
                @ApiResponse(responseCode = "404", description = "Rating was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    RatingResponse getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a category by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Rating updated successfully"),
                @ApiResponse(responseCode = "404", description = "Rating was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    ResponseEntity<?> updateById(@PathVariable(name = "id") String id, @RequestBody UpdateRatingRequest input);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a rating by it's identifier")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
                @ApiResponse(responseCode = "404", description = "Category was not found"),
                @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            })
    void deleteById(@PathVariable(name = "id") String id);
}
