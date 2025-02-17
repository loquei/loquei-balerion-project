package com.loquei.core.domain.rent;

public enum RentStatus {
    ACCEPTED, // QUANDO É ACEITO, PORÉM NÃO INICIOU
    PENDING, // QUANDO É CRIADO A SOLICITAÇÃO
    IN_PROGRESS, // QUANDO INICIOU PELO STARTDATE
    COMPLETED, // QUANDO TERMINOU PELO ENDDATE
    CANCELLED, // QUANDO ELE É RESCINDIDO EM ANDAMENTO
    REFUSED // QUANDO ELE É RECUSADO ESTANDO PENDENTE OU ACEITO
}
