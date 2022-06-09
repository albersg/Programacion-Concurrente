package practicaFinal.Mensajes;

public class MensajePreparadoClienteServidor extends Mensaje {
	private static final long serialVersionUID = 1L;
	private int port;
	private String filename;
	private String ipUserDest;
	private String idUser;

	public MensajePreparadoClienteServidor(String ipSource, String ipDest, TiposMensaje type, int port, String filename,
			String ipUserDest, String idUser) {
		super(ipSource, ipDest, type);
		this.port = port;
		this.ipUserDest = ipUserDest;
		this.filename = filename;
		this.idUser = idUser;
	}

	public String getFilename() {
		return filename;
	}

	public String getIdUser() {
		return idUser;
	}

	public String getIpUserDest() {
		return ipUserDest;
	}

	public int getPort() {
		return port;
	}
}
