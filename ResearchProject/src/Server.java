import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        System.out.println("server has started........");
        try {
            Registry r = LocateRegistry.createRegistry(1234);
            ArrayListClass alc = new ArrayListClass();
            r.bind("listclass", alc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}