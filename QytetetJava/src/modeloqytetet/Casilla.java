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

    private void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }
    int getCoste()
    {
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

    }

    int calcularValorHipoteca() {

    }

    int cancelarHipoteca() {

    }

    int cobrarAlquiler() {

    }

    int edificarCasa() {

    }

    int edificarHotel() {

    }

    int getCosteHipoteca() {

    }

    int getPrecioEdificar() {

    }

    int hipotecar() {

    }

    int precioTotalComprar() {

    }

    boolean propietarioEncarcelado() {

    }

    boolean sePuedeEdificarCasa() {

    }
    boolean soyEdificable(){
        
        return (tipo == TipoCasilla.CALLE);
    }
    boolean estaHipotecada(){
        
        return titulo.getHipotecada();
    }
    
    boolean tengoPropietario() {
        
    }
    int venderTitulo(){
        
    }
    void asignarTituloPropiedad(){
        
    }
}
