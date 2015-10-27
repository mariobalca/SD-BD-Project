/**
 * Created by Rui on 26/10/2015.
 */
public class AddReward extends Request{

    AddReward(){
        int proj;
        double valor;
        String nome = "";
        String descricao = "";

        //Definir o projeto a que adicionar a reward
        System.out.println("Indique o ID do projeto: ");
        try{
            proj = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }

        //Definir o valor mínimo da reward
        System.out.println("Indique o valor mínimo da reward: ");
        try{
            valor = Double.parseDouble(reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita do double.");
        }

        //Definir o nome da reward
        System.out.println("Indique o nome da reward: ");
        try{
           nome = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }

        //Definir a descrição da reward
        System.out.println("Indique a descrição para a reward: ");
        try{
           descricao = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
        
    }
}
