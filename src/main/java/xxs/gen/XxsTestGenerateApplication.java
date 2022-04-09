package xxs.gen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xxs.gen.gentest.GenTestApplication;

@SpringBootApplication
public class XxsTestGenerateApplication  implements ApplicationRunner {
    @Autowired
    private GenTestApplication genTestApplication;

    public static void main(String[] args) {
        SpringApplication.run(XxsTestGenerateApplication.class, args);
    }

    @Bean
    public GenTestApplication genTestApplication(){
        return new GenTestApplication();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        genTestApplication.before();
        genTestApplication.application();
    }
}
