package com.zrf.rabbitmq.example.publish;

import com.zrf.rabbitmq.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape(){
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }

}
