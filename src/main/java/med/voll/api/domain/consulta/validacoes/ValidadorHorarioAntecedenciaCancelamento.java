package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;


@Component
public class ValidadorHorarioAntecedenciaCancelamento implements ValidadorCancelamentoConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosCancelamentoConsulta dados) {
        Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());
        LocalDateTime horaConsulta = consulta.getDataHoraConsulta();
        LocalDateTime horaAtual = LocalDateTime.now();
        Long diferencaEmHoras = Duration.between(horaAtual, horaConsulta).toHours();

        if (diferencaEmHoras < 24 ){
            throw new ValidacaoException("Consulta só pode ser cancelada com no minímo 24 horas de antecedência");
        }

    }
}
