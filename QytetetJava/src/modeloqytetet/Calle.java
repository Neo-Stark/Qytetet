/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author fran
 */
public class Calle extends Casilla {

    private TituloPropiedad titulo;
    private int numHoteles;
    private int numCasas;

    public Calle(int numeroCasilla, int coste, TipoCasilla tipo, TituloPropiedad titulo) {
        super(numeroCasilla, coste, tipo);
        this.numHoteles = 0;
        this.numCasas = 0;
        setTitulo(titulo);

    }

    public TituloPropiedad getTitulo() {
        return titulo;
    }

    public int getNumHoteles() {
        return numHoteles;
    }

    public int getNumCasas() {
        return numCasas;
    }

    public int getCosteHipoteca() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int getPrecioEdificar() {
        return titulo.getPrecioEdificar();
    }

    private void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
        titulo.setCalle(this);
    }

    void setNumHoteles(int numHoteles) {
        this.numHoteles = numHoteles;
    }

    void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
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
        titulo.setHipotecada(false);
        int precio = (int) (1.1 * calcularValorHipoteca());
        return precio;
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

    boolean propietarioEncarcelado() {
        return titulo.propietarioEncarcelado();
    }

    boolean sePuedeEdificarCasa(int factorEspeculador) {
        return numCasas < 4 * factorEspeculador;
    }

    boolean sePuedeEdificarHotel(int factorEspeculador) {
        return numHoteles < 4 * factorEspeculador && numCasas == 4 * factorEspeculador;
    }

    @Override
    boolean soyEdificable() {

        return true;
    }

    boolean estaHipotecada() {

        return titulo.getHipotecada();
    }

    boolean tengoPropietario() {
        return titulo.tengoPropietario();
    }

    int venderTitulo() {
        int precioCompra = coste + (numCasas + numHoteles) * titulo.getPrecioEdificar();
        int precioVenta = (int) (titulo.getFactorRevalorizacion() * precioCompra);

        titulo.setPropietario(null);
        setNumCasas(0);
        setNumHoteles(0);

        return precioVenta;
    }

    int precioTotalComprar() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    @Override
    public String toString() {
        String s = super.toString();
        s += "\nCoste=" + coste
                + "\n\t" + titulo;
        if (titulo.tengoPropietario()) {
            s += "\n\tPropietario: " + titulo.getPropietario().getNombre();
        } else {
            s += "\n\tNo tiene propietario";
        }
        return s;
    }
}
