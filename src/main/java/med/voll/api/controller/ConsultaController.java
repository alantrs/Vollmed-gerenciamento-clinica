package med.voll.api.controller;

import med.voll.api.domain.consulta.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {


    @Autowired
    private ConsultaService consultaService;


}
