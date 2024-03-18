create table consultas(
    id serial primary key,
    medico_id bigint not null,
    paciente_id bigint not null,

    constraint fk_consultas_medico_id foreign key (medico_id) references medicos(id),
    constraint fk_consultas_paciente_id foreign key (medico_id) references pacientes(id)
)