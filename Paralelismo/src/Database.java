import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Database {
    private final List<Integer> lista;
    private final Semaphore mutex;
    private final Semaphore semaforoInsert;
    private final Semaphore semaforoSearch;

    public Database() {
        lista = new ArrayList<>();
        mutex = new Semaphore(1);
        semaforoInsert = new Semaphore(1);
        semaforoSearch = new Semaphore(1);
        
    }

    public static void main(String[] args) throws InterruptedException {
        Database lista = new Database();

        Thread inserir1 = new Thread(() -> {
            try {
                lista.inserir(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread inserir2 = new Thread(() -> {
            try {
                lista.inserir(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread procura1 = new Thread(() -> {
            try {
                lista.procurar(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread procura2 = new Thread(() -> {
            try {
                lista.procurar(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        
        Thread deleta1 = new Thread(() -> {
            try {
                lista.deletar(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        inserir1.start();
        inserir2.start();
        procura1.start();
        deleta1.start();
        procura2.start();

    }

    public void inserir(int valor) throws InterruptedException {
        semaforoInsert.acquire();
        mutex.acquire();
        try {
            lista.add(valor);
            System.out.println("Inserted: " + valor);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            mutex.release();
            semaforoInsert.release();
        }
    }

    public boolean procurar(int valor) throws InterruptedException {
        semaforoSearch.acquire();
        try {
            for (int item : lista) {
                if (item == valor) {
                    System.out.println("Found: " + valor);
                    return true;
                }
            }
            System.out.println("Not Found: " + valor);
            return false;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        } finally {
            semaforoSearch.release();
        }
    }

    public void deletar(int valor) throws InterruptedException {
        semaforoSearch.acquire();
        semaforoInsert.acquire();
        try {
            if (lista.remove(Integer.valueOf(valor))) {
                System.out.println("Deleted: " + valor);
            } else {
                System.out.println("Not Found: " + valor);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            semaforoSearch.release();
            semaforoInsert.release();
        }
    }
}
