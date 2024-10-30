package com.loquei.core.domain.user.image;

import com.loquei.core.domain.user.UserId;
import java.util.Optional;

public interface UserImageGateway {

    UserImage save(UserImage userImage);

    Optional<UserImage> findById(UserImageId userImageId);

    Optional<UserImage> findByUserId(UserId userId);

    void delete(UserImageId userImageId);

    void deleteByUserId(UserId userId);
}
