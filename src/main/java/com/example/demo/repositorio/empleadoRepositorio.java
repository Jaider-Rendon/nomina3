package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.empleado;

public interface empleadoRepositorio extends JpaRepository <empleado,Long>{
	
	public empleado findByEmail(String email);
	public List<empleado> findBynombre(String nombre);
	public List<empleado>findByapellidos(String apellido);
	
	@Query(value="Select e.nombre,e.apellidos,e.email,n.salario,n.fecha from empleados e inner join"
	+ " nominaE n on e.identificacion = n.identificacion and e.identificacion = :idEmpleado", nativeQuery=true)
	public List<Object> buscarnominaE(@Param ("idEmpleado")Long idEmpleados);
}
