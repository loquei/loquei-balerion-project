package com.loquei.core.domain.rent;

import com.loquei.common.AggregateRoot;
import com.loquei.common.utils.InstantUtils;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.user.User;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public class Rent extends AggregateRoot<RentId> {

    private User lessor;
    private User lessee;
    private Item item;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal totalValue;
    private RentStatus status;
    private String cancellationReason;
    private Instant createdAt;
    private Instant updatedAt;

    // Construtor
    public Rent(
                final RentId anid,
                final User lessor,
                final User lessee,
                final Item item,
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
            final User lessor,
            final User lessee,
            final Item item,
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


    public Rent update(
        final LocalDateTime startDate,
        final LocalDateTime endDate,
        final BigDecimal totalValue,
        final RentStatus status) {

       this.startDate = startDate;
       this.endDate = endDate;
       this.totalValue = totalValue;
       this.status = status;
       this.updatedAt = InstantUtils.now();

       return this;
    }



    public User getLessor() {
        return lessor;
    }

    public User getLessee() {
        return lessee;
    }

    public Item getItem() {
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


    public void cancel(String reason) {
        if (this.status.equals(RentStatus.PENDING)) {
            this.status = RentStatus.CANCELLED;
            this.cancellationReason = reason;
            this.updatedAt = Instant.now();
        } else {
            throw new IllegalStateException("A locação já foi iniciada ou finalizada e não pode ser cancelada.");
        }
    }

    public void accept() {
        if (this.status.equals(RentStatus.PENDING)) {
            this.status = RentStatus.ACCEPTED;
            this.updatedAt = Instant.now();
        } else {
            throw new IllegalStateException("A locação não pode ser aceita.");
        }
    }

    @Override
    public void validate(ValidationHandler aHandler) {

    }
}
