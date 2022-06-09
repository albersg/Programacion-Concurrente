package practicaFinal.Mensajes;

import java.util.ArrayList;

import practicaFinal.Cliente.Usuario;

public class MensajeConfirmacionListaUsuarios extends Mensaje {
	private static final long serialVersionUID = 1L;
	private ArrayList<Usuario> userList;

	public MensajeConfirmacionListaUsuarios(String ipSource, String ipDest, TiposMensaje type,
			ArrayList<Usuario> userList) {
		super(ipSource, ipDest, type);
		this.userList = userList;
	}

	public ArrayList<Usuario> getUserList() {
		return userList;
	}

}
