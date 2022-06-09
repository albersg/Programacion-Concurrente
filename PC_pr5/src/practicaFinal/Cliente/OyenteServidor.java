package practicaFinal.Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import practicaFinal.Concurrencia.LockRompeEmpate;
import practicaFinal.Mensajes.Mensaje;
import practicaFinal.Mensajes.MensajeConfirmacionListaUsuarios;
import practicaFinal.Mensajes.MensajeEmitirFichero;
import practicaFinal.Mensajes.MensajeFicheroNoEncontrado;
import practicaFinal.Mensajes.MensajePreparadoClienteServidor;
import practicaFinal.Mensajes.MensajePreparadoServidorCliente;
import practicaFinal.Mensajes.TiposMensaje;

public class OyenteServidor extends Thread {
	private Socket s;
	private ObjectInputStream fin;
	private Cliente client;
	private LockRompeEmpate lock;

	public OyenteServidor(Socket s, Cliente client, LockRompeEmpate lock) throws IOException {
		this.s = s;
		this.client = client;
		this.lock = lock;

		fin = new ObjectInputStream(s.getInputStream());
	}

	private void confirmationUserList(MensajeConfirmacionListaUsuarios m) {
		ArrayList<Usuario> userList = m.getUserList();
		System.out.println("-------------- LISTA DE USUARIOS --------------");
		for (Usuario u : userList) {
			System.out.println("Usuario: " + u.getIdUser());
			System.out.println(" IP: " + u.getIpUser());
			System.out.println(" Ficheros:");

			int count = 1;
			for (String f : u.getInfoList()) {
				System.out.println("  Fichero " + count + ": " + f);
				count++;
			}

			System.out.println();
		}

	}

	private void emitFile(MensajeEmitirFichero m) throws IOException {
		ServerSocket s = new ServerSocket(0);
		MensajePreparadoClienteServidor mClient = new MensajePreparadoClienteServidor(m.getIpDest(), m.getIpSource(),
				TiposMensaje.MENSAJE_PREPARADO_CLIENTE_SERVIDOR, s.getLocalPort(), m.getFilename(), m.getIpUserSource(),
				m.getIdUser());
		client.sendMsg(mClient);
		Emisor emisor = new Emisor(s, m.getFilename());
		emisor.start();
	}

	private void fileNotFound(MensajeFicheroNoEncontrado m) {
		System.out.println("El fichero " + m.getFilename() + " solicitado no existe en el sistema\n");
	}
	
	public void signal() {
		lock.requestLockP2();
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Mensaje m = (Mensaje) fin.readObject();

				switch (m.getType()) {
				case MENSAJE_CONFIRMACION_CONEXION:
					System.out.println("Conexión establecida.\n");
					lock.releaseLockP2();
					break;
				case MENSAJE_CONFIRMACION_CERRAR_CONEXION:
					System.out.println("Conexión cerrada.\n");
					fin.close();
					s.close();
					return;
				case MENSAJE_CONFIRMACION_LISTA_USUARIOS:
					// Función para mostrar la información de todos los usuarios
					confirmationUserList((MensajeConfirmacionListaUsuarios) m);
					lock.releaseLockP2();
					break;
				case MENSAJE_EMITIR_FICHERO:
					// Función para crear el Emisor que enviará el fichero al otro cliente
					emitFile((MensajeEmitirFichero) m);
					break;
				case MENSAJE_PREPARADO_SERVIDOR_CLIENTE:
					// Función para crear el Receptor que recibirá el fichero del otro cliente
					serverClienteReady((MensajePreparadoServidorCliente) m);
					lock.releaseLockP2();
					break;
				case MENSAJE_FICHERO_NO_ENCONTRADO:
					// Función para manejar el caso de que el fichero no exista
					fileNotFound((MensajeFicheroNoEncontrado) m);
					lock.releaseLockP2();
					break;
				default:
					System.err.println("ERROR");
					break;
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void serverClienteReady(MensajePreparadoServidorCliente m) {
		Receptor receptor = new Receptor(m.getFilename(), m.getIpUserSource(), m.getPort());
		receptor.start();
	}
}
