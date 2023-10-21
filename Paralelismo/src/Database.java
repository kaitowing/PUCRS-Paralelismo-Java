import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

class Database {
    private final List<Integer> data;
    private final Semaphore semaphore;

    public Database() {
        data = new ArrayList<>();
        semaphore = new Semaphore(1);
    }

    public static void main(String[] args) throws InterruptedException {
        Database database = new Database();

       database.insert(0);
       database.search(0);
       database.delete(0);
    }

    public void insert(int value) throws InterruptedException {
        semaphore.acquire();
        try {
            data.add(value);
            System.out.println("Inserted: " + value);
        }catch (Exception e){
            System.out.println("Error: " + e);
        } finally {
            semaphore.release();
        }
    }

    public boolean search(int value) throws InterruptedException {
        semaphore.acquire();
        try {
            for (int item : data) {
                if (item == value) {
                    System.out.println("Found: " + value);
                    return true;
                }
            }
            System.out.println("Not Found: " + value);
            return false;
        }catch (Exception e){
            System.out.println("Error: " + e);
            return false;
        } finally {
            semaphore.release();
        }
    }

    public void delete(int value) throws InterruptedException {
        semaphore.acquire();
        try {
            if (data.remove(Integer.valueOf(value))) {
                System.out.println("Deleted: " + value);
            } else {
                System.out.println("Not Found: " + value);
            }
        }catch (Exception e){
            System.out.println("Error: " + e);
        } finally {
            semaphore.release();
        }
    }
}
