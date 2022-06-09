package practicaFinal.Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import practicaFinal.Cliente.Usuario;
import practicaFinal.Mensajes.Mensaje;
import practicaFinal.Mensajes.MensajeCerrarConexion;
import practicaFinal.Mensajes.MensajeConexion;
import practicaFinal.Mensajes.MensajeConfirmacionCerrarConexion;
import practicaFinal.Mensajes.MensajeConfirmacionConexion;
import practicaFinal.Mensajes.MensajeConfirmacionListaUsuarios;
import practicaFinal.Mensajes.MensajeEmitirFichero;
import practicaFinal.Mensajes.MensajeFicheroNoEncontrado;
import practicaFinal.Mensajes.MensajeListaUsuarios;
import practicaFinal.Mensajes.MensajePedirFichero;
import practicaFinal.Mensajes.MensajePreparadoClienteServidor;
import practicaFinal.Mensajes.MensajePreparadoServidorCliente;
import practicaFinal.Mensajes.TiposMensaje;
import practicaFinal.Servidor.InfoServidor.DatosServidor;
import practicaFinal.Servidor.InfoServidor.FlujosUsuarios;

public class OyenteCliente extends Thread {
	private ObjectInputStream fin;
	private ObjectOutputStream fout;
	private Servidor servidor;
	private DatosServidor ds;

	public OyenteCliente(Socket s, Servidor servidor) throws IOException {
		this.servidor = servidor;
		fin = new ObjectInputStream(s.getInputStream());
		fout = new ObjectOutputStream(s.getOutputStream());

		ds = new DatosServidor();
	}

	private void askForFile(MensajePedirFichero m) throws IOException {
		Usuario usuario = servidor.searchClient(m.getFilename());
		if (usuario != null) {
			ObjectOutputStream aux_fout = servidor.getFout(usuario.getIdUser());
			aux_fout.writeObject(new MensajeEmitirFichero(ds.getIP(), usuario.getIpUser(),
					TiposMensaje.MENSAJE_EMITIR_FICHERO, usuario.getIpUser(), m.getFilename(), m.getIdUser()));
			fout.flush();
			
			// Para actualizar la lista de ficheros del cliente
			servidor.updateClient(m.getIdUser(), m.getFilename());
		} else {
			fout.writeObject(new MensajeFicheroNoEncontrado(ds.getIP(), m.getIpSource(),
					TiposMensaje.MENSAJE_FICHERO_NO_ENCONTRADO, m.getFilename()));
			fout.flush();
		}
	}

	private void clientServerReady(MensajePreparadoClienteServidor m) throws IOException {
		ObjectOutputStream aux_fout = servidor.getFout(m.getIdUser());
		aux_fout.writeObject(new MensajePreparadoServidorCliente(m.getIpDest(), m.getIpUserDest(),
				TiposMensaje.MENSAJE_PREPARADO_SERVIDOR_CLIENTE, m.getIpSource(), m.getPort(), m.getFilename()));
		fout.flush();
	}

	private void closeConnection(MensajeCerrarConexion m) throws IOException {
		servidor.deleteUser(m.getIdClient());
		fout.writeObject(new MensajeConfirmacionCerrarConexion(m.getIpDest(), m.getIpSource(),
				TiposMensaje.MENSAJE_CONFIRMACION_CERRAR_CONEXION));
		fout.flush();
		fout.close();
		System.out.println("El cliente " + m.getIdClient() + " se ha desconectado del servidor.");
	}

	private void connection(MensajeConexion m) throws IOException {
		System.out.println("Se ha establecido conexión con el cliente " + m.getIdClient());
		servidor.newUser(new Usuario(m.getIpSource(), m.getIdClient(), m.getInfoList()),
				new FlujosUsuarios(m.getIdClient(), fin, fout));
		fout.writeObject(new MensajeConfirmacionConexion(m.getIpDest(), m.getIpSource(),
				TiposMensaje.MENSAJE_CONFIRMACION_CONEXION));
		fout.flush();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Mensaje m = (Mensaje) fin.readObject();

				switch (m.getType()) {
				case MENSAJE_CONEXION:
					connection((MensajeConexion) m);
					break;
				case MENSAJE_CERRAR_CONEXION:
					closeConnection((MensajeCerrarConexion) m);
					return;
				case MENSAJE_LISTA_USUARIOS:
					userList((MensajeListaUsuarios) m);
					break;
				case MENSAJE_PEDIR_FICHERO:
					askForFile((MensajePedirFichero) m);
					break;
				case MENSAJE_PREPARADO_CLIENTE_SERVIDOR:
					clientServerReady((MensajePreparadoClienteServidor) m);
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

	private void userList(MensajeListaUsuarios m) throws IOException {
		ArrayList<Usuario> listaUsuarios = servidor.getUserList();

		// Tengo que hacer el reset al fout, ya que debido a la implementación, si no
		// hago el reset manda el mismo objeto siempre (la misma lista).
		fout.reset();

		fout.writeObject(new MensajeConfirmacionListaUsuarios(m.getIpDest(), m.getIpSource(),
				TiposMensaje.MENSAJE_CONFIRMACION_LISTA_USUARIOS, listaUsuarios));
		fout.flush();
	}
}
