package practicaFinal.Cliente;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receptor extends Thread {
	private String filename;
	private Socket socket;
	private ObjectInputStream fin;
	private String ip;
	private int port;

	public Receptor(String filename, String ip, int port) {
		this.filename = filename;
		this.ip = ip;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(ip, port);
			fin = new ObjectInputStream(socket.getInputStream());

			File file = new File("./ficherosOut/" + filename + ".txt");

			// Leemos todo el contenido
			BufferedOutputStream buffout = new BufferedOutputStream(new FileOutputStream(file));
			byte[] content = (byte[]) fin.readObject();

			// Lo mandamos por el buffer de salida
			buffout.write(content);

			buffout.close();
			fin.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
