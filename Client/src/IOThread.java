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
        this.start();
    }

    public void run(){
        String aux;
        while(true){
            try {
                aux = reader.readLine();
                synchronized (Client.currentString){
                    if(Client.currentString.equals("vazio")){
                        Client.currentString = aux;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
