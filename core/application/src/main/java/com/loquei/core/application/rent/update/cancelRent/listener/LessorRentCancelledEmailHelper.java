package com.loquei.core.application.rent.update.cancelRent.listener;

import com.loquei.common.utils.EmailUtils;
import com.loquei.core.domain.email.Email;
import com.loquei.core.domain.item.Item;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.user.User;

public final class LessorRentCancelledEmailHelper {

    private LessorRentCancelledEmailHelper() {}

    public static Email buildLessorEmail(final User lessor, final Item item, final Rent rent) {
        final var email = lessor.getEmail();
        final var emailSubject = buildLessorEmailSubject();
        final var emailBody = buildLessorEmailBody(lessor, item, rent);
        return Email.newEmail(email, emailSubject, emailBody);
    }

    private static String buildLessorEmailSubject() {
        return "Solicitação de locação cancelada!";
    }

    private static String buildLessorEmailBody(final User lessor, final Item item, final Rent rent) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Olá ").append(lessor.getPersonalName()).append(", somos a Loquei!\n\n");
        sb.append("Você cancelou uma solicitação de locação!\n");
        sb.append("Confira abaixo alguns dados da locação\n");
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
