package com.loquei.core.application.rent.create.listener;

import com.loquei.common.event.EventListener;
import com.loquei.common.exceptions.NotFoundException;
import com.loquei.core.domain.email.EmailGateway;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.item.ItemGateway;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.rent.RentId;
import com.loquei.core.domain.rent.event.RentCreatedNotificationEvent;
import com.loquei.core.domain.user.User;
import com.loquei.core.domain.user.UserGateway;
import com.loquei.core.domain.user.UserId;

import java.util.function.Supplier;


import static com.loquei.core.application.rent.create.listener.LesseeRentCreatedEmailHelper.buildLesseeEmail;
import static com.loquei.core.application.rent.create.listener.LessorRentCreatedEmailHelper.buildLessorEmail;
import static java.util.Objects.requireNonNull;

public class RentCreatedNotificationListener implements EventListener<RentCreatedNotificationEvent> {

    private final RentGateway rentGateway;
    private final UserGateway userGateway;
    private final ItemGateway itemGateway;
    private final EmailGateway emailGateway;

    public RentCreatedNotificationListener(
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
    public void onEvent(final RentCreatedNotificationEvent event) {
        final var rentId = event.getRentId();
        final var rent = retireveRent(rentId);

        final var lessorId = rent.getLessor();
        final var lessor = retrieveUser(lessorId);

        final var lesseeId = rent.getLessee();
        final var lessee = retrieveUser(lesseeId);

        final var itemId = rent.getItem();
        final var item = retrieveItem(itemId);

        sendEmails(lessor, lessee, item, rent);
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
