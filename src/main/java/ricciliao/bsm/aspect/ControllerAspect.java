package ricciliao.bsm.aspect;

import org.aopalliance.intercept.MethodInvocation;
import ricciliao.dynamic.aop.DynamicAspect;

public class ControllerAspect extends DynamicAspect {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return super.invoke(invocation);
    }

}
