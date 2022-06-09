package practicaFinal.Servidor.InfoServidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * Clase que voy a usar para guardar en el servidor una lista de objetos FlujosUsuario.
 * Aquí voy a guardar los flujos de entrada y salida del usuario (fin y fout).
 *
 */

public class FlujosUsuarios {
	private ObjectInputStream fin;
	private ObjectOutputStream fout;
	private String id;

	public FlujosUsuarios(String id, ObjectInputStream fin, ObjectOutputStream fout) {
		this.id = id;
		this.fin = fin;
		this.fout = fout;
	}

	public ObjectInputStream getFin() {
		return fin;
	}

	public ObjectOutputStream getFout() {
		return fout;
	}

	public String getId() {
		return id;
	}

}
