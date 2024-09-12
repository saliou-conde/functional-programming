package de.arag.functinal.programming.iq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Initiliazation {
    static {
        log.info("Static Block");
    }
    {
        log.info("Instance Block");
    }
    public Initiliazation() {
        log.info("Constructor");
    }
    public static void main(String[] args) {
        new Initiliazation();
        new Initiliazation();
    }
}
