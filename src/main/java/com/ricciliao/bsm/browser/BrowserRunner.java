package com.ricciliao.bsm.browser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BrowserRunner implements CommandLineRunner {
    @Value("${spring.web.url}")
    private String webUrl;

    @Value("${spring.web.google.path}")
    private String googlePath;

    @Value("${spring.auto.open.url}")
    private boolean isOpen;

    @Override
    public void run(String... args) {
        if(isOpen){
            String cmd = googlePath +" "+ webUrl;
            Runtime run = Runtime.getRuntime();
            try{
                run.exec(cmd);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
