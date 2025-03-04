package com.example.demo.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelo.empleado;
import com.example.demo.modelo.nominaE;

public interface nominaRepositorio extends JpaRepository<nominaE,Long>{
	
	public List<nominaE> findBysalario(Float salario);
	public List<nominaE> findBydeducidos(Float deducidos);
	public List<nominaE> findByingresos(Float ingresos);
	public List<nominaE> findBytotalsalario(Float totalsalario);
	public List<nominaE> findByfecha(Date fecha);
	public List <nominaE> findByEmpleados(empleado id);
	
	
	@Query(value = "select e.nombre, SUM(n.total_salario) AS total_salario " +
            "from nominaE n " +
            "inner join empleados e ON e.identificacion = n.identificacion " +
            "and EXTRACT(YEAR FROM n.fecha) = :year " +
            "GROUP BY e.nombre",nativeQuery = true)
    public List<Object> totalano(@Param("year") String year);
	
	@Query(value = "SELECT EXTRACT(month FROM n.fecha) AS mes, SUM(n.salario) AS total_salario " +
            "FROM nominaE n " +
            "GROUP BY mes ",
    nativeQuery = true)
	public List<Object> totalNominaPorMes();
	
	@Query(value = "Select count(*) as empleados from empleados ",nativeQuery = true)
	public List<Object>totalempleados();
	
	
	@Query(value = "select e.nombre, count(n.total_salario) AS total_salario " +
            "from nominaE n " +
            "inner join empleados e ON e.identificacion = n.identificacion " +
            "GROUP BY e.nombre",nativeQuery = true)
    public List<Object> totalpago();
	
	@Query(value = "select e.nombre, max(n.salario)as mayor_salario from empleados e "
			+ "inner join nominaE n ON e.identificacion = n.identificacion "
			+ "group by e.nombre",nativeQuery = true)
	public List<Object> mayor();
	
	
	@Query(value = "SELECT EXTRACT(month FROM n.fecha) AS mes, avg(n.salario) AS total_salario " +
            "FROM nominaE n " +
            "GROUP BY mes ",nativeQuery = true)
	public List<Object> totalpromedio();
	
	@Query(value = "SELECT e.nombre, SUM(n.salario) FROM nominaE n "
            + "INNER JOIN empleados e ON e.identificacion = n.identificacion "
            + "AND extract(MONTH FROM n.fecha) = :mes AND e.identificacion = :idEmpleado "
            + "GROUP BY e.nombre", nativeQuery = true)
	public List<Object> tata(@Param("mes") String mes, @Param("idEmpleado") Long idEmpleados);

}
