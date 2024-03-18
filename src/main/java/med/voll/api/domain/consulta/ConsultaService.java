package med.voll.api.domain.consulta;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        Paciente paciente = buscarPaciente(dados.idPaciente());
        Medico medico = buscarMedico(dados.idMedico());
        Consulta consulta = new Consulta(null, medico, paciente, dados.dataHoraConsulta());
        return new DadosDetalhamentoConsulta(consulta);
    }

    public Paciente buscarPaciente(Long id){
        Paciente paciente =  pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não existe na base de dados"));

        return paciente;
    }

    public Medico buscarMedico(Long id){
        Medico medico =  medicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Médico não existe na base de dados"));
        return medico;
    }

}
