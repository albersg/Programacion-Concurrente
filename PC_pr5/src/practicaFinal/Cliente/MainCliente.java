package practicaFinal.Cliente;

/*
 * 
 * Asignatura: Programaci�n Concurrente
 * Apellidos, Nombre: S�nchez G�mez, Alberto
 * Grupo: 3� A
 * 
 */

public class MainCliente {
	
	/*
	 * Creo esta clase ya que en si uso la clase Cliente con el main ah� solo puedo
	 * usar m�todos y atributos static. Aprovecho esta clase tambi�n para hacer el
	 * parseo de par�metros del cliente. Hay que pasarle la ip y el puerto por par�metros al main.
	 * De la siguiente forma: ip (espacio) port.
	 *
	 */

	public static void main(String args[]) {
		String ipC;
		int port;

		// Les asignamos los par�metros por defecto
		if (args.length != 2) {
			System.out.println("Debes indicar dos par�metros (separados por espacios):");
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
