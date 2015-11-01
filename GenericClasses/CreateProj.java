import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
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
        this.rewards = new ArrayList<>();
        this.paths = new ArrayList<>();
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
            //Definir a deadline do projeto
            System.out.println("Write the deadline of the projet: \nYEAR: ");

            try {
                year = (Integer.parseInt(reader.readLine()));

            } catch (Exception e) {
                continue;
            }
            System.out.println("MONTH: ");
            try {
                mon = (Integer.parseInt(reader.readLine()));
            } catch (Exception e) {
                continue;
            }
            System.out.println("DAY: ");
            try {
                day = (Integer.parseInt(reader.readLine()));
            } catch (Exception e) {
                continue;
            }
            System.out.println("HOUR: ");
            try {
                hour = (Integer.parseInt(reader.readLine()));
            } catch (Exception e) {
                continue;
            }
            System.out.println("MINUTE: ");
            try {
                min = (Integer.parseInt(reader.readLine()));
            } catch (Exception e) {
                continue;
            }
            check =1;
        }

        //Definir o objetivo monetário
        System.out.println("Write the goal of the project: ");
        try{
           goal = (Double.parseDouble(reader.readLine()));
        }
        catch (Exception e) {
        }


        //Definir a descrição do projeto
        System.out.println("Write the description of the projet: ");
        try{
            description = (reader.readLine());
        }
        catch (Exception e) {
        }

        System.out.println("Add reward (0 if no)");
        try {
            while(!reader.readLine().equals("0")){
                System.out.println("Value of the Reward:");
                int valor = Integer.parseInt(reader.readLine());
                System.out.println("Name of the Reward :");
                String nome = reader.readLine();
                System.out.println("Description: ");
                String d = reader.readLine();
                rewards.add(new Reward(valor,nome,d));
                System.out.println("Add reward (0 if no)");
            }
            System.out.println("Add path (0 if no)");
            while(!reader.readLine().equals("0")){
                System.out.println("Name of the path :");
                String nome = reader.readLine();
                System.out.println("Description of the path: ");
                String d = reader.readLine();
                paths.add(new Path(nome,d,0));
                System.out.println("Add path (0 if no)");
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
                String dataAux = year + "-" + mon + "-" + day + " " + hour + ":" + min;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = dateFormat.parse(dataAux);

                Project project = new Project(name,date,goal,description,true);
                project.setRewards(rewards);
                project.setPaths(paths);
                return new BooleanResponse("CreateProj",rmiServer.createProject(project,requestId,userId));
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                verifica = false;
            }
        }

        return new BooleanResponse("CreatProj", false);
    }
}