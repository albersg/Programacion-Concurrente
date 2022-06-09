package practicaFinal.Mensajes;

public class MensajeCerrarConexion extends Mensaje {
	private static final long serialVersionUID = 1L;
	private String idClient;

	public MensajeCerrarConexion(String ipSource, String ipDest, String idClient, TiposMensaje type) {
		super(ipSource, ipDest, type);
		this.idClient = idClient;
	}

	public String getIdClient() {
		return idClient;
	}
}
