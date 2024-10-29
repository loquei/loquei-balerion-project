package com.loquei.core.domain.user.image;

import com.loquei.common.Identifier;
import com.loquei.common.utils.IdUtils;
import com.loquei.core.domain.item.image.ItemImageId;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class UserImageId extends Identifier {

    private final String value;

    private UserImageId(String value) {
        this.value = requireNonNull(value);
    }

    public static UserImageId unique() {
        return UserImageId.from(IdUtils.uuid());
    }

    public static UserImageId from(final String anId) {
        return new UserImageId(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final UserImageId that = (UserImageId) object;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
