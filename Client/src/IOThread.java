import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by pedro on 27-10-2015.
 */
public class IOThread extends Thread{

    private BufferedReader reader;

    public IOThread(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run(){
        while(true){
            /*aux = reader.readLine();
            synchronized (Client.currentString){
                if(Client.currentString.equals("vazio")){
                    Client.currentString = aux;
                }
            }*/
            synchronized (Client.aux) {
                System.out.println(Client.aux);
            }
            synchronized (Client.currentRequest) {
                Client.currentRequest = new Login();
            }
            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
