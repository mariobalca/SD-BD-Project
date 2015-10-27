import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rui on 27/10/2015.
 */
public class CreateProj extends Request{
    CreateProj(){
        String name = "", description;
        int year, mon, day, hour, min;
        double goal;


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
        System.out.println("Mês: ");
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
        System.out.println("Defina o objetivo monetário a alcançar: ");
        try{
           goal = (Double.parseDouble(reader.readLine()));
        }
        catch (Exception e) {
        }


        //Definir a descrição do projeto
        System.out.println("Indique a descrição do projeto: ");
        try{
            description = (reader.readLine());
        }
        catch (Exception e) {
        }


    }
}