package practicaFinal.Servidor.InfoServidor;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ListaFlujos implements ListasServidor {
	private ArrayList<FlujosUsuarios> fList;

	public ListaFlujos() {
		fList = new ArrayList<FlujosUsuarios>();
	}

	public void deleteUser(String id) {
		for (FlujosUsuarios u : fList) {
			if (id.equals(u.getId())) {
				fList.remove(u);
				break;
			}
		}
	}

	public ObjectOutputStream getFout(String id) {
		for (FlujosUsuarios u : fList) {
			if (id.equals(u.getId())) {
				return u.getFout();
			}
		}

		return null;
	}

	public void newUser(FlujosUsuarios flujosUsuarios) {
		fList.add(flujosUsuarios);
	}

}
