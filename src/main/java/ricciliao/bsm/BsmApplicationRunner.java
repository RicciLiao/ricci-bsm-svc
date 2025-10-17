package ricciliao.bsm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ricciliao.bsm.service.BsmUserService;

@Component("bsmApplicationRunner")
public class BsmApplicationRunner implements ApplicationRunner {

    private BsmUserService bsmUserService;

    @Autowired
    public void setBsmUserService(BsmUserService bsmUserService) {
        this.bsmUserService = bsmUserService;
    }

    @Override
    public void run(ApplicationArguments args) {
        bsmUserService.initialize();
    }

}
