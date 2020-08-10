package com.example;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.utils.DefaultSecurityService;
import io.reactivex.Completable;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Secured("isAuthenticated()")
@Controller
public class MyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyController.class);
    @Inject
    private DefaultSecurityService defaultSecurityService;

    @Inject
    private ScheduledExecutorService executorService;

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public boolean index(@QueryValue("name") String str) {

        System.out.println("defaultSecurityService.getAuthentication(): " + defaultSecurityService.getAuthentication());

        CompletableFuture.supplyAsync(() -> {System.out.println("defaultSecurityService.getAuthentication(): " + defaultSecurityService.getAuthentication());
        return true;}, executorService);

        Random r = new Random();
        try {
            int millis = (r.nextInt(5) + 1) * 1000;
            System.out.println("String " + str + " sleeping for " + millis);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return str.length()==6;
    }

}
