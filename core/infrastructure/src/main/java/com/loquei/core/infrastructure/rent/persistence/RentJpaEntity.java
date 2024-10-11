package com.loquei.core.infrastructure.rent.persistence;

import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentId;
import com.loquei.core.domain.rent.RentStatus;
import com.loquei.core.domain.user.UserId;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity(name = "Rent")
@Table(name = "rentals")
public class RentJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "lessor_id", nullable = false)
    private String lessorId;

    @Column(name = "lessee_id", nullable = false)
    private String lesseeId;

    @Column(name = "item_id", nullable = false)
    private String itemId;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "total_value", nullable = false)
    private BigDecimal totalValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RentStatus status;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    public RentJpaEntity() {}

    public RentJpaEntity(
            final String id,
            final String lessorId,
            final String lesseeId,
            final String itemId,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final BigDecimal totalValue,
            final RentStatus status,
            final String cancellationReason,
            final Instant createdAt,
            final Instant updatedAt) {
        this.id = id;
        this.lessorId = lessorId;
        this.lesseeId = lesseeId;
        this.itemId = itemId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalValue = totalValue;
        this.status = status;
        this.cancellationReason = cancellationReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static RentJpaEntity from(final Rent rent) {
        return new RentJpaEntity(
                rent.getId().getValue(),
                rent.getLessor().getValue(),
                rent.getLessee().getValue(),
                rent.getItem().getValue(),
                rent.getStartDate(),
                rent.getEndDate(),
                rent.getTotalValue(),
                rent.getStatus(),
                rent.getCancellationReason(),
                rent.getCreatedAt(),
                rent.getUpdatedAt());
    }

    public Rent toAggregate() {
        return Rent.with(
                RentId.from(getId()),
                UserId.from(getLessorId()),
                UserId.from(getLesseeId()),
                ItemId.from(getItemId()),
                getStartDate(),
                getEndDate(),
                getTotalValue(),
                getStatus(),
                getCancellationReason(),
                getCreatedAt(),
                getUpdatedAt());
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getLessorId() {
        return lessorId;
    }

    public void setLessorId(final String lessorId) {
        this.lessorId = lessorId;
    }

    public String getLesseeId() {
        return lesseeId;
    }

    public void setLesseeId(final String lesseeId) {
        this.lesseeId = lesseeId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(final String itemId) {
        this.itemId = itemId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(final BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public RentStatus getStatus() {
        return status;
    }

    public void setStatus(final RentStatus status) {
        this.status = status;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(final String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
