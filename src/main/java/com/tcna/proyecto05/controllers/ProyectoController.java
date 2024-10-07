package com.tcna.proyecto05.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcna.proyecto05.entities.Empleado;
import com.tcna.proyecto05.entities.Proyecto;
import com.tcna.proyecto05.mappers.EmpleadoMapper;
import com.tcna.proyecto05.mappers.ProyectoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "proyectos/crearProyecto";
    }

    @PostMapping("/crear")
    public String crearProyecto(@ModelAttribute Proyecto proyecto) {
        proyectoMapper.insertProyecto(proyecto);
        return "redirect:/proyectos";
    }

    @GetMapping("/{id}")
    public String mostrarDetallesProyecto(@PathVariable int id, Model model) {
        Proyecto proyecto = proyectoMapper.getProyectoById(id);
        model.addAttribute("proyecto", proyecto);
        return "proyectos/detallesProyecto";
    }

    @GetMapping
    public String listarProyectos(Model model) {
        List<Proyecto> proyectos = proyectoMapper.getAllProyectos();
        model.addAttribute("proyectos", proyectos);
        return "proyectos/listaProyectos";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEdicionProyecto(@PathVariable int id, Model model) {
        Proyecto proyecto = proyectoMapper.getProyectoById(id);
        model.addAttribute("proyecto", proyecto);
        return "/proyectos/editarProyecto";
    }

    @PostMapping("/{id}/editar")
    public String editarProyecto(@PathVariable int id, @ModelAttribute Proyecto proyecto) {
        proyecto.setId(id);
        proyectoMapper.updateProyecto(proyecto);
        return "redirect:/proyectos";
    }

    /*
     * Primero eliminamos la relacion, y luego el proyecto en si ya que están
     * relacionados
     * Por lo que no podemos eliminar un proyecto directamente
     */

    @GetMapping("/{id}/eliminar")
    public String eliminarProyecto(@PathVariable int id) {
        proyectoMapper.deleteProyectoEmpleado(id);
        proyectoMapper.deleteProyecto(id);
        return "redirect:/proyectos";
    }

    // Asignamos empleados a un proyecto
    @PostMapping("/{idProyecto}/empleados/asignar/{idEmpleado}")
    public String asignarEmpleadoAProyecto(@PathVariable int idProyecto, @PathVariable int idEmpleado) {
        proyectoMapper.asignarEmpleadoAProyecto(idProyecto, idEmpleado);
        return "redirect:/proyectos/" + idProyecto + "/empleados";
    }

    @PostMapping("/{idProyecto}/empleados/designar/{idEmpleado}")
    public String designarEmpleadoDeProyecto(@PathVariable int idProyecto, @PathVariable int idEmpleado) {
        proyectoMapper.designarEmpleadoDeProyecto(idProyecto, idEmpleado);
        return "redirect:/proyectos/" + idProyecto + "/empleados";
    }

    @GetMapping("/{id}/empleados")
    public String mostrarEmpleadosProyecto(@PathVariable int id, Model model) {
        Proyecto proyecto = proyectoMapper.getProyectoById(id);
        List<Empleado> empleadosAsignados = empleadoMapper.getEmpleadosByProyectoId(id);
        List<Empleado> empleadosNOAsignados = empleadoMapper.getEmpleadosNoAsignadosAProyecto(id);
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("empleadosAsignados", empleadosAsignados);
        model.addAttribute("empleadosNOAsignados", empleadosNOAsignados);
        return "/proyectos/listaEmpleadosProyecto";
    }

}
