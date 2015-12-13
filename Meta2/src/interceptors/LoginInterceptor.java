package interceptors;

/**
 * Created by mariobalca on 12/13/15.
 */
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> session = invocation.getInvocationContext().getSession();

        String username = (String) session.get("username");

        if (username == null)
        {
            return Action.LOGIN;
        }
        else
        {
            return invocation.invoke();
        }
    }
}