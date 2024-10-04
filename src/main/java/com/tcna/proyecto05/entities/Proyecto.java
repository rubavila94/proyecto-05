package com.tcna.proyecto05.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {
    private int id;
    private String nombre;
    private List<Empleado> empleadosAsignados;
}
