package practicaFinal.Cliente;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Clase para el usuario, donde guardamos su IP, su ID y la lista de ficheros (los nombres) del usuario.
 */

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ipUser;
	private String idUser;
	private ArrayList<String> infoList;

	public Usuario(String ipUser, String idUser, ArrayList<String> infoList) {
		this.ipUser = ipUser;
		this.idUser = idUser;
		this.infoList = infoList;
	}

	public void addFile(String nombreFichero) {
		if(!infoList.contains(nombreFichero))
			infoList.add(nombreFichero);
	}

	public String getIdUser() {
		return idUser;
	}

	public ArrayList<String> getInfoList() {
		return infoList;
	}
	
	public String getIpUser() {
		return ipUser;
	}
}
