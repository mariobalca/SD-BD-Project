import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by Rui on 27/10/2015.
 */
public class PledgeProj extends Request {
    public int proj, path;
    public double valor;
    public int requestId;

    public PledgeProj(){
        super("PledgeProj");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Definir o projeto a apoiar
        System.out.println("Write the project Id to pledge: ");
        try {
            proj = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
        }

        //Definir a quantia com que apoiar o projeto
        System.out.println("Write how much you want to plege the project (exact reward values only): ");
        try{
           valor = (Double.parseDouble(reader.readLine()));
        }
        catch (Exception e) {
        }

        //Escolher o caminho (voto) para o projeto
        System.out.println("Select witch path you want to vote on (path id): ");
        try{
            path = (Integer.parseInt(reader.readLine()));
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
                return new BooleanResponse("PledgeProj",rmiServer.financeProject(proj,requestId,userId,path,valor));
            } catch (RemoteException e) {
                verifica = false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new BooleanResponse("PledgeProj", false);
    }
}
