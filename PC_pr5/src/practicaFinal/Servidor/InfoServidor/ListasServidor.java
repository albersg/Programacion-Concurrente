package practicaFinal.Servidor.InfoServidor;

import practicaFinal.Cliente.Usuario;

public interface ListasServidor {
	
	// Función para eliminar un usuario del sistema
	public default void deleteUser(String id) {}
	// Función para añadir un nuevo usuario
	public default void newUser(FlujosUsuarios flujosUsuarios) {}
	
	public default void newUser(Usuario usuario) {}
}
