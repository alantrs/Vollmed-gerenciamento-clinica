package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {

    public void validarHorario(DadosAgendamentoConsulta dados){
        Boolean isDomingo = dados.dataHoraConsulta().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean antesDaAbertura = dados.dataHoraConsulta().getHour() < 7;
        Boolean depoisDoFechamento = dados.dataHoraConsulta().getHour() > 18;

         if (isDomingo || antesDaAbertura || depoisDoFechamento){
             throw new ValidacaoException("Consulta fora de horário da clínica");
         }
    }
}
