/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author fran98
 */
public class TituloPropiedad {
    private String nombre;
    private Boolean hipotecada;
    private int alquilerBase;
    private float factorRevalorizado;
    private int hipotecaBase;
    private int precioEdificar;

    public TituloPropiedad(String nombre, int alquilerBase, float factorRevalorizado, int hipotecaBase, int precioEdificar) {
        this.nombre = nombre;
        this.hipotecada = false;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizado = factorRevalorizado;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
    }
    public String getNombre()
    {
        return nombre;
    }
    public Boolean getHipotecada()
    {
        return hipotecada;
    }
    public int getAlquiler()
    {
        return alquilerBase;
    }
    public float getFactorRevalorizado()
    {
        return factorRevalorizado;
    }
    public int getHipoteca()
    {
        return hipotecaBase;
    }
    public int getPrecioEdificar()
    {
        return precioEdificar;
    }
    public void setHipotecada(Boolean hipotecada)
    {
        this.hipotecada = hipotecada;
    }
    @Override
    public String toString()
    {
        return "Titulo propiedad{nombre=" + nombre + ", hipotecada=" + hipotecada 
                + ", alquiler base= " + alquilerBase + " factor revalorizado="
                + factorRevalorizado + ", hipoteca base" + hipotecaBase
                + ", precio edificar=" + precioEdificar + "}";
    }
    
    
}