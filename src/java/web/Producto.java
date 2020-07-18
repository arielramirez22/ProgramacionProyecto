package web;

public class Producto {

    String nombre;
    String precio;
    String foto; //url de la foto
    String cantidad;
    String descripcion;
     void validarFoto(){
        if (this.foto == null || this.foto.isEmpty()) {            
            //la foto que cargo el usuario
            this.foto = "https://us.123rf.com/450wm/naropano/naropano1606/naropano160600550/58727711-fondo-gris-oscuro-el-dise%C3%B1o-de-textura-fondo-del-grunge-.jpg?ver=6";
        } 
     }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", precio=" + precio + ", foto=" + foto + ", cantidad=" + cantidad + ", descripcion=" + descripcion + '}';
    }
    
    
}
