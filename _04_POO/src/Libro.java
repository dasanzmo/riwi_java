public class Libro {

    //1. Atributos (que sean privados)
    private String titulo;
    private String autor;
    private String publicacion;

    private boolean prestado;

    public Libro(String titulo, String autor, String publicacion, boolean prestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.publicacion = publicacion;
        this.prestado = prestado;
    }


// 2. Métodos (que sean públicos)

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public void cambiarEstado(){

        this.prestado = !this.prestado;

//        if(this.prestado){
//            this.prestado = false;
//        }
//        else{
//            this.prestado = true;
//        }

        System.out.println("Estado de libro actualizado correctamente");
    }

    // Método toString();
    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", publicacion='" + publicacion + '\'' +
                ", prestado=" + prestado +
                '}';
    }
}
