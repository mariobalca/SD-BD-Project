import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Rui on 27/10/2015.
 */
public class CommentResponse extends Request{
    int id;
    String response;
    public int requestId;

    public CommentResponse(){
        super("CommentResponse");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        //Perguntar o ID da mensagem a que estamos a responder
        System.out.println("Write the question Id: ");
        try{
            id = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
        }

        //Escrever a resposta
        System.out.println("Write your answer: ");
        try{
           response = (reader.readLine());
        }
        catch (Exception e) {
        }
        requestId = ++Client.requestId;

    }

    @Override
    public Response execute(RMI rmiServer){
        boolean verifica = false;
        while(!verifica){
            try {
                return new BooleanResponse("CommentResponse", rmiServer.answerMessage(id, response, requestId, userId));
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new BooleanResponse("CommentResponse", false);
    }
}
