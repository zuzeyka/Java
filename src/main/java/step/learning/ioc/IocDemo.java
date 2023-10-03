package step.learning.ioc;

import step.learning.ioc.service.hash.HashService;
import step.learning.ioc.service.random.RandomService;

import javax.inject.Inject;
import javax.inject.Named;

public class IocDemo {
    private final HashService digesthashService;
    private final HashService dsahashService;
    private  final RandomService randomService;

    @Inject // не забывать перед инжекционным конструктором
    public IocDemo(
            @Named("Digest-Hash") HashService digesthashService,
            @Named("DSA-Hash") HashService dsahashService,
            RandomService randomService) {
        this.digesthashService = digesthashService;
        this.dsahashService = dsahashService;
        this.randomService = randomService;
    }

    @Inject
    private HashService hashService2;

    @Inject @Named("Hash256") private HashService hashService256 ;

    public void run(){
        String text = "Ioc Demo";
        System.out.println(text);
        System.out.println("MD5: " + digesthashService.hash(text));
        System.out.println("SHA-256: " + dsahashService.hash(text));
        System.out.println("Random: " + randomService.randomHex(6));
    }
}
