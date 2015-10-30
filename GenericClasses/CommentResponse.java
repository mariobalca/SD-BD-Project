import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rui on 27/10/2015.
 */
public class CommentResponse extends Request{
    int id;
    String response;

    public CommentResponse(){
        super("CommentResponse");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        //Perguntar o ID da mensagem a que estamos a responder
        System.out.println("Indique o ID da mensagem a que esta a responder: ");
        try{
            id = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
        }

        //Escrever a resposta
        System.out.println("Escreva a resposta: ");
        try{
           response = (reader.readLine());
        }
        catch (Exception e) {
        }
    }
}
