import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by Enayet Hussain on 18/11/2018.
 */
public class ResultRunnable implements Runnable {

    private Future<KeyPair> future;
    private ExecutorService es;

    public ResultRunnable(Future<KeyPair> future, ExecutorService es) {
        this.future = future;
        this.es = es;
    }

    public void run() {
        try {
            System.out.printf(future.get().toString());
        } catch (InterruptedException e) {
            //ignore
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
