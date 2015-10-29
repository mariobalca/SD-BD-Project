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
                System.out.println("O que deseja?\n1.Login\n2.Register\n3.List current projects\n4.List older projects\n");
                int opc;
                try {
                    opc = Integer.parseInt(reader.readLine());
                    switch (opc) {
                        case 1:
                            schedule(new Login());
                            IntResponse intResponse = (IntResponse)Client.currentRequest.response;
                            if(intResponse.values[0] == 0){
                                System.out.println("Invalid Credentials");
                            }
                            else{
                                System.out.println("Successful Login");
                                Client.userId = intResponse.values[0];
                            }
                            break;
                        case 2:
                            schedule(new Register());
                            break;
                        case 3:
                            schedule(new ListOlderProj());
                            ProjectListResponse projectListResponse = (ProjectListResponse)Client.currentRequest.response;
                            if(projectListResponse.projects.size() == 0){
                                System.out.println("Nothing to show");
                            }
                            else{
                                for(Project project:projectListResponse.projects){
                                    System.out.println(project);
                                }
                                schedule(new ConsultProj());
                            }
                            break;
                        case 4:
                            schedule(new ListOlderProj());

                            System.out.println("Qual Queres?");
                            schedule(new ConsultProj());
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
                    synchronized (Client.currentRequest) {
                        switch (opc) {
                            case 1:
                                Client.currentRequest.request = new CheckBalance();
                                break;
                            case 2:
                                Client.currentRequest.request = new CheckRewards();
                                break;
                            case 3:
                                Client.currentRequest.request = new ListActualProj();
                                break;
                            case 4:
                                Client.currentRequest.request = new ListOlderProj();
                                break;
                            case 5:
                                Client.currentRequest.request = new ConsultProj();
                                break;
                            case 6:
                                Client.currentRequest.request = new PledgeProj();
                                break;
                            case 7:
                                Client.currentRequest.request = new CommentProj();
                                break;
                            case 8:
                                Client.currentRequest.request = new CreateProj();
                                break;
                        }
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

    }
}
