package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Boolean existsByPacienteIdAndDataHoraConsultaBetween(Long aLong, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
    Boolean existsByMedicoIdAndDataHoraConsulta(Long idMedico, LocalDateTime data);
}
