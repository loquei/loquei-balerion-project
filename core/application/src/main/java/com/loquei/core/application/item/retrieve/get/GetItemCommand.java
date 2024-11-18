package com.loquei.core.application.item.retrieve.get;

public record GetItemCommand(String itemId, String userEmail) {
    public static GetItemCommand from(String itemId, String userEmail) {
        return new GetItemCommand(itemId, userEmail);
    }
}
