import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Enayet Hussain on 19/11/2018.
 */
public class VanityWalletGenerator {

    private ExecutorService es;
    private int threads;

    public static void main(String[] args) throws ExecutionException, InterruptedException, WrongNumberOfArguments {
        VanityWalletGenerator vanity = new VanityWalletGenerator();
        switch (args.length) {
            case 0:
                vanity.setNumberOfThreads(1);
                System.out.printf(vanity.getFirstResult("").toString());
                break;
            case 1:
                vanity.setNumberOfThreads(2);
                System.out.printf(vanity.getResultWith(args[0]).toString());
                break;
            case 2:
                vanity.setNumberOfThreads(Integer.parseInt(args[1]));
                System.out.printf(vanity.getResultWith(args[0]).toString());
                break;
            default:
                throw new WrongNumberOfArguments(
                    "\nUsage:\n\t" +
                            "java VanityWalletGenerator\n\t\t" +
                                "or\n\t" +
                            "java VanityWalletGenerator <word>\n\t\t" +
                                "or\n\t" +
                            "java VanityWalletGenerator <word> <threads>"
                );
        }
       vanity.es.shutdown();
    }

    public VanityWalletGenerator() {}

    public void setNumberOfThreads(int n) { this.threads = n; this.es = Executors.newFixedThreadPool(n); }

    public KeyPair getFirstResult(String s) throws ExecutionException, InterruptedException {
        Future<KeyPair> future = es.submit(new Generator(s));
        return future.get();
    }

    public KeyPair getResultWith(String s) throws ExecutionException, InterruptedException {
        List<Callable<KeyPair>> callables = new ArrayList<>();

        for (int x=0;x<threads;x++)
            callables.add(new Generator(s));

        return es.invokeAny(callables);
    }

}

class WrongNumberOfArguments extends Exception {
    public WrongNumberOfArguments(String message) {
        super(message);
    }
}