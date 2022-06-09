package ejercicio1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static final int PORT = 999;

	public static void main(String args[]) {
		try (ServerSocket listen = new ServerSocket(PORT)) {
			System.out.println("Levantando servidor...");
			System.out.println("Esperando conexiones...");
			Socket ss = listen.accept();
			System.out.println("Conexión establecida.");

			new Comunicacion(ss).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
