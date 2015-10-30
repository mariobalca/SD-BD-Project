import java.io.BufferedReader;
import java.io.InputStreamReader;
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

    public CreateProj(int userId){
        super("CreateProj");
        this.userId = userId;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        //Definir o nome do projecto
        System.out.println("Indique o nome do projeto: ");
        try{
            name = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }


        //Definir a deadline do projeto
        System.out.println("Indique a Deadline do projeto: \nAno: ");
        try{
            year = (Integer.parseInt(reader.readLine()));
        }
        catch (Exception e) {
        }
        System.out.println("M�s: ");
        try{
            mon = (Integer.parseInt(reader.readLine()));
        }
        catch (Exception e) {
        }
        System.out.println("Dia: ");
        try{
            day = (Integer.parseInt(reader.readLine()));
        }
        catch (Exception e) {
        }
        System.out.println("Hora: ");
        try{
            hour = (Integer.parseInt(reader.readLine()));
        }
        catch (Exception e) {
        }
        System.out.println("Minuto: ");
        try{
            min = (Integer.parseInt(reader.readLine()));
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }


        //Definir o objetivo monetário
        System.out.println("Defina o objetivo monet�rio a alcan�ar: ");
        try{
           goal = (Double.parseDouble(reader.readLine()));
        }
        catch (Exception e) {
        }


        //Definir a descrição do projeto
        System.out.println("Indique a descricao do projeto: ");
        try{
            description = (reader.readLine());
        }
        catch (Exception e) {
        }


    }

    @Override
    public void awnser(IOThread thread) {
        BooleanResponse response = (BooleanResponse)Client.currentRequest.response;
        if(response.status){
            System.out.println("Created successfully");
        }
        else{
            System.out.println("Not possible to create");
        }
    }
}