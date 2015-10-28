import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Rui on 27/10/2015.
 */
public class CommentProj extends Request{
    private int proj;
    private String subject = "", question = "";

    public CommentProj(){
        super("CommentProj");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Definir o projeto a comentar
        System.out.println("Indique o ID do projeto para fazer uma quest�o: ");
        try{
           proj = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }

        //Definir o subject da mensagem
        System.out.println("Indique o assunto da quest�o: ");
        try{
           subject = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }


        //Definir a mensagem
        System.out.println("Escreva a sua quest�o: ");
        try{
           question = (reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Erro de escrita.");
        }
    }
}
