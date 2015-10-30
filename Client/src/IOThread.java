import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.channels.CancelledKeyException;
import java.util.Scanner;

/**
 * Created by pedro on 27-10-2015.
 */
public class IOThread extends Thread{

    private BufferedReader reader;

    public IOThread(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run(){
        IntResponse intResponse;
        while(true){
            /*aux = reader.readLine();
            synchronized (Client.currentString){
                if(Client.currentString.equals("vazio")){
                    Client.currentString = aux;
                }
            }*/

            if(Client.userId<=0){
                System.out.println("O que deseja?\n1.Login\n2.Register\n3.List current projects\n4.List older projects\n");
                int opc;

                try {
                    opc = Integer.parseInt(reader.readLine());
                    switch (opc) {
                        case 1:
                            schedule(new Login());
                            break;
                        case 2:
                            schedule(new Register());
                            break;
                        case 3:
                            schedule(new ListActualProj());
                            break;
                        case 4:
                            schedule(new ListOlderProj());
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("O que deseja?\n1.Check Balance\n2.Check Rewards\n3.List current projects\n4.List older projects\n5.Consult project\n6.Pledge Project\n7.Comment Project\n8.Create Project");
                int opc;
                try {
                    opc = Integer.parseInt(reader.readLine());
                    switch (opc) {
                        case 1:
                            schedule(new CheckBalance());
                            break;
                        case 2:
                            schedule(new CheckRewards());
                            break;
                        case 3:
                            schedule(new ListActualProj());
                            break;
                        case 4:
                            schedule(new ListOlderProj());
                            break;
                        case 5:
                            schedule(new ConsultProj());
                            break;
                        case 6:
                            schedule(new PledgeProj());
                            break;
                        case 7:
                            schedule(new CommentProj());
                            break;
                        case 8:
                            schedule(new CreateProj(Client.userId));
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void schedule(Request request){
        synchronized (Client.currentRequest){
            Client.currentRequest.request = request;
        }
        synchronized (Client.requestToSend){
            Client.requestToSend = true;
        }
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        request.awnser(this);

    }
}
