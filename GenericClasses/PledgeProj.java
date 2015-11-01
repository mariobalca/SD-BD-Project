import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rui on 27/10/2015.
 */
public class PledgeProj extends Request {
    public int proj, path;
    public double valor;
    public int requestId;

    public PledgeProj(ArrayList<Project> projects){
        super("PledgeProj");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        int check = 0;
        Project pledgedproj = null;
        while(check == 0)
        {
            try {
                System.out.println("Write the project Id to pledge: ");
                proj = Integer.parseInt(reader.readLine());
                for(Project project:projects){
                    if(project.getId() == proj){
                        pledgedproj = project;
                        check = 1;
                    }
                }
            } catch (Exception e) {

            }
            if(check == 0){
                System.out.println("Insert a valid project ID\n");
            }
        }


        check = 0;
        while(check == 0)
        {
            try {

                System.out.println("Write how much you want to plege the project (exact reward values only): ");
                valor = (Double.parseDouble(reader.readLine()));
                if(valor > 0){
                    if(pledgedproj.getRewards().size()==0){
                        check = 1;
                        continue;
                    }
                    for(Reward reward:pledgedproj.getRewards()){
                        if(valor == reward.getMinValue()){
                            check =1;
                        }
                    }
                }
            } catch (Exception e) {

            }
            if(check == 0){
                System.out.println("Insert a valid amount");
            }
        }



        //Escolher o caminho (voto) para o projeto


        if(pledgedproj.getPaths().size() > 0) {
            check = 0;
            while (check == 0) {
                try {
                    System.out.println("Select witch path you want to vote on (path id): ");
                    path = (Integer.parseInt(reader.readLine()));
                    if (path <= 0) {
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }
                check = 1;
            }
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
