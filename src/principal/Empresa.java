package principal;

import java.util.HashSet;

public class Empresa {
    
    // Datos
    private String cuit;
    private String nombreFantasia;
    private String telefono;
    private String email;
    private String nombreContacto;
    
    // Colección para búsquedas rápidas (O(1)) de autorizaciones
    private HashSet<String> dnisAutorizados; 

    // Operaciones (Constructor completo)
    public Empresa(String cuit, String nombreFantasia, String telefono, String email, String nombreContacto) {
        this.cuit = cuit;
        this.nombreFantasia = nombreFantasia;
        this.telefono = telefono;
        this.email = email;
        this.nombreContacto = nombreContacto;
        
        // Inicializamos el HashSet vacío para poder agregar DNIs luego sin que dé error
        this.dnisAutorizados = new HashSet<>();
    }

    // Agrega el DNI de un usuario a la lista de autorizados
    public void agregarUsuarioAutorizado(String dni) {
    	dnisAutorizados.add(dni);
        
    }

    // Verifica en O(1) si un DNI está dentro del HashSet de autorizados
    public boolean estaAutorizado(String dni) {
    		return dnisAutorizados.contains(dni);
    	}
        
    

    // Devuelve el identificador de la empresa
    public String obtenerCuit() {
        return this.cuit;
    }
}