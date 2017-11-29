/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

public class Casilla {

    private int numeroCasilla;
    private int coste;
    private int numHoteles;
    private int numCasas;
    private TipoCasilla tipo;
    private TituloPropiedad titulo;

    public Casilla(int numeroCasilla, int coste, TipoCasilla tipo, TituloPropiedad titulo) {
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        this.numHoteles = 0;
        this.numCasas = 0;
        this.tipo = tipo;
        this.titulo = titulo;
        titulo.setCasilla(this);
    }

    //El constructor para las casillas que no son de tipo calle
    public Casilla(int numeroCasilla, int coste, TipoCasilla tipo) {
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        this.numHoteles = 0;
        this.numCasas = 0;
        this.tipo = tipo;
    }

    TipoCasilla getTipo() {
        return this.tipo;
    }

    public TituloPropiedad getTitulo() {
        return titulo;
    }

    int getCoste() {
        return this.coste;
    }

    public int getNumeroCasilla() {
        return numeroCasilla;
    }

    int getNumHoteles() {
        return numHoteles;
    }

    int getNumCasas() {
        return numCasas;
    }

    int getCosteHipoteca() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    int getPrecioEdificar() {
        return titulo.getPrecioEdificar();
    }

    private void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }

    void setNumHoteles(int numHoteles) {
        this.numHoteles = numHoteles;
    }

    void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }

    @Override
    public String toString() {
        String s = "Casilla{" + "numeroCasilla=" + numeroCasilla + ", coste=" + coste + ", numHoteles=" + numHoteles + ", numCasas=" + numCasas + ", tipo=" + tipo + " }\n";
        if (titulo != null) {
            s += " titulo=" + titulo;
        }
        return s;
    }

    TituloPropiedad asignarPropietario(Jugador jugador) {
        titulo.setPropietario(jugador);
        return titulo;
    }

    int calcularValorHipoteca() {
        int hipotecaBase = titulo.getHipotecaBase();
        return hipotecaBase + (int) (numCasas * 0.5 * hipotecaBase + numHoteles * hipotecaBase);
    }

    int cancelarHipoteca() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    int cobrarAlquiler() {
        int costeAlquilerBase = titulo.getAlquilerBase();
        int costeAlquiler = costeAlquilerBase + (int) (numCasas * 0.5 + numHoteles * 2);
        titulo.cobrarAlquiler(costeAlquiler);
        return costeAlquiler;
    }

    int edificarCasa() {
        setNumCasas(numCasas + 1);
        return titulo.getPrecioEdificar();
    }

    int edificarHotel() {
        setNumHoteles(numHoteles + 1);
        setNumCasas(0);
        return titulo.getPrecioEdificar();
    }

    int hipotecar() {
        titulo.setHipotecada(Boolean.TRUE);
        return calcularValorHipoteca();
    }

    int precioTotalComprar() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    boolean propietarioEncarcelado() {
        return titulo.propietarioEncarcelado();
    }

    boolean sePuedeEdificarCasa() {
        return numCasas < 4;
    }

    boolean sePuedeEdificarHotel() {
        return numHoteles < 4 && numCasas == 4;
    }

    boolean soyEdificable() {

        return tipo == TipoCasilla.CALLE && (numCasas < 4 || numHoteles < 4);
    }

    boolean estaHipotecada() {

        return titulo.getHipotecada();
    }

    boolean tengoPropietario() {
        return titulo.tengoPropietario();
    }

    int venderTitulo() {
        int precioCompra = coste + (numCasas + numHoteles) * titulo.getPrecioEdificar();
        int precioVenta = precioCompra + (int) titulo.getFactorRevalorizacion() * precioCompra;

        titulo.setPropietario(null);
        setNumCasas(0);
        setNumHoteles(0);

        return precioVenta;
    }

    void asignarTituloPropiedad() {

    }
}
