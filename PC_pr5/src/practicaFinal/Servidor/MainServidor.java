package practicaFinal.Servidor;

import java.io.IOException;

import practicaFinal.Servidor.InfoServidor.DatosServidor;

/*
 * 
 * Asignatura: Programación Concurrente
 * Apellidos, Nombre: Sánchez Gómez, Alberto
 * Grupo: 3º A
 * 
 */

public class MainServidor {

	/*
	 * Creo esta clase ya que en si uso la clase Servidor con el main ahí solo puedo
	 * usar métodos y atributos static. Aprovecho esta clase también para hacer el
	 * parseo de parámetros del servidor. Si se quiere usar una ip y un puerto en
	 * específico hay que pasarselo por parámetros al main. De la siguiente forma:
	 * ip (espacio) port Si no se establecen, se usan por defecto.
	 *
	 */

	public static void main(String args[]) {
		String ip;
		int port;

		DatosServidor ds = new DatosServidor();

		// Les asignamos los parámetros por defecto
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
