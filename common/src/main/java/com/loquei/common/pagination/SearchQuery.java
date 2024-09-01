package com.loquei.common.pagination;

public record SearchQuery(int page, int perPage, String terms, String sort, String direction) {}
