package practicaFinal.Mensajes;

public class MensajeEmitirFichero extends Mensaje {
	private static final long serialVersionUID = 1L;
	private String ipUserSource;
	private String filename;
	private String idUser;

	public MensajeEmitirFichero(String ipSource, String ipDest, TiposMensaje type, String ipUserSource, String filename,
			String idUser) {
		super(ipSource, ipDest, type);
		this.ipUserSource = ipUserSource;
		this.filename = filename;
		this.idUser = idUser;
	}

	public String getFilename() {
		return filename;
	}

	public String getIdUser() {
		return idUser;
	}

	public String getIpUserSource() {
		return ipUserSource;
	}

}
