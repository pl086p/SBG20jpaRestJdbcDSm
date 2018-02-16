package com.restController;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noDBjpa.Greeting;

@RestController
public class GreetingRestServiceController {

    private static final String messageString = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // mapping request /greeting to service class greeting
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="rpName", defaultValue="World (the default value)") String personName) {
        return new Greeting(counter.incrementAndGet(), 
        					String.format(messageString, personName), "myEmail");
    }
}
