package galgeleg;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.util.Scanner;


public class Galgeklient {
    public static void main(String[] args) {
        HttpResponse response;
        JSONObject game;
        JSONObject status;
        Scanner scan = new Scanner(System.in);
        boolean playAgain = true;
        boolean loggedIn = false;

        do {
            System.out.println("--- Please enter username ---");
            String usrName = scan.next();

            System.out.println("--- Please enter password ---");
            String usrPass = scan.next();
            response = Unirest.post("http://localhost:8080/login").body("{\"user\":\"" + usrName +"\",\"pass\":\"" + usrPass +"\"}").asString();
            if (response.getBody().toString().equals("true")) {
                loggedIn = true;
            }

        } while (!loggedIn);

        do {
            System.out.println("-- Welcome to this gallowgame --");

            game = Unirest.get("http://localhost:8080/newgame").asJson().getBody().getObject();
            System.out.println(game);

            do {
                System.out.println("--- New round --- \n");
                System.out.println("Guess a letter\n");
                String guess = scan.next().trim().toLowerCase();

                response = Unirest.post("http://localhost:8080/guess").body(guess).asJson();

                status = new JSONObject(String.valueOf(response.getBody()));
                status(status);

            } while(!status.getBoolean("gameOver"));

            System.out.println(status.getBoolean("gameIsWon") ? "You won!" : "You lost");


            System.out.println("wanna play again? Press \"0\" for no, \"1\" for yes");

            if(scan.nextInt() == 0) {
                playAgain = false;
            }

        } while (playAgain);
    }



    private static void status(JSONObject status) {
        System.out.println("Word: " + status.get("visibleWord") + " Letters used: " + status.get("usedLetters")
                + " lives used: " + status.get("lives"));
    }
}
