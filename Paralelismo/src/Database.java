import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

class Database {
    private final List<Integer> lista;
    private final Semaphore semaforo;

    public Database() {
        lista = new ArrayList<>();
        semaforo = new Semaphore(1);
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
                lista.deletar(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        
        
        deleta1.start();
        inserir1.start();
        inserir2.start();
        procura1.start();
        procura2.start();
        
    }

    public void inserir(int valor) throws InterruptedException {
        semaforo.acquire();
        try {
            lista.add(valor);
            System.out.println("Inserted: " + valor);
        }catch (Exception e){
            System.out.println("Error: " + e);
        } finally {
            semaforo.release();
        }
    }

    public boolean procurar(int valor) throws InterruptedException {
        semaforo.acquire();
        try {
            for (int item : lista) {
                if (item == valor) {
                    System.out.println("Found: " + valor);
                    return true;
                }
            }
            System.out.println("Not Found: " + valor);
            return false;
        }catch (Exception e){
            System.out.println("Error: " + e);
            return false;
        } finally {
            semaforo.release();
        }
    }

    public void deletar(int valor) throws InterruptedException {
        semaforo.acquire();
        try {
            if (lista.remove(Integer.valueOf(valor))) {
                System.out.println("Deleted: " + valor);
            } else {
                System.out.println("Not Found: " + valor);
            }
        }catch (Exception e){
            System.out.println("Error: " + e);
        } finally {
            semaforo.release();
        }
    }
}
