package daointerface;
import modelo.JbUsuario;

public interface IDaoUsuario {
	public boolean insert( JbUsuario usuario) throws Exception;
	public JbUsuario  getByIdUsuario( Integer idUsuario) throws Exception;
	public JbUsuario  getByCorreoElectronico(String correoElectronico) throws Exception;
	public boolean update( JbUsuario  usuario) throws Exception;
}
