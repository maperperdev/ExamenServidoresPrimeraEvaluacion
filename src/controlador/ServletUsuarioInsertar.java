package controlador;

import java.util.List;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import modelo.JbUsuario;

/**
 * Servlet implementation class ServletUsuarioInsertar
 */
@WebServlet("/ServletUsuarioInsertar")
public class ServletUsuarioInsertar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	@Resource(lookup = "jdbc/datos")
	private DataSource miPool;
	private JbUsuario usuario;
	private DaoUsuario usuarioDAO;
	private JbUsuario usuarioNuevo;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			insertarUsuarioBD(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void insertarUsuarioBD(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<String> listaErrores = new ArrayList<>();
		usuarioDAO = new DaoUsuario(miPool);
		String nombre = "";
		String apellido = "";
		String fechaNacimientoSTR = "";
		LocalDate fechaNacimiento = null;
		String correoElectronico = "";
		String contrasenia = "";
		String contraseniaRepetida = "";
		String fechaRegistro = LocalDate.now().toString();
		String fechaModificacion = LocalDate.now().toString();

		nombre = request.getParameter("nombre");
		apellido = request.getParameter("apellido");
		fechaNacimientoSTR = request.getParameter("fechaNacimiento");
		correoElectronico = request.getParameter("correoElectronico");
		contrasenia = request.getParameter("contrasenia");
		contraseniaRepetida = request.getParameter("contraseniaRepetida");

		if (nombre.length() == 0) {
			listaErrores.add("El nombre no puede estar vacío");
		} else {
			request.setAttribute("nombre", nombre);
		}

		if (apellido.length() == 0) {
			listaErrores.add("El apellido no puede estar vacío");
		} else {
			request.setAttribute("apellido", apellido);
		}

		if (fechaNacimientoSTR.length() == 0) {
			listaErrores.add("La fecha de nacimiento no puede estar vacía");
		} else {
			fechaNacimiento = convertirFecha(fechaNacimientoSTR);
			if (fechaNacimiento == null) {
				listaErrores.add("La fecha de nacimiento no es correcta");
			}
		}

		if (correoElectronico.length() == 0) {
			listaErrores.add("El correo no puede estar vacío");
		}

		if (correoElectronico.length() != 0 && !esEmail(correoElectronico)) {
			listaErrores.add("El correo no tiene un formato válido");
		} else {
			request.setAttribute("correoElectronico", correoElectronico);
		}

		usuarioNuevo = usuarioDAO.getByCorreoElectronico(correoElectronico);

		if (usuarioNuevo != null) {
			listaErrores.add("No puede registrarse con un correo ya guardado en la Base de Datos");
		}

		if (contrasenia.length() == 0) {
			listaErrores.add("La contraseña no puede estar vacía");
		} else {
			if (!contrasenia.equals(contraseniaRepetida)) {
				listaErrores.add("Las contraseñas no coinciden");
			}
		}

		if (listaErrores.size() == 0) {
			usuario = new JbUsuario(nombre, apellido, fechaNacimiento, correoElectronico, contrasenia, fechaRegistro,
					fechaModificacion);
			System.out.println(usuario);
			usuarioDAO.insert(usuario);
			request.getSession().setAttribute("correElectronicoLogin", correoElectronico);

			request.getRequestDispatcher("bienvenida.jsp").forward(request, response);
		} else {
			request.setAttribute("listaErrores", listaErrores);
			request.getRequestDispatcher("vistas/insertar.jsp").forward(request, response);
		}
	}

	public static boolean esEmail(String cadena) {
		Matcher m = VALID_EMAIL_ADDRESS_REGEX.matcher(cadena);
		return m.matches();
	}

	public static LocalDate convertirFecha(String fecha) {
		String[] arrayFecha = fecha.split("/");
		if (arrayFecha.length != 3) {
			return null;
		}
		int dia, mes, anho;
		anho = Integer.parseInt(arrayFecha[2]);
		mes = Integer.parseInt(arrayFecha[1]);
		dia = Integer.parseInt(arrayFecha[0]);
		try {
			return LocalDate.of(anho, mes, dia);
		} catch (Exception e) {
			return null;
		}
	}

}
