package practicaFinal.Mensajes;

public class MensajeConfirmacionCerrarConexion extends Mensaje {
	private static final long serialVersionUID = 1L;

	public MensajeConfirmacionCerrarConexion(String ipSource, String ipDest, TiposMensaje type) {
		super(ipSource, ipDest, type);
	}

}
