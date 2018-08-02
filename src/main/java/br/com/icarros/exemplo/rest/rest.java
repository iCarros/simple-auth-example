package br.com.icarros.exemplo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


@RestController
@RequestMapping("")
public class rest {

    @GetMapping("/location")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void get(@RequestParam("code") String code){
        System.out.println("code = " + code);
    }

}
