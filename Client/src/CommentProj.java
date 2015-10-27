/**
 * Created by Rui on 27/10/2015.
 */
public class CommentProj extends Request{

    CommentProj(){

        //Definir o projeto a comentar
        System.out.println("Indique o ID do projeto para fazer um comentário: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }

        //Definir o subject da mensagem
        System.out.println("Indique o assunto da mensagem: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }

        //Definir o titulo da mensagem
        System.out.println("Indique o titulo da mensagem: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }

        //Definir a mensagem
        System.out.println("Escreva o seu comentário: ");
        try{
            campos_string.add(reader.readLine());
        }
        catch (Exception e) {
        }
    }
}
