/**
 * Created by Rui on 26/10/2015.
 */
public class AddReward extends Request{

    AddReward(){
        //Definir o projeto a que adicionar a reward
        System.out.println("Indique o ID do projeto: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }

        //Definir o valor mínimo da reward
        System.out.println("Indique o valor mínimo da reward: ");
        try{
            campos_double.add(Double.parseDouble(reader.readLine()));
        }
        catch (Exception e) {
        }

        //Definir o nome da reward
        System.out.println("Indique o nome da reward: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }

        //Definir a descrição da reward
        System.out.println("Indique a descrição para a reward: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }
        
    }
}
