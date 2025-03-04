package com.example.demo.modelo;




import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "nominaE")


public class nominaE {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	private Long id_nomina;
	
	@ManyToOne()
	@JoinColumn(name = "identificacion",referencedColumnName="identificacion")
	private empleado empleados;
	
	
	
	@Column(name= "salario", length = 80,nullable=false)
	private Float salario; 
	
	@Column(name= "deducidos", length = 80,nullable=false)
	private Float deducidos; 
	
	@Column(name= "ingresos", length = 80,nullable=false)
	private Float ingresos; 
	
	@Column(name= "total_salario", length = 80,nullable=false)
	private Float totalsalario; 
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyy")
	@Column(name="fecha",nullable=false)
	private Date fecha;
	
	public nominaE() {
	
	}
	
	

	public nominaE(empleado empleados, Float salario, Float deducidos, Float ingresos,
			Float totalsalario, Date fecha) {
		super();
		this.empleados=empleados;
		this.salario = salario;
		this.deducidos = deducidos;
		this.ingresos = ingresos;
		this.totalsalario = totalsalario;
		this.fecha = fecha;
	}
	
	
	public Long getId_nomina() {
		return id_nomina;
	}



	public void setId_nomina(Long id_nomina) {
		this.id_nomina = id_nomina;
	}
	

	public empleado getEmpleados() {
		return empleados;
	}



	public void setEmpleados(empleado empleados) {
		this.empleados = empleados;
	}



	public Float getSalario() {
		return salario;
	}

	public void setSalario(Float salario) {
		this.salario = salario;
	}

	public Float getDeducidos() {
		return deducidos;
	}

	public void setDeducidos(Float deducidos) {
		this.deducidos = deducidos;
	}

	public Float getIngresos() {
		return ingresos;
	}

	public void setIngresos(Float ingresos) {
		this.ingresos = ingresos;
	}

	public Float getTotal_salario() {
		return totalsalario;
	}

	public void setTotal_salario(Float total_salario) {
		this.totalsalario = total_salario;
	}


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

	
}
