import java.util.concurrent.Semaphore;

public class Filosofos {
    private static final int NUM_FILOSOFOS = 5;
    private static Semaphore[] garfos = new Semaphore[NUM_FILOSOFOS];
    private static Semaphore semaforo = new Semaphore(NUM_FILOSOFOS - 1);

    public static void main(String[] args) {
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            garfos[i] = new Semaphore(1);
        }

        Thread[] filosofos = new Thread[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            final int filosofoID = i;
            filosofos[i] = new Thread(() -> {
                while (true) {
                    pensar(filosofoID);
                    pegarGarfos(filosofoID);
                    comer(filosofoID);
                    largarGarfos(filosofoID);
                }
            });
            filosofos[i].start();
        }
    }

    private static void pensar(int filosofoID) {
        System.out.println("Filósofo " + filosofoID + " está pensando.");
    }

    private static void pegarGarfos(int filosofoID) {
        try {
            semaforo.acquire();
            int garfoEsquerda = filosofoID;
            int garfoDireita = (filosofoID + 1) % NUM_FILOSOFOS;
            garfos[garfoEsquerda].acquire();
            garfos[garfoDireita].acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Filósofo " + filosofoID + " pegou os garfos " + filosofoID + " e " + (filosofoID + 1) % NUM_FILOSOFOS + ".");
    }

    private static void comer(int filosofoID) {
        System.out.println("Filósofo " + filosofoID + " está comendo.");
    }

    private static void largarGarfos(int filosofoID) {
        int garfoEsquerda = filosofoID;
        int garfoDireita = (filosofoID + 1) % NUM_FILOSOFOS;
        garfos[garfoEsquerda].release();
        garfos[garfoDireita].release();
        semaforo.release();
        System.out.println("Filósofo " + filosofoID + " largou os garfos " + garfoEsquerda + " e " + garfoDireita + ".");
    }
}
