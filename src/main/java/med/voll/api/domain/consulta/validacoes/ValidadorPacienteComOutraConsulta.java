package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;

public class ValidadorPacienteComOutraConsulta {

    private PacienteRepository pacienteRepository;

    public void validarPacienteComConsultaMesmaData(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.dataHoraConsulta().withHour(7);
        var ultimoHorario = dados.dataHoraConsulta().withHour(18);
        Boolean pacientePossuiOutraConsultaNoDia = pacienteRepository.existsByPacienteIdAndDataHoraConsultaBetween(dados.idPaciente(),primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente com consulta marcada nesse dia");
        }
    }
}
