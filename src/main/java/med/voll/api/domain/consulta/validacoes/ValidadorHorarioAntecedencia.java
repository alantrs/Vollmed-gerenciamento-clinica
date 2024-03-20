package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta{

    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime horaConsulta = dados.dataHoraConsulta();
        LocalDateTime horaAtual = LocalDateTime.now();
        Long diferencaEmMinutos = Duration.between(horaAtual, horaConsulta).toMinutes();

        if (diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta só pode ser marcada com no minímo 30 minutos de antecedência");
        }
    }
}
