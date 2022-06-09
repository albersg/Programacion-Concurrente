package practicaFinal.Mensajes;

public class MensajePedirFichero extends Mensaje {
	private static final long serialVersionUID = 1L;
	private String filename;
	private String idUser;

	public MensajePedirFichero(String ipSource, String ipDest, TiposMensaje type, String filename, String idUser) {
		super(ipSource, ipDest, type);
		this.filename = filename;
		this.idUser = idUser;
	}

	public String getFilename() {
		return filename;
	}

	public String getIdUser() {
		return idUser;
	}

}
