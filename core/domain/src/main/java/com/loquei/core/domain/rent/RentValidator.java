package com.loquei.core.domain.rent;

import com.loquei.common.validation.Error;
import com.loquei.common.validation.ValidationHandler;
import com.loquei.common.validation.Validator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RentValidator extends Validator {

    private static final int MAX_REASON_LENGTH = 255;
    private static final BigDecimal MIN_TOTAL_VALUE = BigDecimal.ZERO;
    private final Rent rent;

    public RentValidator(final Rent rent, final ValidationHandler handler) {
        super(handler);
        this.rent = rent;
    }

    @Override
    public void validate() {
        checkLessor();
        checkLessee();
        checkItem();
        checkDates();
        checkTotalValue();
        checkStatus();
        checkCancellationReason();
    }

    private void checkLessor() {
        if (rent.getLessor() == null) {
            this.validationHandler().append(new Error("'lessor' should not be null"));
        }
    }

    private void checkLessee() {
        if (rent.getLessee() == null) {
            this.validationHandler().append(new Error("'lessee' should not be null"));
        }
    }

    private void checkItem() {
        if (rent.getItem() == null) {
            this.validationHandler().append(new Error("'item' should not be null"));
        }
    }

    private void checkDates() {
        if (rent.getStartDate() == null || rent.getEndDate() == null) {
            this.validationHandler().append(new Error("'startDate' and 'endDate' should not be null"));
            return;
        }

        if (rent.getStartDate().isAfter(rent.getEndDate())) {
            this.validationHandler().append(new Error("'startDate' must be before 'endDate'"));
        }

        if (rent.getStartDate().isBefore(LocalDateTime.now())) {
            this.validationHandler().append(new Error("'startDate' must be today or in the future"));
        }

        if (rent.getEndDate().isBefore(LocalDateTime.now())) {
            this.validationHandler().append(new Error("'endDate' must be today or in the future"));
        }
    }

    private void checkTotalValue() {
        if (rent.getTotalValue() == null) {
            this.validationHandler().append(new Error("'totalValue' should not be null"));
        } else if (rent.getTotalValue().compareTo(MIN_TOTAL_VALUE) <= 0) {
            this.validationHandler().append(new Error("'totalValue' must be greater than zero"));
        }
    }

    private void checkStatus() {
        if (rent.getStatus() == null) {
            this.validationHandler().append(new Error("'status' should not be null"));
        }
    }

    private void checkCancellationReason() {
        final String reason = rent.getCancellationReason();
        if (rent.getStatus() == RentStatus.CANCELLED && (reason == null || reason.isBlank())) {
            this.validationHandler().append(new Error("'cancellationReason' is required when rent is cancelled"));
        } else if (reason != null && reason.length() > MAX_REASON_LENGTH) {
            this.validationHandler().append(new Error("'cancellationReason' must not exceed " + MAX_REASON_LENGTH + " characters"));
        }
    }
}
