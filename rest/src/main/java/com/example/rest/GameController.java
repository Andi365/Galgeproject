package com.example.rest;


import game.GalgelegInterface;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GameController {
    private GalgelegInterface logik;

    public void getInfo() throws RemoteException, NotBoundException, MalformedURLException {
        logik = (GalgelegInterface) Naming.lookup("rmi://localhost:8081/logic");

    }


    @GetMapping("/newgame")
    public Game getGame() throws RemoteException, NotBoundException, MalformedURLException {
        getInfo();
        System.out.println("newgame started");
        return new Game(logik.getSynligtOrd(), logik.getAntalForkerteBogstaver(),
                logik.getBrugteBogstaver(), logik.erSpilletSlut(), "-- Nyt spil er startet --");
    }

    @PostMapping("/guess")
    public Game guessLetter(@RequestBody String guess) throws RemoteException {
        System.out.println(guess);
        logik.gætBogstav(guess);
        System.out.println("guess confirmed");

        if(logik.erSpilletVundet()) {
            return new Game(logik.getSynligtOrd(), logik.getAntalForkerteBogstaver(),
                    logik.getBrugteBogstaver(), logik.erSpilletSlut(), "Du gættede på " + guess, logik.getOrdet());
        } else {
            return new Game(logik.getSynligtOrd(), logik.getAntalForkerteBogstaver(),
                    logik.getBrugteBogstaver(), logik.erSpilletSlut(), "Du gættede på " + guess);
        }
    }
}
