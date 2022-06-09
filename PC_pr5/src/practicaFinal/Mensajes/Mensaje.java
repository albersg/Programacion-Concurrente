package practicaFinal.Mensajes;

import java.io.Serializable;

public abstract class Mensaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private TiposMensaje type;
	private String ipSource;
	private String ipDest;

	public Mensaje(String ipSource, String ipDest, TiposMensaje type) {
		this.ipSource = ipSource;
		this.ipDest = ipDest;
		this.type = type;
	}

	public String getIpDest() {
		return ipDest;
	}

	public String getIpSource() {
		return ipSource;
	}

	public TiposMensaje getType() {
		return type;
	}

}
