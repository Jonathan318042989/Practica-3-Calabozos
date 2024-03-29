package kass.concurrente.modelos;

import kass.concurrente.constantes.Contante;

/**
 * Clase que fungira como habitacion
 * Se sabe que existe un interruptor que nos dice
 * si el foco esta prendido o no
 * Se desconoce el estado inicial del foco (Usar un random, para que sea
 * pseudoaleatorio el estado inicial)
 * 
 * @author <Equipo>
 * @version 1.0
 */
public class Habitacion {
    private Boolean prendido;
    private Boolean todosPasaron;

    /**
     * Metodo Constructor
     * Aqui se define el como estara el foco inicialmente
     */
    public Habitacion() {
        setPrendido(Math.random() < 0.5);
        setTodosPasaron(false);
    }

    /**
     * Metodo que permite al prisionero entrar a la habitacion
     * Recordemos que solo uno pasa a la vez, esta es la SECCION CRITICA
     * En este caso se controla desde fuera
     * Es similar al algoritmo que progonan y similar al de su tarea
     * El prisionero espera una cantidad finita de tiempo
     * 
     * @param prisionero El prisionero que viene entrando
     * @return false si ya pasaron todos, true en otro caso
     * @throws InterruptedException Si falla algun hilo
     */
    public Boolean entraHabitacion(Prisionero prisionero) throws InterruptedException {
        if (Boolean.FALSE.equals(prisionero.getEsVocero())) {
            if (Boolean.TRUE.equals(prisionero.getMarcado())) {
                return true;
            } else if (Boolean.FALSE.equals(this.getPrendido())) {
                this.setPrendido(true);
                prisionero.setMarcado(true);
                return true;
            }
        } else if (Boolean.TRUE.equals(prisionero.getEsVocero()) && prisionero instanceof Vocero) {
            Vocero vocero = (Vocero) prisionero;
            if (Boolean.TRUE.equals(this.getPrendido())) {
                this.setPrendido(false);
                vocero.incrementaContador();
            }
            vocero.setMarcado(true);
            this.setTodosPasaron(vocero.getContador() >= Contante.PRISIONEROS - 1);
            return vocero.getContador() >= Contante.PRISIONEROS - 1;
        }
        return true;
    }

    /**
     * Bool que indica si está prendido o apagado el interruptor
     * 
     * @return true si está encendido, false si no
     */
    public Boolean getPrendido() {
        return prendido;
    }

    /**
     * Establece cómo está el interruptor
     * 
     * @param prendido true si está encendido, false si no
     */
    public void setPrendido(Boolean prendido) {
        this.prendido = prendido;
    }

    public Boolean getTodosPasaron() {
        return todosPasaron;
    }

    public void setTodosPasaron(Boolean todosPasaron) {
        this.todosPasaron = todosPasaron;
    }

}