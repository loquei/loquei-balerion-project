package com.loquei.core.domain.user;


import com.loquei.common.pagination.Pagination;
import com.loquei.common.pagination.SearchQuery;

import java.util.Optional;

public interface UserGateway {

    User create(User user);

    User update(User user);

    Optional<User> findById(UserId id);

    Optional<User> findByEmail(String email);

    Optional<User> findByDocument(String document);

    Optional<User> findByUsername(String username);

    Optional<User> findByPhone(String phone);

    Pagination<User> findAll(SearchQuery query);

    void delete(UserId id);
}
