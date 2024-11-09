package com.loquei.core.domain.wishList;

import com.loquei.common.Identifier;
import com.loquei.common.utils.IdUtils;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class WishListId extends Identifier {

    private final String value;

    public WishListId(String value) {
        this.value = requireNonNull(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    public static WishListId unique(){
        return WishListId.from(IdUtils.uuid());
    }

    public static WishListId from(String anId){
        return new WishListId(anId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishListId that = (WishListId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
