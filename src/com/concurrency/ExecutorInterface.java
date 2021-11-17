package com.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

class ExecutorImp implements Executor{

    @Override
    public void execute(Runnable command){
        new Thread(command).start();
    }
}

class NewThread implements Runnable{

    @Override
    public void run(){
        System.out.println("Thread executed under an executor");
    }
}

public class ExecutorInterface {
    public static void main(String[] args) {
        ExecutorImp emp = new ExecutorImp();
        try {
            emp.execute(new NewThread());
        }catch (RejectedExecutionException | NullPointerException e){
            System.out.println("Exception occur");
        }
    }
}