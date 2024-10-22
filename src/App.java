
import MisProductos.GestionmisProductos;
import MisProductos.Productos;

public class App {
    public static void main(String[] args) {
        Thread.currentThread().setName("Principal");
        Productos manzana = new Productos(10);
        GestionmisProductos gestor1 = new GestionmisProductos(manzana, manzana.getPreciosProveedores().length);
        GestionmisProductos gestor2 = new GestionmisProductos(manzana, manzana.getPreciosProveedores().length);
        GestionmisProductos gestor3 = new GestionmisProductos(manzana, manzana.getPreciosProveedores().length);

        gestor1.start();
        gestor2.start();
        gestor3.start();

        /*
         * for(int i=0;i<manzana.getPreciosProveedores().length;i++){
         * manzana.addProducto(" proveedor " + i, i*10+1);
         * }
         */

         try {
            gestor1.join();
            gestor2.join();
            gestor3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        manzana.mostrarPreciosProveedores();
        manzana.mostrarMejorPrecio();
    }

}
