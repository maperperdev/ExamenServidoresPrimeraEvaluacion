package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.JbUsuario;


@WebServlet("/ServletUsuarioLogout")
public class ServletUsuarioLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private JbUsuario usuario;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		usuario = (JbUsuario) request.getSession().getAttribute("usuarioLogueado");
		usuario = null;
		request.getSession().setAttribute("usuarioLogueado", usuario);
		request.getRequestDispatcher("/bienvenida.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
