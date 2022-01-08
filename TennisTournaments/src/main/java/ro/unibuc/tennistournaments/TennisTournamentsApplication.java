package ro.unibuc.tennistournaments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ro.unibuc.tennistournaments.service.EmailSenderService;

@SpringBootApplication
public class TennisTournamentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TennisTournamentsApplication.class, args);
    }

}
