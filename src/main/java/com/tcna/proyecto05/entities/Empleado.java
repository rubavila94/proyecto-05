package com.tcna.proyecto05.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private double salario;
    private Departamento departamento;
    private List<Proyecto> proyectosAsignados;

}
