package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;

public class ValidadorPacienteAtivo {


    public PacienteRepository pacienteRepository;

    public void validarPacienteAtivo(DadosAgendamentoConsulta dados){
        Boolean isAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if (!isAtivo){
            throw new ValidacaoException("Paciente não esta ativo");
        }
    }
}
