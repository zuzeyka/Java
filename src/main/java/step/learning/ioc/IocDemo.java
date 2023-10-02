package step.learning.ioc;

import step.learning.ioc.service.hash.HashService;

import javax.inject.Inject;

public class IocDemo {
    @Inject
    private HashService hashService;
    public void run(){
        String text = "Ioc Demo";
        System.out.println(text);
        System.out.println(hashService.hash(text));
    }
}
