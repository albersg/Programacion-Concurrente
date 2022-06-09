package ejercicio3;

public interface Almacen {
	/**
	* Stores (last) a product in the warehouse. If there is no hole, 
	* the process that executes the method will block until there is.
	 * @throws InterruptedException 
	*/
	public void almacenar(Producto producto) throws InterruptedException;
	/**
	* Extract the first available product. If not available products,
	* the process that executes the method will block until data is stored.
	 * @throws InterruptedException 
	*/
	public Producto extraer() throws InterruptedException;
}