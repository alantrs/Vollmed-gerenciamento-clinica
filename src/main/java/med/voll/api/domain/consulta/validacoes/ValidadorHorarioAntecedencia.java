package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecedencia {

    public void validarAntecedencia(DadosAgendamentoConsulta dados){
        LocalDateTime horaConsulta = dados.dataHoraConsulta();
        LocalDateTime horaAtual = LocalDateTime.now();
        Long diferencaEmMinutos = Duration.between(horaAtual, horaConsulta).toMinutes();

        if (diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta só pode ser marcada com no minímo 30 minutos de antecedência");
        }
    }
}
