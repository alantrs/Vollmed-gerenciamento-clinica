package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsulta implements ValidadorAgendamentoConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){
        Boolean consultaNaoDisponivel = consultaRepository.existsByMedicoIdAndDataHoraConsulta(dados.idMedico(), dados.dataHoraConsulta());

        if (consultaNaoDisponivel){
            throw new ValidacaoException("Médico ja possui uma consulta agendada nesse horario");
        }
    }
}
