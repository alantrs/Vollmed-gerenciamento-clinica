package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;

public class ValidadorMedicoAtivo {

    public MedicoRepository medicoRepository;

    public void validarMedicoAtivo(DadosAgendamentoConsulta dados){

        if (dados.idMedico() == null){
            return;
        }
        Boolean isAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if (!isAtivo){
            throw new ValidacaoException("Médico não esta ativo");
        }
    }
}
