package com.example.demo.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.empleado;
import com.example.demo.modelo.nominaE;
import com.example.demo.repositorio.empleadoRepositorio;
import com.example.demo.repositorio.nominaRepositorio;

@RestController
@RequestMapping("/ver/nomina/")
public class nominacontrolador {
	
	@Autowired
	private empleadoRepositorio repositorioo;
	
	@Autowired
	private nominaRepositorio repositorio;
	

	
	@GetMapping("/guardarnomi")
	public List<nominaE> guardarnomina(
		@RequestParam Long identificacion,	
		@RequestParam Float salario,
		@RequestParam String fecha
		)throws ParseException {
		
		empleado emple=this.repositorioo.findById(identificacion).orElse(null);
		
		if(emple==null) {
			return null;
		}else {
			
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
		 Date fechaa=sdf.parse(fecha);
		 
		 Float totalSalario = calcularTotalSalario(salario);
		 Float ingresos=calcularboni(salario);
		 Float deducidos=calculardedu(salario);
		 
		 nominaE e= new nominaE (emple,salario,deducidos,ingresos,totalSalario,fechaa);
		 this.repositorio.save(e);
		 
		 return this.repositorio.findByEmpleados(emple);
		 
		}
		
	}
	
	
	@GetMapping("/mostrar")
	public List<nominaE> vertodoempleados(){
		return this.repositorio.findAll();
	}
	
	@GetMapping("/eliminar")
	public List<nominaE>eliminar(){
		this.repositorio.deleteById((long)102);
		return this.repositorio.findAll();
	}
	
	@GetMapping("/buscar")
	public Optional<nominaE> buscar(){
		return this.repositorio.findById((long)152);
	}
	
	@GetMapping("/actualizar")
	public List<nominaE> actualizar(){
		nominaE n= this.repositorio.findById((long)152).get();
		n.setDeducidos((float)10000);
		this.repositorio.save(n);
		return this.repositorio.findAll();
	
	}
	@GetMapping("/ingreso")
	public List<nominaE> ingreso() {
		return this.repositorio.findByingresos((float)78);
		
	}
	@GetMapping("/salario")
	public List<nominaE> salario() {
		return this.repositorio.findBysalario((float)12.44);
		
	}
	
	@GetMapping("/deducidos")
	public List<nominaE> deducidos() {
		return this.repositorio.findBydeducidos((float)34.77);
		
	}
	@GetMapping("/salariototal")
	public List<nominaE> salariototal() {
		return this.repositorio.findBytotalsalario((float)45.99);
		
	}
	
	@GetMapping("/nominatotal")
	public List<Object> totalnomina(){
	List<Object>r=this.repositorio.totalano("2020");
	return r;
	}
	
	@GetMapping("/nominames")
	public List<Object> totalmes(){
		return this.repositorio.totalNominaPorMes();
	}
	@GetMapping("/total")
	public List<Object> total(){
		return this.repositorio.totalempleados();
	}
	
	@GetMapping("/totalpago")
	public List<Object> totapl(){
		return this.repositorio.totalpago();
	}
	@GetMapping("/mayor")
	public List<Object> mayorpa(){
		return this.repositorio.mayor();
	}
	

	@GetMapping("/promedio")
	public List<Object> promedio(){
		return this.repositorio.totalpromedio();
}
	
	@GetMapping("/ultima")
	public List<Object> buscarnomina(){
		return this.repositorio.tata("12",123456L);
	}
	
	private Float calcularTotalSalario(Float salario) {
	    float descuentoSalud = salario * 0.04f;
	    float descuentoPension = salario * 0.06f;
	    float bonificacion = 0;

	    float salarioMinimo = 1300000; 

	    if (salario < 2 * salarioMinimo) {
	        bonificacion = salario * 0.10f;
	    }

	    return salario - descuentoSalud - descuentoPension + bonificacion;
	}
	
	private Float calcularboni(Float salario) {
		 float bonificacion = 0;

		    float salarioMinimo = 1300000; 

		    if (salario < 2 * salarioMinimo) {
		        bonificacion = salario * 0.10f;
		    }
		    return bonificacion;
		
	}
	
	private Float calculardedu(Float salario) {
		float zzz=0;
		
		  float descuentoSalud = salario * 0.04f;
		  float descuentoPension = salario * 0.06f;
		  zzz = descuentoSalud + descuentoPension;
		  
		  return zzz;
		
	}
	
	@GetMapping("/calcularpara")
	public List<Object> calcularr(){
		float icbf=0; float sena=0; float cajacom=0;
		String empleado;
		List<Object> nombre = new LinkedList <>();
		List<nominaE> se=this.repositorio.findAll();
		for(int i=0;i<se.size();i++) {
			empleado = se.get(i).getEmpleados().getNombre();
			icbf=se.get(i).getSalario() *0.03f;
			sena=se.get(i).getSalario() *0.02f;
			cajacom=se.get(i).getSalario() *0.04f;
			nombre.add(empleado);
			nombre.add(icbf);
			nombre.add(sena);
			nombre.add(cajacom);
			
		}
		return nombre;
		
	}
	@GetMapping("/parafis")
	public float calcularParafis() {
	    
	    List<nominaE> nominaE = this.repositorio.findAll();
	    float totalParafiscales = 0;
	    for (int i = 0; i < nominaE.size(); i++) {
	        
	        nominaE nominaac = nominaE.get(i);
	       
	        float parafiscales = nominaac.getSalario()* 0.09f;
	        totalParafiscales += parafiscales;

	    }
	    return  totalParafiscales;
	 
	}
	
	@GetMapping("/cesantias")
	public List<Object> calcularCesantias(@RequestParam int diasTrabajados) {
	    List<Object> resultados = new LinkedList<>();
	    List<nominaE> nominas = this.repositorio.findAll();
	    Date fecha = new Date();
	    System.out.print(fecha.getMonth());
	    for (nominaE nomina : nominas) {
	        float salario = nomina.getSalario(); 
	        float cesantiass=salario*diasTrabajados/360;
	        float interesCesantias = cesantiass * 0.12f * ((float) diasTrabajados / 360);
	        float totalCesantias = cesantiass + interesCesantias;
	        
	       
	        resultados.add("Empleado: " + nomina.getEmpleados().getNombre());
	        resultados.add("Salario: " + nomina.getSalario());
	        resultados.add("Interés Cesantías: " + interesCesantias);
	        resultados.add("Total Cesantías: " + totalCesantias);
	        
	    }
	    
	    return resultados;
	}
	
	
	
}
