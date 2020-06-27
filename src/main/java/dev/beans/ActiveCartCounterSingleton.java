package dev.beans;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
public class ActiveCartCounterSingleton {
    private int activeCartCounter;

    @Lock(LockType.WRITE)
    public void incrementCounter(){
        activeCartCounter++;
    }

    @Lock(LockType.WRITE)
    public void decrementCounter(){
        activeCartCounter--;
    }

    @Lock(LockType.READ)
    public int getCounter(){
        return activeCartCounter;
    }
}
