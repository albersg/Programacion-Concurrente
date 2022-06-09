package practicaFinal.Servidor.InfoServidor;

/*
 * En esta clase voy a guardar la información relacionada con el servidor,
 * el puerto, la dirección IP, y otra información que vaya surgiendo.
 */

public class DatosServidor {
	private static final String DEFAULT_IP = "127.0.0.1";
	private static final int DEFAULT_PORT = 9999;

	private String ip = DEFAULT_IP;
	private int port = DEFAULT_PORT;

	public String getIP() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public void setIP(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
