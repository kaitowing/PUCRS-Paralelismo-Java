import java.util.concurrent.Semaphore;

public class Barbearia {
    private Semaphore mutex = new Semaphore(1);
    private Semaphore barbeiroDisponivel = new Semaphore(0);
    private Semaphore clienteEsperando = new Semaphore(0);
    private int clientesEsperando = 0;
    private static long timeStart = System.currentTimeMillis();

    public void clienteEntra() throws InterruptedException {
        mutex.acquire();
        if (clientesEsperando < 5) {
            clientesEsperando++;
            System.out.println("Cliente entrou na barbearia.");
            mutex.release();
            clienteEsperando.release();
            barbeiroDisponivel.release();
        } else {
            mutex.release();
            System.out.println("Barbearia está cheia, cliente foi embora.");
        }
    }

    public void barbeiroCortaCabelo() throws InterruptedException {
        clienteEsperando.acquire();
        mutex.acquire();
        clientesEsperando--;
        System.out.println("Barbeiro está cortando o cabelo de um cliente.");
        mutex.release();
    }

    public void clienteSai() throws InterruptedException {
        System.out.println("Cliente está saindo da barbearia.");
    }

    public static void main(String[] args) {
        Barbearia barbearia = new Barbearia();

        Thread barbeiro = new Thread(() -> {
            try {
                while (true) {
                    barbearia.barbeiroCortaCabelo();
                    barbearia.clienteSai();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread[] clientes = new Thread[10];
        for (int i = 0; i < 10; i++) {
            clientes[i] = new Thread(() -> {
                try {
                    barbearia.clienteEntra();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        barbeiro.start();
        for (Thread cliente : clientes) {
            cliente.start();
        }

        while(true){
          if (System.currentTimeMillis() - timeStart > 2000) {
            System.out.println("Barbearia está vazia, o barbeiro vai dormir");
            System.exit(0);
        }
        }
    }
}
