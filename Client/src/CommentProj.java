/**
 * Created by Rui on 27/10/2015.
 */
public class CommentProj extends Request{
    int proj;
    String subject = "", question = "";

    CommentProj(){




        //Definir o projeto a comentar
        System.out.println("Indique o ID do projeto para fazer uma questão: ");
        try{
           proj = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }

        //Definir o subject da mensagem
        System.out.println("Indique o assunto da questão: ");
        try{
           subject = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }


        //Definir a mensagem
        System.out.println("Escreva a sua questão: ");
        try{
           question = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
    }
}
