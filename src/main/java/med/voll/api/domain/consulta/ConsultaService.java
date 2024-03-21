package med.voll.api.domain.consulta;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.ValidadorCancelamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadoresAgendamento;
    @Autowired
    private List<ValidadorCancelamentoConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        Paciente paciente = buscarPaciente(dados.idPaciente());
        Medico medico = buscarMedico(dados);
        if (medico == null){
            throw new ValidacaoException("Nenhum médico disponível nessa data");
        }

        validadoresAgendamento.forEach(v -> v.validar(dados));

        Consulta consulta = new Consulta(null, medico, paciente, dados.dataHoraConsulta());
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    public Paciente buscarPaciente(Long id){
        Paciente paciente =  pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não existe na base de dados"));
        return paciente;
    }

    public Medico buscarMedico(DadosAgendamentoConsulta dados){
        if (dados.idMedico() != null){
            Medico medico =  medicoRepository.findById(dados.idMedico()).orElseThrow(() -> new EntityNotFoundException("Médico não existe na base de dados"));
            return medico;
        }

        if (dados.especialidade() == null){
            throw new ValidacaoException("Especialidade obrigatória");
        }

        return medicoRepository.buscarMedicoAleatorio(dados.dataHoraConsulta(), dados.especialidade());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        validadoresCancelamento.forEach(v -> v.validar(dados));
        consultaRepository.deleteById(dados.idConsulta());
    }
}
