import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
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
        while(true){
            /*aux = reader.readLine();
            synchronized (Client.currentString){
                if(Client.currentString.equals("vazio")){
                    Client.currentString = aux;
                }
            }*/

            if(Client.userId<=0){
                System.out.println("O que deseja?\n1.Login\n2.Register\n3.List current projects\n4.List older projects\n5.Consult project\n");
                int opc;
                try {
                    opc = Integer.parseInt(reader.readLine());
                    synchronized (Client.currentRequest) {
                        switch (opc) {
                            case 1:
                                Client.currentRequest = new Login();
                                break;
                            case 2:
                                Client.currentRequest = new Register();
                                break;
                            case 3:
                                Client.currentRequest = new ListActualProj();
                                synchronized (this){
                                    wait();
                                }
                                System.out.println("Qual Queres?");
                                Client.currentRequest = new ConsultProj();
                                break;
                            case 4:
                                Client.currentRequest = new ListOlderProj();
                                break;
                            case 5:
                                Client.currentRequest = new ConsultProj();
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("O que deseja?\n1.Check Balance\n2.Check Rewards\n3.List current projects\n4.List older projects\n5.Consult project\n6.Pledge Project\n7.Comment Project\n8.Create Project");
                int opc;
                try {
                    opc = Integer.parseInt(reader.readLine());
                    synchronized (Client.currentRequest) {
                        switch (opc) {
                            case 1:
                                Client.currentRequest = new CheckBalance();
                                break;
                            case 2:
                                Client.currentRequest = new CheckRewards();
                                break;
                            case 3:
                                Client.currentRequest = new ListActualProj();
                                break;
                            case 4:
                                Client.currentRequest = new ListOlderProj();
                                break;
                            case 5:
                                Client.currentRequest = new ConsultProj();
                                break;
                            case 6:
                                Client.currentRequest = new PledgeProj();
                                break;
                            case 7:
                                Client.currentRequest = new CommentProj();
                                break;
                            case 8:
                                Client.currentRequest = new CreateProj();
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
