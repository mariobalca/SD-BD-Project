import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rui on 26/10/2015.
 */

import java.util.ArrayList;


public abstract class Request implements Serializable{

    //protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    protected String tipo;
    protected int userId;

    public Request(String tipo){
        this.tipo = tipo;
        this.userId = Client.userId;
    }

    public String getTipo(){
        return tipo;
    }

    public void awnser(IOThread thread) {
    }


    /*Request(String tipo){
        this.tipo = tipo;
        switch (this.tipo) {
            case("LOGIN"):
                new Login();
                break;

            case("PING"):
                new Ping();
                break;

            case("REGISTER"):
                new Register();
                break;

            case("LIST_ACTUAL_PROJ"):
                new ListActualProj();
                break;
            case("LIST_OLDER_PROJ"):
                new ListOlderProj();
                break;
            case("CONSULT_PROJ"):
                new ConsultProj();
                break;
            case("ADD_ADMIN"):
                new AddAdmin();
                break;
            case("ADD_REWARD"):
                new AddReward();
                break;
            case "COMMENT_RESPONSE":
                new CommentResponse();
                break;
            case "DELETE_PROJ":
                new DeleteProj();
                break;
            case "CREATE_PROJ":
                new CreateProj();
                break;
            case "CHECK_BALANCE":
                new CheckBalance();
                break;
            case "CHECK_REWARDS":
                new CheckRewards();
                break;
            case "PLEDGE_PROJ":
                new PledgeProj();
                break;
            case "COMMENT_PROJ":
                new CommentProj();
                break;
        }
    }*/



            /*
    public enum REQUEST_TYPE{
        PING,
        //Guest
        LOGIN,
        REGISTER,
        LIST_ACTUAL_PROJ,
        LIST_OLDER_PROJ,
        CONSULT_PROJ,

        //Admin
        ADD_ADMIN,
        ADD_REWARD,
        COMMENT_RESPONSE,
        EDIT_PROJ,
        DELETE_PROJ,

        //User
        CREATE_PROJ,
        CHECK_BALANCE,
        CHECK_REWARDS,
        PLEDGE_PROJ, //Inclui o voto e se quer ficar com o produto ou ofecerer
        COMMENT_PROJ,


    }*/

}
