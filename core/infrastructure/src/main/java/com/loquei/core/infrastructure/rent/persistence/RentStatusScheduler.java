package com.loquei.core.infrastructure.rent.persistence;

import com.loquei.core.application.rent.update.UpdateScheduleStatus.UpdateScheduleStatusUseCase;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class RentStatusScheduler {

    private final UpdateScheduleStatusUseCase updateScheduleStatusUseCase;

    public RentStatusScheduler(UpdateScheduleStatusUseCase updateScheduleStatusUseCase) {
        this.updateScheduleStatusUseCase = updateScheduleStatusUseCase;
    }

    // Agendamento para execução do método updateRentStatuses a cada hora
    @Scheduled(cron = "0 0 * * * ?") // Define a frequência: a cada hora
    public void updateRentStatuses() {
        updateScheduleStatusUseCase.execute(); 
    }

}
