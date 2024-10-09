package com.loquei.core.domain.user.address;

import static java.util.Objects.requireNonNull;

import com.loquei.common.Identifier;
import com.loquei.common.utils.IdUtils;

public class AddressId extends Identifier {

    private final String value;

    private AddressId(String value) {
        this.value = requireNonNull(value);
    }

    public static AddressId unique() {
        return AddressId.from(IdUtils.uuid());
    }

    public static AddressId from(final String anId) {
        return new AddressId(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AddressId other = (AddressId) obj;
        if (value == null) {
            if (other.value != null) return false;
        } else if (!value.equals(other.value)) return false;
        return true;
    }
}
