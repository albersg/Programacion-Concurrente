package practicaFinal.Cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import practicaFinal.Concurrencia.LockRompeEmpate;
import practicaFinal.Mensajes.Mensaje;
import practicaFinal.Mensajes.MensajeCerrarConexion;
import practicaFinal.Mensajes.MensajeConexion;
import practicaFinal.Mensajes.MensajeListaUsuarios;
import practicaFinal.Mensajes.MensajePedirFichero;
import practicaFinal.Mensajes.TiposMensaje;
import practicaFinal.Servidor.InfoServidor.DatosServidor;

public class Cliente extends Thread {
	private int port;
	private String ipC;
	private String username;
	private Scanner scanner;
	private DatosServidor ds;
	private Socket socket;
	private ObjectOutputStream fout;
	private LockRompeEmpate lock;
	private ArrayList<String> fileList;

	public Cliente(String ipC, int port) {
		this.ipC = ipC;
		this.port = port;

		lock = new LockRompeEmpate();
		ds = new DatosServidor();
		fileList = new ArrayList<String>();
	}

	private void askForFile() throws IOException {
		System.out.print("Introduce el nombre del fichero que quieres: ");
		String name = scanner.nextLine();
		fout.writeObject(new MensajePedirFichero(ipC, ds.getIP(), TiposMensaje.MENSAJE_PEDIR_FICHERO, name, username));
		fout.flush();
	}

	private void checkUserList() throws IOException {
		fout.writeObject(new MensajeListaUsuarios(ipC, ds.getIP(), TiposMensaje.MENSAJE_LISTA_USUARIOS));
		fout.flush();
	}

	private void createSocketServer() throws UnknownHostException, IOException {
		socket = new Socket(ipC, port);

		fout = new ObjectOutputStream(socket.getOutputStream());
	}

	private void exit() throws IOException {
		fout.writeObject(new MensajeCerrarConexion(ipC, ds.getIP(), username, TiposMensaje.MENSAJE_CERRAR_CONEXION));
		fout.flush();
	}

	public ArrayList<String> getFileList() {
		return fileList;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	private void menu() {
		System.out.println("------------------- MENÚ ---------------------");
		System.out.println("   1. Consultar la lista de usuarios");
		System.out.println("   2. Pedir fichero");
		System.out.println("   3. Salir");
		System.out.print("Elije una opción: ");
	}

	private void readFilenames() {
		System.out.println("Tienes que introducir el nombre de los ficheros que dispones en tu sistema.");
		System.out.println("Introduce simplemente su nombre, sin la ruta ni la extensión .txt");
		System.out.println("Introduce el nombre de los ficheros que tienes disponibles: ");
		System.out.println("Para salir introduce \"exit\"");
		String filename = "";

		filename = scanner.nextLine();
		while (!filename.equals("exit")) {
			fileList.add(filename);
			filename = scanner.nextLine();
		}
	}
	
	private void readUsername() {
		scanner = new Scanner(System.in);
		System.out.print("Introduce tu nombre de usuario: ");

		username = scanner.nextLine();

		while (username.isEmpty()) {
			System.out.println("Nombre de usuario introducido no válido.");
			System.out.print("Vuelve a introducir tu nombre de usuario: ");

			username = scanner.nextLine();
		}
	}
	

	@Override
	public void run() {
		try {
			// Lectura del nombre del usuario
			readUsername();

			// Creación del socket con el servidor
			createSocketServer();

			// Creación del thread OyenteServidor
			OyenteServidor t = new OyenteServidor(socket, this, lock);
			t.start();

			// Leemos los ficheros que el cliente tiene disponible
			readFilenames();

			// Envío de MensajeConexion
			t.signal();
			fout.writeObject(new MensajeConexion(ipC, ds.getIP(), TiposMensaje.MENSAJE_CONEXION, fileList, username));
			fout.flush();

			while (true) {

				// Esperamos a que el servidor acepte la conexión en la primera iteración
				// Para las siguientes, esperamos que que le llegue el mensaje al OyenteServidor
				// Usamos un lock tie-breaker para dos procesos
				lock.requestLockP1();

				// Mostramos el menú
				menu();
				int userAction = Integer.parseInt(scanner.nextLine());

				// Comprobamos que sea una opción válida
				while (userAction < 1 || userAction > 3) {
					System.out.println("Tienes que seleccionar una opción válida.\n");
					menu();
					System.out.print("Elije una opción válida: ");
					userAction = scanner.nextInt();
				}
				
				System.out.println();
				
				lock.releaseLockP1();

				// Avisamos al OyenteServidor de que ya hemos seleccionado una opción.
				// OyenteServidor coge el lock y así el Cliente no vuelve a mostrar el 
				// menú hasta que OyenteServidor suelte el lock.
				t.signal();
								
				switch (userAction) {
				case 1:
					// El usuario pide la lista de usuarios
					checkUserList();
					break;
				case 2:
					// El usuario pide un fichero
					askForFile();
					break;
				case 3:
					// El usuario quiere salir
					exit();
					return;
				default:
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendMsg(Mensaje m) {
		try {
			fout.writeObject(m);
			fout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
