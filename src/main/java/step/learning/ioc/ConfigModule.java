package step.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import step.learning.ioc.service.hash.HashService;
import step.learning.ioc.service.hash.Md5HashService;
import step.learning.ioc.service.hash.SHA256;
import step.learning.ioc.service.random.RandomService;
import step.learning.ioc.service.random.RandomServiceV1;

public class ConfigModule extends AbstractModule {
    // освновной метод, в котором производятся настройки служб
    @Override
    protected void configure(){
        bind(HashService.class).to(Md5HashService.class);
        bind(HashService.class).annotatedWith(Names.named("Hash256")).to(SHA256.class);
        bind(HashService.class).annotatedWith(Names.named("Digest-Hash")).to(Md5HashService.class);
        bind(HashService.class).annotatedWith(Names.named("DSA-Hash")).to(SHA256.class);
    }
    private  RandomService randomService;
    @Provides
    private RandomService injectRandomService(){
        if(randomService == null){
            randomService = new RandomServiceV1();
            randomService.seed("0");
        }
        return  randomService;
    }
}
