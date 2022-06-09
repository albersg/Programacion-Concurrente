package practicaFinal.Cliente;

/*
 * 
 * Asignatura: Programación Concurrente
 * Apellidos, Nombre: Sánchez Gómez, Alberto
 * Grupo: 3º A
 * 
 */

public class MainCliente {
	
	/*
	 * Creo esta clase ya que en si uso la clase Cliente con el main ahí solo puedo
	 * usar métodos y atributos static. Aprovecho esta clase también para hacer el
	 * parseo de parámetros del cliente. Hay que pasarle la ip y el puerto por parámetros al main.
	 * De la siguiente forma: ip (espacio) port.
	 *
	 */

	public static void main(String args[]) {
		String ipC;
		int port;

		// Les asignamos los parámetros por defecto
		if (args.length != 2) {
			System.out.println("Debes indicar dos parámetros (separados por espacios):");
			System.out.println(" 1. IP Cliente");
			System.out.println(" 2. Puerto Cliente");

			return;
		} else {
			ipC = args[0];
			port = Integer.parseInt(args[1]);
		}

		new Cliente(ipC, port).start();
	}
}
