package com.vitor.app.practice;

import com.vitor.app.core.Speed;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Synchronized {

    @Getter
    @Setter
    private int num;
    @Getter
    private static int numStatic;

    @Speed
    public synchronized void syncMethod(){
        setNum(getNum()+1);
    }

    @Speed
    public void syncBlock(){
        synchronized (this){
            setNum(getNum()+1);
        }
    }

    @Speed
    protected static synchronized void syncStatic(){
        numStatic +=1;
    }

}
