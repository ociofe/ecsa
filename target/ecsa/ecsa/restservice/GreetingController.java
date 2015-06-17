package main.webapp.ecsa.restservice;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(headers="Accept=application/json", value="/greeting" )
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	System.out.println("RESTCOntroller");
    	Greeting ciao = new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    	return ciao;
    }
}