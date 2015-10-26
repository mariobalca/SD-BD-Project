import java.util.ArrayList;

/**
 * Created by Rui on 26/10/2015.
 */

import java.util.ArrayList;


public class Request{

    ArrayList<String> campos_string = new ArrayList<String>();
    ArrayList<Integer> campos_int = new ArrayList<Integer>();
    ArrayList<Double> campos_double = new ArrayList<Double>();


    String tipo;

    Request(){

    }


    Request(String tipo){
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
                String proj_id;
                String
            case PING:
                break;
            case LOGIN:
                break;
            case REGISTER:
                break;
            case LIST_ACTUAL_PROJ:
                break;
            case LIST_OLDER_PROJ:
                break;
            case CONSULT_PROJ:
                break;
            case ADD_ADMIN:
                break;
            case ADD_REWARD:
                break;
            case COMMENT_RESPONSE:
                break;
            case EDIT_PROJ:
                break;
            case DELETE_PROJ:
                break;
            case CREATE_PROJ:
                break;
            case CHECK_BALANCE:
                break;
            case CHECK_REWARDS:
                break;
            case PLEDGE_PROJ:
                break;
            case COMMENT_PROJ:
                break;
        }
    }



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
