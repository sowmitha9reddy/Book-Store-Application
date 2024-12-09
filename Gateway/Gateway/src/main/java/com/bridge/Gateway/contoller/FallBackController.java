package com.bridge.Gateway.contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/fallBackController")
public class FallBackController {

        @RequestMapping("/orderFallBack")
        public Mono<String> orderServiceFallBack(Exception e) {
            System.out.println("Exception................................................................" +e.getMessage());
            return Mono.just("Order Service is taking too long to respond or is down. Please try again later");
        }
        @RequestMapping("/cartFallBack")
        public Mono<String> cartServiceFallBack(Exception e) {
            System.out.println("Exception..........................................................................." +e.getMessage());
            return Mono.just("Cart Service is taking too long to respond or is down. Please try again later");
        }

    @RequestMapping("/bookFallBack")
    public Mono<String> bookServiceFallBack() {
        return Mono.just("Book Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/userFallBack")
    public Mono<String> userServiceFallBack() {
        return Mono.just("User Service is taking too long to respond or is down. Please try again later");
    }


}
