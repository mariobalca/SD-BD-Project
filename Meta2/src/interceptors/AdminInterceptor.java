package interceptors;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

/**
 * Created by mariobalca on 12/13/15.
 */
public class AdminInterceptor extends AbstractInterceptor {
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
