package com.tcna.proyecto05.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tcna.proyecto05.entities.Departamento;
import com.tcna.proyecto05.entities.Empleado;
import com.tcna.proyecto05.mappers.DepartamentoMapper;
import com.tcna.proyecto05.mappers.EmpleadoMapper;
import com.tcna.proyecto05.mappers.ProyectoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoMapper empleadoMapper;

    @Autowired
    private DepartamentoMapper departamentoMapper;

    @Autowired
    private ProyectoMapper proyectoMapper;

    @GetMapping("/crear")
    public String mostrarFormularioCreacionEmpleado(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("departamentos", departamentoMapper.getAllDepartamentos());
        return "empleados/crearEmpleado";
    }

    /*
     * ModelAttribute asigna los valores de un formulario al objeto que le estás
     * mandando
     * RequestParam captura un parametro a traves del metodo HTTP
     */
    @PostMapping("/crear")
    public String crearEmpleado(@ModelAttribute Empleado empleado, @RequestParam("departamentoId") int departamentoId) {
        Departamento departamento = departamentoMapper.getDepartamentoById(departamentoId);
        empleado.setDepartamento(departamento);
        empleadoMapper.insertEmpleado(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/{id}")
    public String mostrarDetallesEmpleado(@PathVariable int id, Model model) {
        Empleado empleado = empleadoMapper.getEmpleadoById(id);
        model.addAttribute("empleado", empleado);
        return "empleados/detallesEmpleado";
    }

    @GetMapping
    public String listarEmpleado(Model model) {
        List<Empleado> empleados = empleadoMapper.getAllEmpleados();
        model.addAttribute("empleados", empleados);
        return "empleados/listaEmpleados";
    }

    // Este es solo para mostrar el formulario de Editar
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEdicionEmpleado(@PathVariable int id, Model model) {
        Empleado empleado = empleadoMapper.getEmpleadoById(id);
        model.addAttribute("empleado", empleado);
        model.addAttribute("departamentos", departamentoMapper.getAllDepartamentos());
        return "empleados/editarEmpleado";
    }

    @PostMapping("/{id}/editar")
    public String editarEmpleado(@PathVariable int id, @ModelAttribute Empleado empleado) {
        empleado.setId(id);
        empleadoMapper.updateEmpleado(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarEmpleado(@PathVariable int id) {
        empleadoMapper.deleteProyectoEmpleadoByEmpleadoId(id);
        empleadoMapper.deleteEmpleado(id);
        return "redirect:/empleados";
    }

}
