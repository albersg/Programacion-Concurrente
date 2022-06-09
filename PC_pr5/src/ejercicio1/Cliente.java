package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	public static final int PORT = 999;

	public static void main(String args[]) {
		try {
			Socket s = new Socket("localhost", PORT);

			BufferedReader fin = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter fout = new PrintWriter(s.getOutputStream());

			fout.println("./src/ejercicio1/datos.txt");
			fout.flush();

			String line = fin.readLine();
			while (!line.isEmpty()) {
				System.out.println(line);
				line = fin.readLine();
			}
			
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
