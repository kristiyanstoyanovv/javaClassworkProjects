import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class HomeStreaming {
    public static void main(String[] args) {
        int maxNumberOfVisitors = 1000;
        int currentVisitors = 0;
        double entrancePrice 	= 9.99;
        double oborotZaVecherta = 0;
        String nameOfParty 		= "EXTREME PARTY";
        boolean isAnyoneSinging = false;
        char idLetter = 'K';
        String idNumber = "0541541654156";
        String wholeIdOfTheParty = idLetter + idNumber;
        String colorsOfIceCream[] = {"Red","Green","Yellow","Black"};
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            menuPrinting();
            try {
                String choice = reader.readLine();
                switch (choice) {
                    case "1": {
                        if (isAnyoneSinging == false) {
                            System.out.println("Streammm is on! Djordjano is live! Have fun!");
                            isAnyoneSinging = true;
                        } else if (isAnyoneSinging == true) {
                            isAnyoneSinging = false;
                            System.out.println("Djordjano se umori da pee, spirame streama za kratka push pauza!");
                        }
                    } break;
                    case "2": {
                        System.out.println("+1 Visitor!");
                        currentVisitors++;
                        oborotZaVecherta += entrancePrice;
                        System.out.println("Visitors online: " + currentVisitors);
                        System.out.println("Oborot za vecherta: " + oborotZaVecherta);
                    } break;
                    case "3": {
                        if (currentVisitors <= 0) break;
                        currentVisitors--;
                        System.out.println("Visitors online: " + currentVisitors);
                    } break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    }
    public static void menuPrinting() {
        System.out.println("================ Menu ================");
        System.out.println("1) Start/Stop stream.");
        System.out.println("2) Record new visitor.");
        System.out.println("3) Leave the stream!");
        System.out.println("======================================");
    }
}