package com.example.rest;


import game.GalgelegInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@RestController
public class GameController {
    private GalgelegInterface logik;

    public void getInfo() throws RemoteException, NotBoundException, MalformedURLException {
        logik = (GalgelegInterface) Naming.lookup("rmi://localhost:8081/logic");
    }

    @GetMapping("/game")
    public Game getGame() throws RemoteException, NotBoundException, MalformedURLException {
        getInfo();

        if (logik != null) {
            //return new Game("hej",0, new ArrayList<String>(),false);
            return new Game(logik.getSynligtOrd(), logik.getAntalForkerteBogstaver(), logik.getBrugteBogstaver(), logik.erSpilletSlut());
        }
        return null;
    }

    @PostMapping
    public void guess(@RequestParam("guess") String guess) throws RemoteException, MalformedURLException, NotBoundException {
        getInfo();
        logik.gætBogstav(guess);
    }
}
