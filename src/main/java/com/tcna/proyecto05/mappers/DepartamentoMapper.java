package com.tcna.proyecto05.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.tcna.proyecto05.entities.Departamento;

@Mapper
public interface DepartamentoMapper {

    @Insert("INSERT INTO Departamento (nombre,ubicacion) VALUES (#{nombre}, #{ubicacion})")
    void insertDepartamento(Departamento departamento);

    @Select("SELECT * FROM Departamento WHERE id = #{id}")
    Departamento getDepartamentoById(int id);

    @Select("SELECT * FROM Departamento")
    List<Departamento> getAllDepartamentos();

    @Update("UPDATE Departamento SET nombre = #{nombre}, ubicacion=#{ubicacion} WHERE id = #{id}")
    void updateDepartamento(Departamento departamento);

    @Delete("DELETE FROM Departamento WHERE id = #{id}")
    void deleteDepartamento(int id);
}
