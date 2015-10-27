/**
 * Created by Rui on 27/10/2015.
 */
public class PledgeProj extends Request {
    PledgeProj(){
        //Definir o projeto a apoiar
        System.out.println("Indique o ID do projeto: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }

        //Definir a quantia com que apoiar o projeto
        System.out.println("Indique com quanto quer apoiar o projecto: ");
        try{
            campos_double.add(Double.parseDouble(reader.readLine()));
        }
        catch (Exception e) {
        }

        //Escolher o caminho (voto) para o projeto
        System.out.println("Indique em qual caminho quer votar (id do path): ");
        try{
            campos_int.add(Integer.parseInt(reader.readLine()));
        }
        catch (Exception e) {
        }
    }
}
