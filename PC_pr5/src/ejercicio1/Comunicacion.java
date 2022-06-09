package ejercicio1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Comunicacion extends Thread {
	Socket s;
	BufferedReader fin;
	PrintWriter fout;

	public Comunicacion(Socket s) throws IOException {
		this.s = s;
		this.fin = new BufferedReader(new InputStreamReader(s.getInputStream()));
		this.fout = new PrintWriter(s.getOutputStream());

	}

	private String readFile(String fileName) {
		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				contentBuilder.append(sCurrentLine).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}

	@Override
	public void run() {
		System.out.println("El cliente se ha conectado con el servidor.");
		try {
			String fileName = fin.readLine();
			System.out.println("El cliente ha solicitado el contenido del fichero " + fileName);

			String fileData = readFile(fileName);

			fout.println(fileData);
			fout.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
