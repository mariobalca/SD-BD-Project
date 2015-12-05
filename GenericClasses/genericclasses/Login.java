package genericclasses;

import rmi.RMI;
import server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

/**
 * Created by Rui on 26/10/2015.
 */
public class Login extends Request {
    public String username = "";
    public String password = "";


    public Login(){
        super("Login");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //add username;
        System.out.println("Insert username: ");
        try{
            username = reader.readLine();
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
        //add password;
        System.out.println("Insert password: ");
        try{
            password = reader.readLine();
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
    }



    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                rmiServer  = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
                int[] login = rmiServer.loginUser(username,password);
                verifica = true;
                return new IntResponse("Login",login);
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {
            } catch (NotBoundException e) {

            }
        }

        return new IntResponse("Login", new int[]{0,0});
    }

}
