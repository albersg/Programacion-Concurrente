package practicaFinal.Servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import practicaFinal.Cliente.Usuario;
import practicaFinal.Concurrencia.MonitorRWController;
import practicaFinal.Concurrencia.SemRWController;
import practicaFinal.Servidor.InfoServidor.DatosServidor;
import practicaFinal.Servidor.InfoServidor.FlujosUsuarios;
import practicaFinal.Servidor.InfoServidor.ListaFlujos;
import practicaFinal.Servidor.InfoServidor.ListaUsuarios;

public class Servidor extends Thread {
	// Controlador lectores/escritores para la tabla de los usuarios
	private MonitorRWController uController;
	// Controlador lectores/escritores para la tabla de los flujos
	private SemRWController fController;
	private ListaUsuarios uList;
	private ListaFlujos fList;
	private ServerSocket socket;
	private DatosServidor ds;

	public Servidor() throws IOException {
		fList = new ListaFlujos();
		uList = new ListaUsuarios();

		uController = new MonitorRWController();
		fController = new SemRWController();

		ds = new DatosServidor();
		socket = new ServerSocket(ds.getPort());
	}

	public void deleteUser(String id) {
		uController.request_write();
		uList.deleteUser(id);
		uController.release_write();

		fController.request_write();
		fList.deleteUser(id);
		fController.release_write();
	}

	public ObjectOutputStream getFout(String idUsuario) {
		fController.request_read();
		ObjectOutputStream aux = fList.getFout(idUsuario);
		fController.release_read();

		return aux;
	}

	public ArrayList<Usuario> getUserList() {
		uController.request_read();
		ArrayList<Usuario> aux = uList.getUserList();
		uController.release_read();

		return aux;
	}

	public void newUser(Usuario usuario, FlujosUsuarios flujosUsuarios) {
		uController.request_write();
		uList.newUser(usuario);
		uController.release_write();

		fController.request_write();
		fList.newUser(flujosUsuarios);
		fController.release_write();
	}

	@Override
	public void run() {
		System.out.println("Levantando servidor...");
		while (true) {
			try {
				System.out.println("Esperando conexiones...");
				Socket s = socket.accept();
				System.out.println("Conexión establecida.");

				// Creamos el OyenteCliente.
				// Le pasamos el socket y la referencia a Servidor, para guardar posteriormente
				// desde OyenteCliente la información que nos pasen los clientes.
				new OyenteCliente(s, this).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Usuario searchClient(String nombreFichero) {
		uController.request_read();
		Usuario aux = uList.searchClient(nombreFichero);
		uController.release_read();

		return aux;
	}

	public void updateClient(String idUsuario, String nombreFichero) {
		uController.request_write();
		uList.updateUser(idUsuario, nombreFichero);
		uController.release_write();
	}
}
