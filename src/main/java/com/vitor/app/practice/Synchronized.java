package com.vitor.app.practice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Synchronized {

    @Getter
    @Setter
    private static int num;

    public synchronized void syncMethod(){
        setNum(getNum()+1);
    }

    public void syncBlock(){
        synchronized (this){
            setNum(getNum()+1);
        }
    }

    protected static synchronized void syncStatic(){
        num +=1;
    }

}
