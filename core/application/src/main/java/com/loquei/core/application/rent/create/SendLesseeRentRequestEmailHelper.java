package com.loquei.core.application.rent.create;

import com.loquei.common.utils.EmailUtils;
import com.loquei.core.domain.email.Email;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.user.User;

public final class SendLesseeRentRequestEmailHelper {

    private SendLesseeRentRequestEmailHelper() {}

    public static Email buildLesseeEmail(final User lessor, final Item item, final Rent rent) {
        final var email = lessor.getEmail();
        final var emailSubject = buildLesseeEmailSubject();
        final var emailBody = buildLesseeEmailBody(lessor, item, rent);
        return Email.newEmail(email, emailSubject, emailBody);
    }

    private static String buildLesseeEmailSubject() {
        return "Sua solicitação de locação foi registrada!";
    }

    private static String buildLesseeEmailBody(final User lessor, final Item item, final Rent rent) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Olá ").append(lessor.getPersonalName()).append(", somos a Loquei!\n\n");
        sb.append("Você realizou uma solicitação de locação com os dados abaixo:\n");
        sb.append("Item: ").append(item.getName()).append("\n");
        sb.append("Data de início: ").append(EmailUtils.formatLocalDateTime(rent.getStartDate())).append("\n");
        sb.append("Data de término: ").append(EmailUtils.formatLocalDateTime(rent.getEndDate())).append("\n");
        sb.append("Valor total: R$").append(EmailUtils.formatBigDecimal(rent.getTotalValue())).append("\n");
        sb.append("\n\n");
        sb.append("Atenciosamente,\n");
        sb.append("Equipe de locações, Loquei.");

        return sb.toString();
    }
}