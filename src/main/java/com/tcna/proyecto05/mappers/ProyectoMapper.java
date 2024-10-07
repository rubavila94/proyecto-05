package com.tcna.proyecto05.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tcna.proyecto05.entities.Proyecto;

@Mapper
public interface ProyectoMapper {
    @Insert("INSERT INTO proyecto (nombre) VALUES(#{nombre})")
    void insertProyecto(Proyecto proyecto);

    @Select("SELECT * FROM Proyecto WHERE id = #{id}")
    Proyecto getProyectoById(int id);

    @Select("SELECT DISTINCT p.id, p.nombre FROM Proyecto p " +
            "LEFT JOIN proyecto_empleado pe ON p.id = pe.proyecto_id")
    List<Proyecto> getAllProyectos();

    @Update("UPDATE Proyecto SET nombre = #{nombre} WHERE id = #{id}")
    void updateProyecto(Proyecto proyecto);

    @Delete("DELETE FROM proyecto_empleado WHERE proyecto_id = #{proyectoId}")
    void deleteProyectoEmpleado(int proyectoId);

    @Delete("DELETE FROM proyecto WHERE id = #{id}")
    void deleteProyecto(int id);

    @Insert("INSERT INTO proyecto_empleado (proyecto_id, empleado_id) VALUES(#{proyectoId}, #{empleadoId})")
    void asignarEmpleadoAProyecto(@Param("proyectoId") int proyectoId, @Param("empleadoId") int empleadoId);

    @Delete("DELETE FROM proyecto_empleado WHERE proyecto_id = #{proyectoId} AND empleado_id = #{empleadoId}")
    void designarEmpleadoDeProyecto(@Param("proyectoId") int proyectoId, @Param("empleadoId") int empleadoId);

    @Delete("DELETE FROM proyecto_empleado WHERE proyecto_id = #{proyectoId}")
    void deleteProyectoEmpleadoByProyectoId(int proyectoId);
}
