package rmi;/**
 * Created by mariobalca on 24-10-2015.
 */

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TumblrApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;

public class RMIServer{

    public static String oauth_key = "5WvSypvkMXbY4bQtDW5v8zjJYHsR0qzJoacCmTwlh0LrNP9IBI";
    public static String secret_key = "pkUvCjVi9U0bOvaEFXIPj9hTsIio8czSbMz32DSr26MlTPerHw";
    public static Token request_token;
    public static OAuthService service;
    String hostname;
    int port;
    int delta;
    public RMIServer(){
        try {
            try {
                BufferedReader fR = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("configRMI.txt")));
                hostname = fR.readLine();
                port = Integer.parseInt(fR.readLine());
                delta = Integer.parseInt(fR.readLine());
                fR.close();
            }


            catch (Exception e){
                System.out.println("Erro ao abrir ficheiro configRMI");
                System.exit(1);
            }
            Connection connection = DriverManager.getConnection("jdbc:sqlite:RMIServer/src/db/db.db");
            //Connection connection = DriverManager.getConnection("jdbc:sqlite:db/db.db");
            System.setProperty("java.rmi.server.hostname",hostname);
            RMIImpl rmiServer = new RMIImpl(connection);
            Registry r = LocateRegistry.createRegistry(port);
            r.rebind("rmi", rmiServer);
            Timer t = new Timer();
            MyTask mTask = new MyTask(rmiServer);
            // This task is scheduled to run every 10 seconds

            t.scheduleAtFixedRate(mTask, 0, delta);
            RMIServer.service = new ServiceBuilder()
                    .provider(TumblrApi.class)
                    .apiKey(RMIServer.oauth_key)
                    .apiSecret(RMIServer.secret_key)
                    .callback("http://localhost:8080/tumblr/callback") //   forbidden. We need an url and the better is on the tumblr website !
                    .build();
            System.out.println("rmi.RMI Server ready.");
        } catch (RemoteException re) {
            re.printStackTrace();
            System.out.println("Port already in use");
            System.exit(1);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new RMIServer();
    }

}
