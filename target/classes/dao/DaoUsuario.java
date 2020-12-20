package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.sql.DataSource;

import daointerface.IDaoUsuario;

import modelo.JbUsuario;

public class DaoUsuario implements IDaoUsuario {
	private static final String SQL_INSERT = "insert into tusuario (`nombre`, `apellido`, `fechaNacimiento`, `correoElectronico`,"
			+ "`contrasenia`, `fechaRegistro`, `fechaModificacion`)" + " values (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE tusuario SET nombre = ?, apellido = ?,"
			+ "fechaNacimiento = ?, correoElectronico = ?, contrasenia= ?, fechaModificacion = ? WHERE idUsuario = ?";
	private static final String SQL_READ_BY_ID = "SELECT * FROM tusuario WHERE idUsuario = ?";
	private static final String SQL_READ_BY_EMAIL = "SELECT * FROM tusuario WHERE correoElectronico = ?";

	private DataSource origenDatos;

	public DaoUsuario(DataSource origenDatos) {
		this.origenDatos = origenDatos;
	}

	@Override
	public boolean insert(JbUsuario usuario) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = origenDatos.getConnection();
			ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellido());
			ps.setDate(3, Date.valueOf(usuario.getFechaNacimiento()));
			ps.setNString(4, usuario.getCorreoElectronico());
			ps.setString(5, usuario.getContrasenia());
			ps.setDate(6, Date.valueOf(LocalDate.now()));
			ps.setDate(7, Date.valueOf(LocalDate.now()));

			if (ps.executeUpdate() > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					usuario.setIdUsuario(rs.getInt(1));
				}
				return true;
			}

		} catch (Exception e) {

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public JbUsuario getByIdUsuario(Integer idUsuarioInput) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		JbUsuario usuarioEncontrado = null;
		try {
			con = origenDatos.getConnection();
			ps = con.prepareStatement(SQL_READ_BY_ID);
			ps.setInt(1, idUsuarioInput);
			rs = ps.executeQuery();
			System.out.println(rs.toString());
			while (rs.next()) {
				int idUsuario = rs.getInt("idUsuario");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				LocalDate fechaNacimiento = LocalDate.parse(rs.getString("fechaNacimiento"));
				String coreoElectronico = rs.getString("correoElectronico");
				String contrasenia = rs.getString("contrasenia");
				String fechaRegistro = rs.getString("fechaRegistro");
				String fechaModificacion = rs.getString("fechaModificacion");
				usuarioEncontrado = new JbUsuario(idUsuario, nombre, apellido, fechaNacimiento, coreoElectronico,
						contrasenia, fechaRegistro, fechaModificacion);
			}
			return usuarioEncontrado;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public JbUsuario getByCorreoElectronico(String correoElectronicoInput) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		JbUsuario usuarioEncontrado = null;
		try {
			con = origenDatos.getConnection();
			ps = con.prepareStatement(SQL_READ_BY_EMAIL);
			ps.setString(1, correoElectronicoInput);
			rs = ps.executeQuery();
			System.out.println(rs.toString());
			while (rs.next()) {
				int idUsuario = rs.getInt("idUsuario");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				LocalDate fechaNacimiento = LocalDate.parse(rs.getString("fechaNacimiento"));
				String coreoElectronico = rs.getString("correoElectronico");
				String contrasenia = rs.getString("contrasenia");
				String fechaRegistro = rs.getString("fechaRegistro");
				String fechaModificacion = rs.getString("fechaModificacion");
				usuarioEncontrado = new JbUsuario(idUsuario, nombre, apellido, fechaNacimiento, coreoElectronico,
						contrasenia, fechaRegistro, fechaModificacion);
			}
			return usuarioEncontrado;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean update(JbUsuario usuario) throws Exception {
		PreparedStatement ps = null;
		// ResultSet rs = null;
		Connection con = null;
		boolean result = false;
		try {
			con = origenDatos.getConnection();
			ps = con.prepareStatement(SQL_UPDATE);
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getApellido());
			ps.setDate(3, Date.valueOf(usuario.getFechaNacimiento()));
			ps.setString(4, usuario.getCorreoElectronico());
			ps.setString(5, usuario.getContrasenia());
			ps.setDate(6, Date.valueOf(usuario.getFechaModificacion()));
			ps.setInt(7, usuario.getIdUsuario());
			result = ps.execute();
			System.out.println("result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
