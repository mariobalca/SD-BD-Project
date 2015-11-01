import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rui on 27/10/2015.
 */
public class CommentProj extends Request{
    public int proj;
    public String subject = "", question = "";
    public int requestId;

    public CommentProj(){
        super("CommentProj");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Definir o projeto a comentar
        System.out.println("Write the project Id to question: ");
        try{
           proj = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }

        //Definir o subject da mensagem
        System.out.println("Write the subject of the question: ");
        try{
           subject = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }


        //Definir a mensagem
        System.out.println("Write your question: ");
        try{
           question = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
        requestId = ++Client.requestId;
    }
}
