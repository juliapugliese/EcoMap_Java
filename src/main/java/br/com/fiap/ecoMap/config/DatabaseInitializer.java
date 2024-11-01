package br.com.fiap.ecoMap.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private Flyway flyway;

    @Override
    public void run(String... args) throws Exception {
        flyway.clean();
        flyway.migrate();
    }
}