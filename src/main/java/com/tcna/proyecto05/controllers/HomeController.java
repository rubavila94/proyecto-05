package com.tcna.proyecto05.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.tcna.proyecto05.entities.Proyecto;
import com.tcna.proyecto05.mappers.ProyectoMapper;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProyectoMapper proyectoMapper;

    @GetMapping({ "/", "" })
    public String mostrarPaginaDeInicio(Model model) {
        List<Proyecto> proyectos = proyectoMapper.getAllProyectos();
        model.addAttribute("proyectos", proyectos);
        return "proyectos/listaProyectos";
    }
}
