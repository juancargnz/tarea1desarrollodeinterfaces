package MisProductos;

public class GestionmisProductos extends Thread {
    private Productos productos;
    private int numPrecios;

    public GestionmisProductos(Productos productos, int numPrecios) {
        this.productos = productos;
        this.numPrecios = numPrecios;

    }

    public void run() {
        PrecioProducto precioProducto;

        for (int i = 0; i < numPrecios; i++) {

            productos.addProducto(this.getName() + "-" + i, i * 10 + 1);
        }
    }

}
