package genericclasses;

import client.Client;
import rmi.RMI;
import server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rui on 27/10/2015.
 */
public class CreateProj extends Request{
    public String name = "", description;
    public int year, mon, day, hour, min;
    public double goal;
    public int userId;
    public ArrayList<Path> paths;
    public ArrayList<Reward> rewards;
    public int requestId;

    public CreateProj(int userId){
        super("CreateProj");
        this.userId = userId;
        this.rewards = new ArrayList<Reward>();
        this.paths = new ArrayList<Path>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        //Definir o nome do projecto
        System.out.println("Write the name of the project: ");
        try{
            name = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
        int check =0;
        while(check == 0) {
            Date data = new Date();
            //Definir a deadline do projeto
            System.out.println("Write the deadline of the projet: \nYEAR: ");

            try {
                year = (Integer.parseInt(reader.readLine()));
                if(year < 1){
                    continue;
                }

            } catch (Exception e) {
                continue;
            }
            System.out.println("MONTH: ");
            try {
                mon = (Integer.parseInt(reader.readLine()));
                if(mon> 12 || mon <1){
                    continue;
                }else
                    mon -=1;
            } catch (Exception e) {
                continue;
            }
            System.out.println("DAY: ");
            try {
                day = (Integer.parseInt(reader.readLine()));
                if(day<1 || day >31){
                    continue;
                }
            } catch (Exception e) {
                continue;
            }
            System.out.println("HOUR (0-23): ");
            try {
                hour = (Integer.parseInt(reader.readLine()));
                if(hour >23 || hour < 0){
                    continue;
                }

            } catch (Exception e) {
                continue;
            }
            System.out.println("MINUTE: ");
            try {
                min = (Integer.parseInt(reader.readLine()));
                if(min >60 || min < 0){
                    continue;
                }
            } catch (Exception e) {
                continue;
            }
                Date dataInserida = new Date(year-1900,mon,day,hour,min);
            if(dataInserida.after(data)) {
                check = 1;
            }else
                System.out.println("Data anterior à atual!");
        }

        //Definir o objetivo monetário
        check = 0;
        while (check ==0) {
            System.out.println("Write the goal of the project: ");
            try {
                goal = (Double.parseDouble(reader.readLine()));
                if(goal<= 0){
                    continue;
                }else
                    check =1;
            } catch (Exception e) {
                System.out.println("Invalid goal");
                continue;
            }
        }

        //Definir a descrição do projeto
        System.out.println("Write the description of the projet: ");
        try{
            description = (reader.readLine());
        }
        catch (Exception e) {
        }

        System.out.println("Add reward (At least one, 0 if enough)");
        try {
            while(!reader.readLine().equals("0") || rewards.size() == 0){

                check = 0;
                while(check == 0)
                {
                    try {
                        System.out.println("Value of the Reward:");
                        int valor = Integer.parseInt(reader.readLine());
                        if(valor>0){
                            System.out.println("Name of the Reward :");
                            String nome = reader.readLine();
                            System.out.println("Description: ");
                            String d = reader.readLine();
                            rewards.add(new Reward(valor,nome,d));
                            System.out.println("Add reward (0 if no)");
                            check =1;
                        }

                    } catch (Exception e) {
                        continue;
                    }
                }


            }
            System.out.println("Add path (At least one, 0 if enough)");
            while(!reader.readLine().equals("0") || paths.size()==0){
                System.out.println("Name of the path :");
                String nome = reader.readLine();
                System.out.println("Description of the path: ");
                String d = reader.readLine();
                paths.add(new Path(nome,d,0));
                System.out.println("Add path (At least one, 0 if enough)");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        requestId = ++Client.requestId;
    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                String dataAux = year + "-" + (mon + 1) + "-" + day + " " + hour + ":" + min;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = dateFormat.parse(dataAux);

                Project project = new Project(name,date,goal,description,true);
                project.setRewards(rewards);
                project.setPaths(paths);
                rmiServer = (RMI) LocateRegistry.getRegistry(Server.RMI_ADDRESS, Server.rmiPort).lookup("rmi");
                return new BooleanResponse("CreateProj",rmiServer.createProject(project,requestId,userId));
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {
            } catch (ParseException e) {
                verifica = false;
            } catch (NotBoundException e) {

            }
        }

        return new BooleanResponse("CreatProj", false);
    }
}
