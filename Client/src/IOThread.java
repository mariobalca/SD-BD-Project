import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by pedro on 27-10-2015.
 */
public class IOThread extends Thread{

    private BufferedReader reader;
    private String currentString;

    public IOThread(String currentString){
        this.currentString = currentString;
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.start();
    }

    public void run(){
        String aux = null;
        while(true){
            try {
                aux = reader.readLine();
                synchronized (currentString){
                    if(currentString.equals("vazio")){
                        currentString = aux;
                        System.out.println(currentString);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
