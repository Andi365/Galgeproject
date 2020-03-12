package galgeleg;

import game.GalgelegInterface;

import java.rmi.Naming;

public class GalgeServer {

    public static void main(String[] args) throws Exception {
        java.rmi.registry.LocateRegistry.createRegistry(8081);
        GalgelegInterface glI = new Galgelogik();
        Naming.rebind("rmi://localhost:8081/logic",glI);
        System.out.println("Galgeservice registreret.");

        /*
        System.setProperty("java.rmi.server.hostname", "dist.saluton.dk");
        java.rmi.registry.LocateRegistry.createRegistry(23011);
        GalgelegInterface glI = new galgeleg.Galgelogik();
        Naming.rebind("rmi://dist.saluton.dk:23011/galgeleg", glI);
        System.out.println("Galgeservice registreret. hej2");

         */
    }
}
