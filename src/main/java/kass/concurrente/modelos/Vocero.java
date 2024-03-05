package kass.concurrente.modelos;

/**
 * Este ess quien lleva la cuenta de los prisioneros que han entrado a la
 * habitacion
 * a parte de los atributos de Prisionero, tambien posee un contador
 * 
 * @author <Su equipo>
 * @version 1.0
 */
public class Vocero extends Prisionero {
    protected Integer contador;

    /**
     * Constructor para el vocero
     * 
     * @param id       Id de prisionero
     * @param esVocero bool que indica si es vocero (debería ser true)
     * @param marcado  bool que indica si ya pasó
     */
    public Vocero(Integer id, Boolean esVocero, Boolean marcado) {
        super(id, esVocero, marcado);
        this.setContador(0);
        // Completar y hacer documentacion
    }

    /**
     * Regresa el contador del vocero, cuantos prisioneros han pasado
     * 
     * @return El contador del vocero
     */
    public Integer getContador() {
        return contador;
    }

    /**
     * Establece el contador del vocero, cuantos prisioneros han pasado
     * 
     * @param contador Cuantos prisioneros han pasado
     */
    public void setContador(Integer contador) {
        this.contador = contador;
    }

    /**
     * Incrementa el contador del vocero en 1
     */
    public void incrementaContador() {
        this.contador += 1;
    }

}
