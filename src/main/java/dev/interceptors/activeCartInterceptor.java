package dev.interceptors;

import dev.beans.ActiveCartCounterSingleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.InvocationContext;

public class activeCartInterceptor {

    @EJB
    ActiveCartCounterSingleton activeCartCounterSingleton;

    @PostConstruct
    @PostActivate
    public void incrementActiveSessionCounter(InvocationContext ic) {
        activeCartCounterSingleton.incrementCounter();
        System.out.println("New Count: "+activeCartCounterSingleton.getCounter());
        System.out.println("Entering: "+ic.getTarget());
    }

    @PreDestroy
    @PrePassivate
    public void decrementActiveSessionCounter(InvocationContext ic) {
        activeCartCounterSingleton.decrementCounter();
        System.out.println("New Count: "+activeCartCounterSingleton.getCounter());
        System.out.println("Leaving: "+ic.getTarget());
    }
}
