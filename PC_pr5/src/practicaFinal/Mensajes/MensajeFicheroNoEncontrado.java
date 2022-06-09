package practicaFinal.Mensajes;

public class MensajeFicheroNoEncontrado extends Mensaje {
	private static final long serialVersionUID = 1L;
	private String filename;

	public MensajeFicheroNoEncontrado(String ipSource, String ipDest, TiposMensaje type, String filename) {
		super(ipSource, ipDest, type);
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}
}