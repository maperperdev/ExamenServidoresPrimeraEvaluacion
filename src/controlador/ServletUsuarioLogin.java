package controlador;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import modelo.JbUsuario;


@WebServlet("/ServletUsuarioLogin")
public class ServletUsuarioLogin extends HttpServlet {
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
		try {
			login(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<String> listaErrores = new ArrayList<>();
		usuarioDAO = new DaoUsuario(miPool);
		String correoElectronico = "";
		String contrasenia = "";
		boolean contraseniaEncontrada = false;

		correoElectronico = request.getParameter("correoElectronico");
		contrasenia = request.getParameter("contrasenia");
		
		if (correoElectronico.length() == 0) {
			listaErrores.add("El correo no puede estar vacío");
		}

		if (correoElectronico.length() != 0 && !esEmail(correoElectronico)) {
			listaErrores.add("El correo no tiene un formato válido");
		}
		usuarioNuevo = usuarioDAO.getByCorreoElectronico(correoElectronico);
		if (usuarioNuevo == null) {
			listaErrores.add("No existe ese correo electrónico");
		} 

		if (contrasenia.length() == 0) {
			listaErrores.add("La contraseña no puede estar vacía");
		}
		
		if (usuarioNuevo != null) {
			if (contrasenia.equals(usuarioNuevo.getContrasenia())) {
				contraseniaEncontrada = true;
			} else {
				contraseniaEncontrada = false;
				listaErrores.add("Contraseña incorrecta");
			}
		}

		if (contraseniaEncontrada) {
			usuario = usuarioNuevo;
			request.getSession().setAttribute("usuarioLogueado", usuario);
			request.getRequestDispatcher("/bienvenida.jsp").forward(request, response);
		} else {
			request.setAttribute("listaErroresLogin", listaErrores);
			request.getRequestDispatcher("vistas/login.jsp").forward(request, response);
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
