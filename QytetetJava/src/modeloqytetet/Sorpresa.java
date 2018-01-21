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
public class Sorpresa {
    private String texto;
    private TipoSorpresa tipo;
    private int valor;
    
    public Sorpresa (String texto, int valor, TipoSorpresa tipo)
    {
        this.texto = texto;
        this.tipo = tipo;
        this.valor = valor;
    }
    
    String getTexto()
    {
        return this.texto;
    }
    public TipoSorpresa getTipo()
    {
        return this.tipo;
    }
    int getValor()
    {
        return this.valor;
    }
    
    @Override
    public String toString() {
        return texto;
    } 
}
