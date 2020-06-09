package uni.fmi.inf.java.homework;

public class Main {
    /**
     * Главен метод на програмата.
     * @param args
     */
    public static void main (String [] args) {

        HeatEngine[] Heaters = new HeatEngine[10];
        WrapperEngine[] Wrappers = new WrapperEngine[10];

        for (int i = 0; i < Heaters.length; i++) {
            Heaters[i] = new HeatEngine();
            Wrappers[i] = new WrapperEngine();
            new HeatManager(Heaters[i],Wrappers[i]);
        }

    }
}
