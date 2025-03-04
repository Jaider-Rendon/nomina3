package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.empleado;
import com.example.demo.repositorio.empleadoRepositorio;

@RestController
@RequestMapping("/ver/empleados/")
public class EmpleadoControlador {
	
	@Autowired
	private empleadoRepositorio repositorio;
	
	@GetMapping("/listaE")
	public List<empleado> vertodoempleados(){
		return repositorio.findAll();
	}
	
	@GetMapping("/listaEE")
	public List<empleado> NuevoE(){
		empleado E = new empleado((long)123456,"juan","perez","goku123@gmail");
		this.repositorio.save(E);
		return repositorio.findAll();
	}
	
	@GetMapping("/buscar")
	public Optional<empleado> buscar(){
		return this.repositorio.findById((long)123456);
	}
	
	@GetMapping ("/buscarEm")
	public empleado BuscarEm(){
		return this.repositorio.findByEmail("goku123@gmail");
		
	}
	
	@GetMapping("/eliminar")
	public List<empleado> eliminar(){
		this.repositorio.deleteById((long)123456);
		return this.repositorio.findAll();
	
	}
	
	@GetMapping("/actualizar")
	public List<empleado> actualizar(){
		empleado e= this.repositorio.findById((long)1054544178).get();
		e.setApellidos("son");
		this.repositorio.save(e);
		return this.repositorio.findAll();
	
	}
	@GetMapping("/nombre")
	public List<empleado> nonbreE(){
	return	this.repositorio.findBynombre("juan");
		
	}
	
	@GetMapping("/apellidos")
	public List<empleado> apellidosE(){
	return	this.repositorio.findByapellidos("perez");
		
	}
	@GetMapping ("/buscar/{id}")
	public String verEmpleados(@PathVariable("id")Long idE){
		 Optional<empleado> e=this.repositorio.findById(idE);
		 return e.get().getNombre()+" " + e.get().getApellidos()+" "+e.get().getIdentificación();
			 
		 }
	
	@GetMapping("/buscarEmpleadonomina")
	public List<Object> buscarnomina(){
		return this.repositorio.buscarnominaE(123456L);
	}
	
	//holamundo
	//hol
	//holaaaaaaaaaaa
	

	
	
	

}
