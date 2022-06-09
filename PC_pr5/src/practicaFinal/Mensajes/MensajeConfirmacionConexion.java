package practicaFinal.Mensajes;

public class MensajeConfirmacionConexion extends Mensaje {
	private static final long serialVersionUID = 1L;

	public MensajeConfirmacionConexion(String ipSource, String ipDest, TiposMensaje type) {
		super(ipSource, ipDest, type);
	}

}
