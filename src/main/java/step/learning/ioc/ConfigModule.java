package step.learning.ioc;

import com.google.inject.AbstractModule;
import step.learning.ioc.service.hash.HashService;
import step.learning.ioc.service.hash.Md5HashService;
import step.learning.ioc.service.hash.SHA256;

public class ConfigModule extends AbstractModule {
    // освновной метод, в котором производятся настройки служб
    @Override
    protected void configure(){
        bind(HashService.class).to(SHA256.class);
    }
}
