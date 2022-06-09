package practicaFinal.Servidor.InfoServidor;

import java.util.ArrayList;

import practicaFinal.Cliente.Usuario;

public class ListaUsuarios implements ListasServidor{
	private ArrayList<Usuario> uList;

	public ListaUsuarios() {
		uList = new ArrayList<Usuario>();
	}

	public void deleteUser(String id) {
		for (Usuario u : uList) {
			if (id.equals(u.getIdUser())) {
				uList.remove(u);
				break;
			}
		}
	}

	public ArrayList<Usuario> getUserList() {
		return uList;
	}
	
	public void newUser(Usuario usuario) {
		uList.add(usuario);
	}

	public Usuario searchClient(String nombreFichero) {
		for (Usuario u : uList) {
			for (String f : u.getInfoList()) {
				if (f.equals(nombreFichero))
					return u;
			}
		}

		return null;
	}

	public void updateUser(String id, String nombreFichero) {
		for (Usuario u : uList) {
			if (id.equals(u.getIdUser())) {
				u.addFile(nombreFichero);
				break;
			}
		}
	}

}
