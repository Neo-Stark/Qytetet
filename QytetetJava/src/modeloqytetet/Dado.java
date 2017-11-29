package modeloqytetet;

public class Dado {

    private int numero;
    private static final Dado instancia = new Dado();

    private Dado() {
        numero = 0;
    }

    /*
    int tirar(){
        numero = (int) (Math.random() * 11) + 2;
        return numero;
    }
    
    int numero(){
        return numero;
    }
     */
    public static Dado getInstancia() {
        return instancia;
    }

        int tirar() {
        numero = (int) (Math.random() * 6) + 1;
        return numero;

    }
}
