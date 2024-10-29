package com.loquei.core.infrastructure.user.image;

import com.loquei.core.domain.user.UserId;
import com.loquei.core.domain.user.image.UserImage;
import com.loquei.core.domain.user.image.UserImageGateway;
import com.loquei.core.domain.user.image.UserImageId;
import com.loquei.core.infrastructure.user.image.persistence.UserImageJpaEntity;
import com.loquei.core.infrastructure.user.image.persistence.UserImageRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserImagePostgresGateway implements UserImageGateway {

    private final UserImageRepository userImageRepository;

    public UserImagePostgresGateway(final UserImageRepository userImageRepository) {
        this.userImageRepository = userImageRepository;
    }

    @Override
    public UserImage save(final UserImage userImage) {
        return this.userImageRepository.save(UserImageJpaEntity.from(userImage)).toEntity();
    }

    @Override
    public Optional<UserImage> findById(final UserImageId userImageId) {
        return this.userImageRepository.findById(userImageId.getValue()).map(UserImageJpaEntity::toEntity);
    }

    @Override
    public Optional<UserImage> findByUserId(final UserId userId) {
        return this.userImageRepository.findByUserId(userId.getValue()).map(UserImageJpaEntity::toEntity);
    }

    @Override
    public void delete(final UserImageId userImageId) {
        this.userImageRepository.deleteById(userImageId.getValue());
    }

    @Override
    @Transactional
    public void deleteByUserId(final UserId userId) {
        this.userImageRepository.deleteByUserId(userId.getValue());
    }
}
