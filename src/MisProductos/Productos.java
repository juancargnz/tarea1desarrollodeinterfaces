package MisProductos;

import java.util.concurrent.locks.ReentrantLock;

public class Productos {
    private PrecioProducto[] preciosProveedores;
    private ReentrantLock cerrojo;
    private int sigProveedor;// ALMACENA EL SIG HUECO LIBRE

    // constructor
    public Productos(int MaxPreciosProductos) {
        // inicializo
        preciosProveedores = new PrecioProducto[MaxPreciosProductos];
        sigProveedor = 0;
        // DENTRO DEL ARRAY INICIALMENTE hay null en cada hueco
        cerrojo = new ReentrantLock();
    }

    // metodos solicitados
    public void addProducto(String proveedor, float precio) {
        accederseccioncritica();
        try {
            if (sigProveedor == preciosProveedores.length) {
                System.out.println("no se puede añadir el almacen esta lleno");
            } else {
                // tengo hueco
                preciosProveedores[sigProveedor] = new PrecioProducto(proveedor, precio);
                sigProveedor++;
            }

        } finally {
            salirSeccionCritica();
        }

    }

    public void mostrarPreciosProveedores() {
        accederseccioncritica();
        try {
            if (sigProveedor == 0) {
                System.out.println("no hay informacion disponible para ese producto");
            } else
                for (int i = 0; i < sigProveedor; i++) {
                    System.out.println("El proveedor:" +
                            preciosProveedores[i].getProveedor() +
                            " ofrece el producto al precio " +
                            preciosProveedores[i].getPrecio() + "$.");
                }
        } finally {
            salirSeccionCritica();
        }
    }

    public void mostrarMejorPrecio() {
        accederseccioncritica();
        try {

            PrecioProducto mejorProducto = new PrecioProducto(null, Float.POSITIVE_INFINITY);
            // float mejorProducto= Float.POSITIVE_INFINITY;
            for (int i = 0; i < sigProveedor; i++) {
                if (preciosProveedores[i].getPrecio() < mejorProducto.getPrecio()) {
                    mejorProducto = preciosProveedores[i];
                }
            }
            System.out.println("el mejor precio para el producto es: " + mejorProducto.getPrecio()
                    + " y es ofrecido por el proveedor" + mejorProducto.getProveedor());
        } finally {
            salirSeccionCritica();
        }
    }

    // getters y setters
    public PrecioProducto[] getPreciosProveedores() {
        accederseccioncritica();
        try {
            return preciosProveedores;
        } finally {
            salirSeccionCritica();
        }

    }

    public void setPreciosProveedores(PrecioProducto[] preciosProveedores) {

        this.preciosProveedores = preciosProveedores;
    }

    private void accederseccioncritica() {
        cerrojo.lock();
    }

    private void salirSeccionCritica() {
        cerrojo.unlock();

    }
}
