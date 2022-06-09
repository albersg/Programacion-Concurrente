package practicaFinal.Servidor;

import java.io.IOException;

import practicaFinal.Servidor.InfoServidor.DatosServidor;

/*
 * 
 * Asignatura: Programaci�n Concurrente
 * Apellidos, Nombre: S�nchez G�mez, Alberto
 * Grupo: 3� A
 * 
 */

public class MainServidor {

	/*
	 * Creo esta clase ya que en si uso la clase Servidor con el main ah� solo puedo
	 * usar m�todos y atributos static. Aprovecho esta clase tambi�n para hacer el
	 * parseo de par�metros del servidor. Si se quiere usar una ip y un puerto en
	 * espec�fico hay que pasarselo por par�metros al main. De la siguiente forma:
	 * ip (espacio) port Si no se establecen, se usan por defecto.
	 *
	 */

	public static void main(String args[]) {
		String ip;
		int port;

		DatosServidor ds = new DatosServidor();

		// Les asignamos los par�metros por defecto
		if (args.length != 2) {
			ip = ds.getIP();
			port = ds.getPort();
		} else {
			ip = args[0];
			port = Integer.parseInt(args[1]);
		}

		ds.setIP(ip);
		ds.setPort(port);

		try {
			new Servidor().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
