import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rui on 27/10/2015.
 */
public class CreateProj extends Request{
    CreateProj(){
        //Definir o nome do projecto
        System.out.println("Indique o nome do projeto: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }


        //Definir a deadline do projeto
        System.out.println("Indique a Deadline do projeto: \nAno: ");
        try{
            campos_int.add(Integer.parseInt(reader.readLine()));
        }
        catch (Exception e) {
        }
        System.out.println("M�s: ");
        try{
            campos_int.add(Integer.parseInt(reader.readLine()));
        }
        catch (Exception e) {
        }
        System.out.println("Dia: ");
        try{
            campos_int.add(Integer.parseInt(reader.readLine()));
        }
        catch (Exception e) {
        }
        System.out.println("Hora: ");
        try{
            campos_int.add(Integer.parseInt(reader.readLine()));
        }
        catch (Exception e) {
        }
        System.out.println("Minuto: ");
        try{
            campos_int.add(Integer.parseInt(reader.readLine()));
        }
        catch (Exception e) {
        }


        //Definir o objetivo monet�rio
        System.out.println("Defina o objetivo monet�rio a alcan�ar: ");
        try{
            campos_double.add(Double.parseDouble(reader.readLine()));
        }
        catch (Exception e) {
        }


        //Definir a descri��o do projeto
        System.out.println("Indique a descri��o do projeto: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }


    }
}