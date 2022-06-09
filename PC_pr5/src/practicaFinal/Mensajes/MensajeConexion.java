package practicaFinal.Mensajes;

import java.util.ArrayList;

public class MensajeConexion extends Mensaje {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> infoList;
	private String idClient;

	public MensajeConexion(String ipSource, String ipDest, TiposMensaje type, ArrayList<String> infoList,
			String idClient) {
		super(ipSource, ipDest, type);
		this.infoList = infoList;
		this.idClient = idClient;
	}

	public String getIdClient() {
		return idClient;
	}

	public ArrayList<String> getInfoList() {
		return infoList;
	}
}
