import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.channels.CancelledKeyException;
import java.util.ArrayList;
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
                System.out.println("Select an option please:\n1.Login\n2.Register\n3.List current projects\n4.List older projects\n");
                int opc;

                try {
                    try{
                        opc = Integer.parseInt(reader.readLine());
                    }catch(NumberFormatException nfe){
                        continue;
                    }

                    switch (opc) {
                        case 1:
                            schedule(new Login());
                            intResponse = (IntResponse) Client.currentRequest.response;
                            if (intResponse.values[0] == 0) {
                                System.out.println("Invalid Credentials");
                            } else {
                                System.out.println("Successful Login");
                                Client.userId = intResponse.values[0];
                                Client.requestId = intResponse.values[1];
                            }
                            break;
                        case 2:
                            schedule(new Register());
                            intResponse = (IntResponse) Client.currentRequest.response;
                            Client.userId = intResponse.values[0];
                            Client.requestId = 0;
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
                                try {
                                    int aux = Integer.parseInt(reader.readLine());
                                    if (aux != 0) {
                                        for (Project project : projectListResponse.projects) {
                                            if (project.getId() == aux) {
                                                System.out.println(project.detailed());
                                            }
                                        }
                                    }
                                }catch (Exception e){
                                    System.out.println("Error in input");
                                }


                            }
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Select an option:\n1.Check Balance\n2.Check Rewards\n3.List current projects\n4.List older projects\n5.Manage your projects\n6.Pledge Project\n7.Comment Project\n8.Create Project");
                int opc;
                try {
                    try{
                        opc = Integer.parseInt(reader.readLine());
                    }catch(NumberFormatException nfe){
                        continue;
                    }

                    switch (opc) {
                        case 1:
                            schedule(new CheckBalance());
                            DoubleResponse doubleResponse;
                            synchronized (Client.currentRequest.response) {
                                doubleResponse = (DoubleResponse) Client.currentRequest.response;
                            }
                            System.out.println("Your balance is: " + doubleResponse.value + " euros\n");
                            break;
                        case 2:
                            schedule(new CheckRewards());
                            RewardsResponse rewardsResponse;
                            synchronized (Client.currentRequest.response){
                                rewardsResponse = (RewardsResponse) Client.currentRequest.response;
                            }
                            if(rewardsResponse.rewards.size()== 0){
                                System.out.println("You don't have rewards.");
                            }
                            else{
                                System.out.println("Rewards:\n");
                                for(Reward reward:rewardsResponse.rewards){
                                    System.out.println(reward.toUser());
                                }
                                System.out.println("Do you wish to donate a reward to a friend (name of the friend if yes, 0 if no)");
                                String aux = reader.readLine();
                                if(!aux.equals("0"))
                                    schedule(new GiveReward());

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
                        case 5:
                            schedule(new ListMyProj());
                            synchronized (Client.currentRequest.response) {
                                projectListResponse = (ProjectListResponse) Client.currentRequest.response;
                            }
                            if(projectListResponse.projects.size()> 0){
                                for(Project p:projectListResponse.projects){
                                    System.out.println(p.detailed());
                                }
                                System.out.println("Which project do you want to manage?");
                                int projectId = Integer.parseInt(reader.readLine());
                                System.out.println("Select an option:\n1.Cancel Project\n2.Add Admin\n3.Add Reward\n4.Remove Reward\n5.Answer Questions");
                                opc = Integer.parseInt(reader.readLine());
                                switch (opc){
                                    case 1:
                                        System.out.println("Are you sure?(y|n)");
                                        if(reader.readLine().equals("y")){
                                            schedule(new DeleteProj(projectId));
                                            BooleanResponse booleanResponse;
                                            synchronized (Client.currentRequest.response){
                                                booleanResponse = (BooleanResponse)Client.currentRequest.response;
                                            }
                                            if(booleanResponse.status){
                                                System.out.println("Project deleted successfully");
                                            }
                                            else{
                                                System.out.println("There was a problem deleting the project");
                                            }
                                        }
                                        break;
                                    case 2:
                                        schedule(new AddAdmin(projectId));
                                        BooleanResponse booleanResponse;
                                        synchronized (Client.currentRequest.response){
                                            booleanResponse = (BooleanResponse)Client.currentRequest.response;
                                        }
                                        if(booleanResponse.status){
                                            System.out.println("Admin added successfully");
                                        }
                                        else{
                                            System.out.println("There was a problem adding the admin");
                                        }
                                        break;
                                    case 3:
                                        schedule(new AddReward(projectId));
                                        synchronized (Client.currentRequest.response){
                                            booleanResponse = (BooleanResponse)Client.currentRequest.response;
                                        }
                                        if(booleanResponse.status){
                                            System.out.println("Reward added successfully");
                                        }
                                        else{
                                            System.out.println("There was a problem adding the reward");
                                        }
                                        break;
                                    case 4:
                                        schedule(new RemoveReward(projectId));
                                        synchronized (Client.currentRequest.response){
                                            booleanResponse = (BooleanResponse)Client.currentRequest.response;
                                        }
                                        if(booleanResponse.status){
                                            System.out.println("Reward removed successfully");
                                        }
                                        else{
                                            System.out.println("There was a problem removing the reward");
                                        }
                                        break;
                                    case 5:
                                        schedule(new ListProjectMessages(projectId));
                                        MessageListResponse messagesListResponse = (MessageListResponse) Client.currentRequest.response;
                                        if(messagesListResponse.messages.size()==0){
                                            System.out.println("Nothing to answer to");
                                        }
                                        else {
                                            for(Message m : messagesListResponse.messages){
                                                System.out.println(m);
                                            }
                                            schedule(new CommentResponse());
                                            synchronized (Client.currentRequest.response){
                                                booleanResponse = (BooleanResponse)Client.currentRequest.response;
                                            }
                                        }
                                        break;
                                }
                            }
                            else{
                                System.out.println("No projects to manage");
                            }
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
                            schedule(new ListActualProj());
                            synchronized (Client.currentRequest.response) {
                                projectListResponse = (ProjectListResponse) Client.currentRequest.response;
                            }
                            if(projectListResponse.projects.size()> 0){
                                for(Project p:projectListResponse.projects){
                                    System.out.println(p);
                                }
                                schedule(new CommentProj(projectListResponse.projects));
                            }
                            else{
                                System.out.println("Nothing to show");
                            }

                            break;
                        case 8:
                            schedule(new CreateProj(Client.userId));
                            BooleanResponse booleanResponse;
                            synchronized (Client.currentRequest.response){
                                booleanResponse = (BooleanResponse)Client.currentRequest.response;
                            }
                            if(booleanResponse.status){
                                System.out.println("Sucessfully created.");
                            }
                            else{
                                System.out.println("Name already in use.");
                            }
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
