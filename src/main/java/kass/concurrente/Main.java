package kass.concurrente;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import kass.concurrente.constantes.Contante;
import kass.concurrente.modelos.*;

/**
 * Clase principal, se ejecuta todo la simulacion
 * Como en el cuento.
 * 
 * @author <Equipo>
 * @version 1.0
 */
public class Main implements Runnable {

    Lock lock;
    Prisionero[] prisioneros;
    Habitacion habitacion;
    private static final Logger LOG = Logger.getLogger("paquete.NombreClase");

    public Main() {
        lock = new ReentrantLock();
        habitacion = new Habitacion();
        prisioneros = new Prisionero[Contante.PRISIONEROS];
        prisioneros[0] = new Vocero(0, true, false);
        for (int i = 1; i < Contante.PRISIONEROS; i++) {
            prisioneros[i] = new Prisionero(i, false, false);
        }
    }

    /**
     * Función donde se ejecutará
     */
    @Override
    public void run() {
        int id = Integer.parseInt(Thread.currentThread().getName());
        while (Boolean.FALSE.equals(habitacion.getTodosPasaron())) {
            boolean disponible = lock.tryLock();
            if (disponible) {
                String prisionero = "\u001B[34m Prisionero " + id + " entrando";
                LOG.info(prisionero);
                try {
                    habitacion.entraHabitacion(prisioneros[id]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                    try {
                        Thread.sleep(Contante.UN_SEGUNDO);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        if (id == 0 && Boolean.TRUE.equals(prisioneros[id].getEsVocero())) {
            LOG.info("\u001B[31m Ya pasaron todos los prisioneros");
            Vocero vocero = (Vocero) prisioneros[id];
            String numeroPrisioneros = String.valueOf(vocero.getContador());
            String prisionerosPasados = "\u001B[31m Pasaron " + numeroPrisioneros + " prisioneros";
            LOG.info(prisionerosPasados);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Main m = new Main();
        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < Contante.PRISIONEROS; ++i) {
            Thread hilo = new Thread(m, "" + i);
            hilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }
    }
}