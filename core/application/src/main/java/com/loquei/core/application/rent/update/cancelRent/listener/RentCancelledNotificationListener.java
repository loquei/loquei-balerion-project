package com.loquei.core.application.rent.update.cancelRent.listener;

import com.loquei.common.event.EventListener;
import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.email.EmailGateway;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.rent.RentId;
import com.loquei.core.domain.rent.event.RentCancelledNotificationEvent;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;

import java.util.function.Supplier;

import static com.loquei.core.application.rent.update.cancelRent.listener.LesseeRentCancelledEmailHelper.buildLesseeEmail;
import static com.loquei.core.application.rent.update.cancelRent.listener.LessorRentCancelledEmailHelper.buildLessorEmail;
import static java.util.Objects.requireNonNull;

public class RentCancelledNotificationListener implements EventListener<RentCancelledNotificationEvent> {

    private final RentGateway rentGateway;
    private final UserGateway userGateway;
    private final ItemGateway itemGateway;
    private final EmailGateway emailGateway;

    public RentCancelledNotificationListener(
            final RentGateway rentGateway,
            final UserGateway userGateway,
            final ItemGateway itemGateway,
            final EmailGateway emailGateway
    ) {
        this.rentGateway = requireNonNull(rentGateway);
        this.userGateway = requireNonNull(userGateway);
        this.itemGateway = requireNonNull(itemGateway);
        this.emailGateway = requireNonNull(emailGateway);
    }

    @Override
    public void onEvent(final RentCancelledNotificationEvent event) {

    }

    private Rent retireveRent(final RentId rentId) {
        return rentGateway.findById(rentId).orElseThrow(rentNotFound(rentId));
    }

    private User retrieveUser(final UserId userId) {
        return userGateway.findById(userId).orElseThrow(userNotFound(userId));
    }

    private Item retrieveItem(final ItemId itemId) {
        return itemGateway.findById(itemId).orElseThrow(itemNotFound(itemId));
    }

    private Supplier<NotFoundException> rentNotFound(final RentId rentId) {
        return () -> NotFoundException.with(Rent.class, rentId);
    }

    private Supplier<NotFoundException> userNotFound(final UserId userId) {
        return () -> NotFoundException.with(User.class, userId);
    }

    private Supplier<NotFoundException> itemNotFound(final ItemId itemId) {
        return () -> NotFoundException.with(Item.class, itemId);
    }

    private void sendEmails(final User lessor, final User lessee, final Item item, final Rent rent) {
        sendLessorEmail(lessor, item, rent);
        sendLesseeEmail(lessee, item, rent);
    }

    private void sendLessorEmail(final User lessor, final Item item, final Rent rent) {
        final var email = buildLessorEmail(lessor, item, rent);
        emailGateway.send(email);
    }

    private void sendLesseeEmail(final User lessee, final Item item, final Rent rent) {
        final var email = buildLesseeEmail(lessee, item, rent);
        emailGateway.send(email);
    }

}
