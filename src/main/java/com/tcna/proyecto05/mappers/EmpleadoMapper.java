package com.tcna.proyecto05.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.tcna.proyecto05.entities.Empleado;

@Mapper
public interface EmpleadoMapper {

    @Insert("INSERT INTO Empleado (nombre, apellido, salario, departamento_id) VALUES(#{nombre}, #{apellido}, #{salario}, #{departamento.id})")
    void insertEmpleado(Empleado empleado);

    @Select("SELECT e.id AS id, e.nombre AS nombre, e.apellido AS apellido, e.salario AS salario, " +
            "d.id AS departamento_id, d.nombre AS departamento_nombre, d.ubicacion AS dep_ubicacion, " +
            "p.id AS proyecto_id, p.nombre AS proyecto_nombre FROM Empleado e " +
            "LEFT JOIN Departamento d ON e.departamento_id = d.id " +
            "LEFT JOIN proyecto_empleado pe ON e.id = pe.empleado_id " +
            "LEFT JOIN Proyecto p ON pe.proyecto_id = p.id " +
            "WHERE e.id = #{id}")
    Empleado getEmpleadoById(int id);

    @Select("SELECT e.id AS id, e.nombre AS nombre, e.apellido AS apellido, e.salario AS salario, " +
            "d.id AS departamento_id, d.nombre AS departamento_nombre, d.ubicacion AS dep_ubicacion " +
            "FROM Empleado e " +
            "LEFT JOIN Departamento d ON e.departamento_id = d.id")
    List<Empleado> getAllEmpleados();

    @Update("UPDATE Empleado SET nombre = #{nombre}, apellido = #{apellido}, salario = #{salario}, departamento_id = #{departamento_id} WHERE id = #{id}")
    void updateEmpleado(Empleado empleado);

    @Delete("DELETE FROM Empleado WHERE id = #{id}")
    void deleteEmpleado(int id);

    // Obtener empleados que no estan asignados en un proyecto
    @Select("SELECT * FROM Empleado WHERE id NOT IN(SELECT empleado_id FROM proyecto_empleado WHERE proyecto_id = #{proyectoId})")
    List<Empleado> getEmpleadosNoAsignadosAProyecto(int proyectoId);

    // El contrario que el metodo de arriba
    @Select("SELECT e.* FROM Empleado e INNER JOIN proyecto_empleado pe ON e.id = pe.empleado_id WHERE pe.proyecto_id = #{proyectoId})")
    List<Empleado> getEmpleadosByProyectoId(int proyectoId);

    @Delete("DELETE FROM Empleado WHERE departamento_id = #{departamentoId}")
    void deleteEmpleadosByDepartamentoId(int departamentoId);

    // Aqui eliminamos la asignacion que tiene un empleado a un proyecto
    @Delete("DELETE FROM proyecto_empleado WHERE empleado_id = #{empleadoId}")
    void deleteProyectoEmpleadoByEmpleadoId(int empleadoId);

    @Delete("DELETE FROM proyecto_empleado WHERE empleado_id IN(SELECT id FROM Empleado WHERE departamento_id = #{departamentoId})")
    void deleteProyectoEmpleadoByDepartamentoId(int departamentoId);
}