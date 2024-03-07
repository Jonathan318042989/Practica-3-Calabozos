package kass.concurrente;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import kass.concurrente.constantes.Contante;
import kass.concurrente.modelos.*;

import static kass.concurrente.constantes.Contante.LOGS;

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

    /*
     * INSTRUCCIONES:
     * 1.- Ya genere el lock, es un reentrantLock, investiguen que hace
     * 2.- Tenenemos que tener un lugar el donde se albergaran los prisioneros
     * 3.- Tenemos que tener un lugar donde se albergan los Hilos
     * 4.- Se nececita un objeto de tipo Habitacion para que sea visitada
     * 5.- Aqui controlaremos el acceso a la habitacion, aunque por defecto tenia
     * exclusion mutua
     * aqui hay que especificar el como se controlara
     * 6.- Hay que estar ITERANDO constantemente para que todos los prisiones puedan
     * ir ingresando
     */
    @Override
    public void run() {
        int id = Integer.parseInt(Thread.currentThread().getName());
        while (Boolean.FALSE.equals(habitacion.getTodosPasaron())) {
            boolean disponible = lock.tryLock();
            if (disponible) {
                String prisionero = "Prisionero " + id + " entrando";
                LOG.info(prisionero);
                try {
                    habitacion.entraHabitacion(prisioneros[id]);
                    Thread.sleep(Contante.UN_SEGUNDO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
        if (id == 0 && Boolean.TRUE.equals(prisioneros[id].getEsVocero())) {
            LOG.info("Ya pasaron todos los prisioneros");
            Vocero vocero = (Vocero) prisioneros[id];
            String prisionerosPasados = String.valueOf(vocero.getContador());
            LOG.info(prisionerosPasados);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Main m = new Main();
        List<Thread> hilos = new ArrayList<>(); // Lista de hilos

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