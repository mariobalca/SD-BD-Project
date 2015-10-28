import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rui on 27/10/2015.
 */
public class CreateProj extends Request{
    private String name = "", description;
    private int year, mon, day, hour, min;
    private double goal;

    public CreateProj(){
        super("CreateProj");
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


        //Definir o objetivo monet�rio
        System.out.println("Defina o objetivo monet�rio a alcan�ar: ");
        try{
           goal = (Double.parseDouble(reader.readLine()));
        }
        catch (Exception e) {
        }


        //Definir a descri��o do projeto
        System.out.println("Indique a descri��o do projeto: ");
        try{
            description = (reader.readLine());
        }
        catch (Exception e) {
        }


    }
}