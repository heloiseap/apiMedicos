package apiMedicos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiMedicosApplication {

    private static final Logger LOGGER = LogManager.getLogger(ApiMedicosApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiMedicosApplication.class, args);

		LOGGER.debug("nivel debug");
		LOGGER.info("nivel INFO");
		LOGGER.warn("nivel WARN");

	}



}
