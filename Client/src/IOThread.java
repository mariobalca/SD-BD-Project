import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.channels.CancelledKeyException;
import java.util.Scanner;

/**
 * Created by pedro on 27-10-2015.
 */
public class IOThread extends Thread {

    public BufferedReader reader;

    public IOThread() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {
        IntResponse intResponse;
        while (true) {
            if (Client.userId <= 0) {
                System.out.println("O que deseja?\n1.Login\n2.Register\n3.List current projects\n4.List older projects\n");
                int opc;

                try {
                    opc = Integer.parseInt(reader.readLine());
                    switch (opc) {
                        case 1:
                            schedule(new Login());
                            intResponse = (IntResponse) Client.currentRequest.response;
                            if (intResponse.values[0] == 0) {
                                System.out.println("Invalid Credentials");
                            } else {
                                System.out.println("Successful Login");
                                Client.userId = intResponse.values[0];
                            }
                            break;
                        case 2:
                            schedule(new Register());
                            intResponse = (IntResponse) Client.currentRequest.response;
                            Client.userId = intResponse.values[0];
                            if (Client.userId == 0) {
                                System.out.println("Invalid Register");
                            } else {
                                System.out.println("Successful Register");
                            }
                            break;
                        case 3:
                            schedule(new ListActualProj());
                            ProjectListResponse projectListResponse = (ProjectListResponse) Client.currentRequest.response;
                            if (projectListResponse.projects.size() == 0) {
                                System.out.println("Nothing to show");
                            } else {
                                for (Project project : projectListResponse.projects) {
                                    System.out.println(project);
                                }
                                System.out.println("Which project do you want to see? (0 if none)");
                                int aux = Integer.parseInt(reader.readLine());
                                if (aux != 0) {
                                    for (Project project : projectListResponse.projects) {
                                        if (project.getId() == aux) {
                                            System.out.println(project.detailed());
                                        }
                                    }
                                }
                            }
                            break;
                        case 4:
                            schedule(new ListOlderProj());
                            projectListResponse = (ProjectListResponse) Client.currentRequest.response;
                            if (projectListResponse.projects.size() == 0) {
                                System.out.println("Nothing to show");
                            } else {
                                for (Project project : projectListResponse.projects) {
                                    System.out.println(project);
                                }
                                System.out.println("Which project do you want to see? (0 if none)");
                                int aux = Integer.parseInt(reader.readLine());
                                if (aux != 0) {
                                    for (Project project : projectListResponse.projects) {
                                        if (project.getId() == aux) {
                                            System.out.println(project.detailed());
                                        }
                                    }
                                }

                            }
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("O que deseja?\n1.Check Balance\n2.Check Rewards\n3.List current projects\n4.List older projects\n5.Consult project\n6.Pledge Project\n7.Comment Project\n8.Create Project");
                int opc;
                try {
                    opc = Integer.parseInt(reader.readLine());
                    switch (opc) {
                        case 1:
                            schedule(new CheckBalance());
                            DoubleResponse doubleResponse;
                            synchronized (Client.currentRequest.response) {
                                doubleResponse = (DoubleResponse) Client.currentRequest.response;
                            }
                            System.out.println("Tem o saldo de " + doubleResponse.value + " euros\n");
                            break;
                        case 2:
                            schedule(new CheckRewards());

                            break;
                        case 3:
                            schedule(new ListActualProj());
                            ProjectListResponse projectListResponse = (ProjectListResponse) Client.currentRequest.response;
                            if (projectListResponse.projects.size() == 0) {
                                System.out.println("Nothing to show");
                            } else {
                                for (Project project : projectListResponse.projects) {
                                    System.out.println(project);
                                }
                                System.out.println("Which project do you want to see? (0 if none)");
                                int aux = Integer.parseInt(reader.readLine());
                                if (aux != 0) {
                                    for (Project project : projectListResponse.projects) {
                                        if (project.getId() == aux) {
                                            System.out.println(project.detailed());
                                        }
                                    }
                                }
                            }
                            break;
                        case 4:
                            schedule(new ListOlderProj());
                            projectListResponse = (ProjectListResponse) Client.currentRequest.response;
                            if (projectListResponse.projects.size() == 0) {
                                System.out.println("Nothing to show");
                            } else {
                                for (Project project : projectListResponse.projects) {
                                    System.out.println(project);
                                }
                                System.out.println("Which project do you want to see? (0 if none)");
                                int aux = Integer.parseInt(reader.readLine());
                                if (aux != 0) {
                                    for (Project project : projectListResponse.projects) {
                                        if (project.getId() == aux) {
                                            System.out.println(project.detailed());
                                        }
                                    }
                                }
                            }

                            break;
                        case 5:
                            schedule(new ConsultProj());
                            break;
                        case 6:
                            schedule(new ListActualProj());
                            projectListResponse = (ProjectListResponse) Client.currentRequest.response;
                            if(projectListResponse.projects.size()==0){
                                System.out.println("Nothing to pledge to");
                            }
                            else {
                                for(Project p:projectListResponse.projects){
                                    System.out.println(p.detailed());
                                }
                                schedule(new PledgeProj());
                            }
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

    public void schedule(Request request) {
        synchronized (Client.currentRequest) {
            Client.currentRequest.request = request;
        }
        synchronized (Client.requestToSend) {
            Client.requestToSend = true;
        }
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
