package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class JbUsuario implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idUsuario = 0;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String correoElectronico;
	private String contrasenia;
	private String fechaRegistro;
	private String fechaModificacion;
	
	public JbUsuario(){}
	
	

	public JbUsuario(Integer idUsuario, String nombre, String apellido, LocalDate fechaNacimiento,
			String correoElectronico, String contrasenia, String fechaRegistro, String fechaModificacion) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.correoElectronico = correoElectronico;
		this.contrasenia = contrasenia;
		this.fechaRegistro = fechaRegistro;
		this.fechaModificacion = fechaModificacion;
	}



	public JbUsuario(String nombre, String apellido, LocalDate fechaNacimiento, String correoElectronico,
			String contrasenia, String fechaRegistro, String fechaModificacion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.correoElectronico = correoElectronico;
		this.contrasenia = contrasenia;
		this.fechaRegistro = fechaRegistro;
		this.fechaModificacion = fechaModificacion;
	}
	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	/**
	 * @return the fechaRegistro
	 */
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	/**
	 * @return the fechaModificacion
	 */
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}



	@Override
	public String toString() {
		return "JbUsuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fechaNacimiento=" + fechaNacimiento + ", correoElectronico=" + correoElectronico + ", contrasenia="
				+ contrasenia + ", fechaRegistro=" + fechaRegistro + ", fechaModificacion=" + fechaModificacion + "]";
	}

}
