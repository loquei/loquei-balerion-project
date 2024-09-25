package com.loquei.core.domain.rent;

import com.loquei.common.AggregateRoot;
import com.loquei.common.utils.InstantUtils;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.user.UserId;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public class Rent extends AggregateRoot<RentId> {

    private final UserId lessor;
    private final UserId lessee;
    private final ItemId item;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal totalValue;
    private RentStatus status;
    private String cancellationReason;
    private final Instant createdAt;
    private Instant updatedAt;

    public Rent(
                final RentId anid,
                final UserId lessor,
                final UserId lessee,
                final ItemId item,
                final LocalDateTime startDate,
                final LocalDateTime endDate,
                final BigDecimal totalValue,
                final RentStatus status,
                final String cancellationReason,
                final Instant createdAt,
                final Instant updatedAt) {
        super(anid);
        this.lessor = lessor;
        this.lessee = lessee;
        this.item = item;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalValue = totalValue;
        this.status = status;
        this.cancellationReason = cancellationReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Rent with(
            final RentId anId,
            final UserId lessor,
            final UserId lessee,
            final ItemId item,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final BigDecimal totalValue,
            final RentStatus status,
            final String cancellationReason,
            final Instant createdAt,
            final Instant updatedAt) {
        return new Rent(anId, lessor, lessee, item, startDate, endDate, totalValue, status, cancellationReason, createdAt, updatedAt);
    }

    public static Rent with(final Rent rent) {
        return new Rent(
                rent.getId(),
                rent.getLessor(),
                rent.getLessee(),
                rent.getItem(),
                rent.getStartDate(),
                rent.getEndDate(),
                rent.getTotalValue(),
                rent.getStatus(),
                rent.getCancellationReason(),
                rent.getCreatedAt(),
                rent.getUpdatedAt());
    }

    public static Rent newRent(
            final UserId lessor,
            final UserId lessee,
            final ItemId item,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final BigDecimal totalValue) {

        final var id = RentId.unique();
        final var now = InstantUtils.now();

        return new Rent(
                id,
                lessor,
                lessee,
                item,
                startDate,
                endDate,
                totalValue,
                RentStatus.PENDING,
                null,
                now,
                now);
    }


    public Rent updateRentalDate(
        final LocalDateTime startDate,
        final LocalDateTime endDate,
        final BigDecimal totalValue) {

       this.startDate = startDate;
       this.endDate = endDate;
       this.totalValue = totalValue;
       this.status = RentStatus.PENDING;
       this.updatedAt = InstantUtils.now();

       return this;
    }

    public void cancelRental(final String cancellationReason) {
        this.status = RentStatus.CANCELLED;
        this.cancellationReason = cancellationReason;
        this.updatedAt = InstantUtils.now();
    }

    @Override
    public void validate(ValidationHandler aHandler) {

    }

    public UserId getLessor() {
        return lessor;
    }

    public UserId getLessee() {
        return lessee;
    }

    public ItemId getItem() {
        return item;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public RentStatus getStatus() {
        return status;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }


}
