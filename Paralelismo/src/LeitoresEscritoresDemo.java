import java.util.concurrent.Semaphore;

public class LeitoresEscritoresDemo {
    public static volatile String escrita = "Não foi escrito ainda.";

    public static void main(String[] args) {
        LeitoresEscritores leitoresEscritores = new LeitoresEscritores();

        Thread leitor1 = new Thread(() -> {
            try {
                Thread.sleep(100);
                leitoresEscritores.iniciarLeitura();
                System.out.println("Leitor 1 está lendo. " + escrita);
                leitoresEscritores.terminarLeitura();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread escritor1 = new Thread(() -> {
            try {
                Thread.sleep(100);
                leitoresEscritores.iniciarEscrita();
                System.out.println("Escritor 1 está escrevendo.");
                escrita = "Foi escrito. por escritor 1";
                leitoresEscritores.terminarEscrita();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread escritor2 = new Thread(() -> {
            try {
                Thread.sleep(100);
                leitoresEscritores.iniciarEscrita();
                System.out.println("Escritor 2 está escrevendo.");
                escrita = "Foi escrito. por escritor 2";
                leitoresEscritores.terminarEscrita();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread leitor2 = new Thread(() -> {
            try {
                Thread.sleep(100);
                leitoresEscritores.iniciarLeitura();
                System.out.println("Leitor 2 está lendo. " + escrita);
                leitoresEscritores.terminarLeitura();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        escritor1.start();
        leitor2.start();
        leitor1.start();
        escritor2.start();
    }
}

class LeitoresEscritores {
    private Semaphore mutex = new Semaphore(1);
    private Semaphore writeBlock = new Semaphore(1);
    private int readCount = 0;

    public void iniciarLeitura() throws InterruptedException {
        mutex.acquire();
        readCount++;
        if (readCount == 1) {
            writeBlock.acquire();
        }
        mutex.release();
    }

    public void terminarLeitura() throws InterruptedException {
        mutex.acquire();
        readCount--;
        if (readCount == 0) {
            writeBlock.release();
        }
        mutex.release();
    }

    public void iniciarEscrita() throws InterruptedException {
        writeBlock.acquire();
    }

    public void terminarEscrita() {
        writeBlock.release();
    }
}
