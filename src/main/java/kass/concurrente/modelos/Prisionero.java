package kass.concurrente.modelos;

/**
 * Clase que modela un prisioner
 * 
 * @version 1.0
 * @author <Su equipo>
 */
public class Prisionero {
    protected Integer id;
    protected Boolean esVocero;
    protected Boolean marcado;

    /**
     * Metodo constructor para generar un prisionero
     * 
     * @param id       El identificador del prisionero
     * @param esVocero true si es Vocero false en otro caso
     * @param marcado  true si ya paso
     */
    public Prisionero(Integer id, Boolean esVocero, Boolean marcado) {
        this.setId(id);
        this.setEsVocero(esVocero);
        this.setMarcado(marcado);
    }

    /**
     * Devuelve el Id del prisionero
     * 
     * @return id del prisionero
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el id de este prisionero
     * 
     * @param id Id del prisionero
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Indica si el prisionero es vocero
     * 
     * @return true si es vocero, false si no lo es
     */
    public Boolean getEsVocero() {
        return esVocero;
    }

    /**
     * Establece si el prisionero es vocero o no
     * 
     * @param esVocero booleano para indicar si lo es o no
     */
    public void setEsVocero(Boolean esVocero) {
        this.esVocero = esVocero;
    }

    /**
     * Indica si el prisionero ya pasó o no
     * 
     * @return true si ya pasó, false si no
     */
    public Boolean getMarcado() {
        return marcado;
    }

    /**
     * Establece si el prisionero ya pasó
     * 
     * @param marcado booleano para indicar si pasó o no
     */
    public void setMarcado(Boolean marcado) {
        this.marcado = marcado;
    }

}
