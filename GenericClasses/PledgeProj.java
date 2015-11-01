import java.io.BufferedReader;
import java.io.InputStreamReader;

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


        int check = 0;
        while(check == 0)
        {
            try {
                System.out.println("Write the project Id to pledge: ");
                proj = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                continue;
            }
            check =1;
        }


        check = 0;
        while(check == 0)
        {
            try {

                System.out.println("Write how much you want to plege the project (exact reward values only): ");
                valor = (Double.parseDouble(reader.readLine()));
                if (valor <= 0)
                    continue;
            } catch (Exception e) {
                continue;
            }
            check =1;
        }



        //Escolher o caminho (voto) para o projeto


        check = 0;
        while(check == 0)
        {
            try {
                System.out.println("Select witch path you want to vote on (path id): ");
                path = (Integer.parseInt(reader.readLine()));
                if(path <= 0){
                    continue;
                }
            } catch (Exception e) {
                continue;
            }
            check =1;
        }


        requestId = ++Client.requestId;

    }
}
