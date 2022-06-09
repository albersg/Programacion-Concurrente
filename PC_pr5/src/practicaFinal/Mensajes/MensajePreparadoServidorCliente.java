package practicaFinal.Mensajes;

public class MensajePreparadoServidorCliente extends Mensaje {

	private static final long serialVersionUID = 1L;
	private String ipUserSource;
	private String filename;
	private int port;

	public MensajePreparadoServidorCliente(String ipSource, String ipDest, TiposMensaje type, String ipUserSource,
			int port, String filename) {
		super(ipSource, ipDest, type);
		this.ipUserSource = ipUserSource;
		this.filename = filename;
		this.port = port;
	}

	public String getFilename() {
		return filename;
	}

	public String getIpUserSource() {
		return ipUserSource;
	}

	public int getPort() {
		return port;
	}

}
