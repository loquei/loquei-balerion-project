package com.loquei.core.infrastructure.rent;

import static java.util.Objects.requireNonNull;

import com.loquei.common.pagination.Pagination;
import com.loquei.core.domain.item.ItemId;
import com.loquei.core.domain.rent.Rent;
import com.loquei.core.domain.rent.RentGateway;
import com.loquei.core.domain.rent.RentId;
import com.loquei.core.domain.user.UserId;
import com.loquei.core.infrastructure.rent.persistence.RentJpaEntity;
import com.loquei.core.infrastructure.rent.persistence.RentRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class RentPostgresGateway implements RentGateway {

    private final RentRepository repository;

    public RentPostgresGateway(RentRepository repository) {
        this.repository = requireNonNull(repository);
    }

    @Override
    public Rent rent(Rent rent) {
        return save(rent);
    }

    @Override
    public Rent update(Rent rent) {
        return save(rent);
    }

    @Override
    public List<Rent> findAll() {
        return repository.findAll().stream().map(RentJpaEntity::toAggregate).toList();
    }

    @Override
    public Optional<Rent> findById(RentId rentId) {
        return repository.findById(rentId.getValue()).map(RentJpaEntity::toAggregate);
    }

    public Pagination<Rent> findAllByUserId(UserId userId) {
        // Página inicial: 0, e tamanho da página: 10
        final Pageable pageable = PageRequest.of(0, 10);

        // Método que retorna a página de RentJpaEntity
        Page<RentJpaEntity> rentsPage = repository.findAllByUserId(userId.getValue(), pageable);

        // Converter a página de RentJpaEntity para uma lista de Rent agregados
        List<Rent> rents = rentsPage.stream().map(RentJpaEntity::toAggregate).collect(Collectors.toList());

        // Criar um objeto de paginação personalizado com os detalhes da página
        return new Pagination<>(rentsPage.getNumber(), rentsPage.getSize(), rentsPage.getTotalElements(), rents);
    }

    @Override
    public boolean isItemAvailableForRent(ItemId itemId, LocalDateTime startDate, LocalDateTime endDate) {
        return repository.isItemAvailableForRent(itemId.getValue(), startDate, endDate);
    }

    @Override
    public List<Rent> findConflictingPendingRentals(UserId userId, ItemId itemId, LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findConflictingPendingRentals(
                        userId.getValue(),
                        itemId.getValue(),
                        startDate,
                        endDate)
                .stream().map(RentJpaEntity::toAggregate).toList();
    }


    private Rent save(final Rent rent) {
        return repository.save(RentJpaEntity.from(rent)).toAggregate();
    }
}
