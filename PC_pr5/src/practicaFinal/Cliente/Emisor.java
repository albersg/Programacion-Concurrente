package practicaFinal.Cliente;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class Emisor extends Thread {
	private ServerSocket sSocket;
	private String filename;
	private Socket socket;
	private ObjectOutputStream fout;

	public Emisor(ServerSocket sSocket, String filename) {
		this.filename = filename;
		this.sSocket = sSocket;
	}

	@Override
	public void run() {
		try {
			// Aceptamos la conexión.
			socket = sSocket.accept();

			// Emitimos el fichero
			fout = new ObjectOutputStream(socket.getOutputStream());

			File f = new File("./ficherosIn/" + filename + ".txt");

			// Leemos todo el contenido
			byte[] content = Files.readAllBytes(f.toPath());

			// Lo escribimos por el fout correspondiente
			fout.writeObject(content);

			fout.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
