package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;

public class ValidadorMedicoComOutraConsulta {

    private MedicoRepository medicoRepository;

    public void validarMedicoConsultaDisponivel(DadosAgendamentoConsulta dados){
        Boolean consultaNaoDisponivel = medicoRepository.existsByMedicoIdAndDataHoraConsulta(dados.idMedico(), dados.dataHoraConsulta());

        if (consultaNaoDisponivel){
            throw new ValidacaoException("Médico ja possui uma consulta agendada nesse horario");
        }
    }
}
