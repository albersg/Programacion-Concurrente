package practicaFinal.Concurrencia;

public interface RWController {
	
	// Devuelve el permiso para escribir
	public void release_read();

	// Devuelve el permiso para escribir
	public void release_write();

	// Pide permiso para leer
	public void request_read();

	// Pide permiso para escribir
	public void request_write();
}
