package com.tcna.proyecto05.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcna.proyecto05.entities.Proyecto;
import com.tcna.proyecto05.mappers.EmpleadoMapper;
import com.tcna.proyecto05.mappers.ProyectoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoMapper proyectoMapper;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    /*
     * GetMapping te lleva a un archivo HTML
     * PostMapping envia datos y los registra en BBDD
     */
    @GetMapping("/crear")
    public String mostrarFormularioCreacionProyecto(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "proyecto/crearProyecto";
    }

    @PostMapping("/crear")
    public String crearProyecto(@ModelAttribute Proyecto proyecto) {
        proyectoMapper.insertProyecto(proyecto);
        return "redirect:/proyectos";
    }

    @GetMapping("/{id}")
    public String mostrarDetallesProyecto(@PathVariable int id, Model model) {
        Proye
        return new String();
    }

}
