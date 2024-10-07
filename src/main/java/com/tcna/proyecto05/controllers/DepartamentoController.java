package com.tcna.proyecto05.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tcna.proyecto05.entities.Departamento;
import com.tcna.proyecto05.mappers.DepartamentoMapper;
import com.tcna.proyecto05.mappers.EmpleadoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoMapper departamentoMapper;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    @GetMapping
    public String listarDepartamentos(Model model) {
        List<Departamento> departamentos = departamentoMapper.getAllDepartamentos();
        model.addAttribute("departamentos", departamentos);
        return "departamento/listaDepartamentos";
    }

    @GetMapping("/{id}")
    public String mostrarDetallesDepartamento(@PathVariable int id, Model model) {
        Departamento departamento = departamentoMapper.getDepartamentoById(id);
        model.addAttribute("departamento", departamento);
        return "departamento/detallesDepartamentos";
    }

    // En este crear, lo que hace es generar la vista de este formulario
    @GetMapping("/crear")
    public String mostrarFormularioCreacionDepartamento(Model model) {
        model.addAttribute("departamento", new Departamento());
        // que vaya a ese archivo HTML
        return "departamento/crearDepartamento";
    }

    // Este crear es cuando pulsamos el boton guardar
    @PostMapping("/crear")
    public String crearDepartamento(@ModelAttribute Departamento departamento) {
        departamentoMapper.insertDepartamento(departamento);
        // Redirect te lleva a un endpoint
        return "redirect:/departamentos";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEdicionDepartamento(@PathVariable int id, Model model) {
        Departamento departamento = departamentoMapper.getDepartamentoById(id);
        model.addAttribute("departamento", departamento);
        return "departamento/editarDepartamento";
    }

    @PostMapping("/{id}/editar")
    public String editarDepartamento(@PathVariable int id, @ModelAttribute Departamento departamento) {
        departamento.setId(id);
        departamentoMapper.updateDepartamento(departamento);
        // Redirect te lleva a un endpoint
        return "redirect:/departamentos";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarDepartamento(@PathVariable int id) {
        empleadoMapper.deleteProyectoEmpleadoByDepartamentoId(id);
        empleadoMapper.deleteEmpleadosByDepartamentoId(id);
        departamentoMapper.deleteDepartamento(id);
        return "redirect:/departamentos";
    }
}
