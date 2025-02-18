package ricciliao.bsm.aspect;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ricciliao.common.component.exception.CmnParameterException;
import ricciliao.common.component.response.ResponseUtils;
import ricciliao.dynamic.aop.DynamicAspect;

public class ControllerAspect extends DynamicAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {

            return super.invoke(invocation);
        } catch (CmnParameterException e) {

            return ResponseUtils.builder().code(e.getCode()).build();
        } catch (Exception e) {
            logger.error("", e);

            return ResponseUtils.errorResponse();
        }
    }

}
