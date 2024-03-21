package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta{

    public void validar(DadosAgendamentoConsulta dados){
        Boolean isDomingo = dados.dataHoraConsulta().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean antesDaAbertura = dados.dataHoraConsulta().getHour() < 7;
        Boolean depoisDoFechamento = dados.dataHoraConsulta().getHour() >= 18 && dados.dataHoraConsulta().getMinute() > 0;

         if (isDomingo || antesDaAbertura || depoisDoFechamento){
             throw new ValidacaoException("Consulta fora de horário da clínica");
         }
    }
}
