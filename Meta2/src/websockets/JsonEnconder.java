package websockets;

import com.google.gson.Gson;
import genericclasses.JsonResponse;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by mariobalca on 12/19/15.
 */
public class JsonEnconder implements Encoder.Text<JsonResponse> {
    @Override
    public String encode (JsonResponse response) {
        Gson gson = new Gson();

        return gson.toJson(response, JsonResponse.class);
    }

    @Override
    public void init (EndpointConfig endpointConfig){

    }

    @Override
    public void destroy(){

    }
}
