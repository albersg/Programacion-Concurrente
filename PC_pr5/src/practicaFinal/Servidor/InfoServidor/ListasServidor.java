package practicaFinal.Servidor.InfoServidor;

import practicaFinal.Cliente.Usuario;

public interface ListasServidor {
	
	// Funci�n para eliminar un usuario del sistema
	public default void deleteUser(String id) {}
	// Funci�n para a�adir un nuevo usuario
	public default void newUser(FlujosUsuarios flujosUsuarios) {}
	
	public default void newUser(Usuario usuario) {}
}
