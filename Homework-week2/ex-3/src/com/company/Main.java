package com.company;

public class Main {

    public static void main(String[] args) {
	    int floorJump = 9;
	    for (int i = floorJump; i >= 0; i--) {
	        switch (i) {
                case 8: {
                    System.out.println("Здравей, Ивано!");
                } break;
                case 7: {
                    System.out.println("Здравей, Марийке!");
                } break;
                case 6: {
                    System.out.println("Здравей, Герганче!");
                } break;
                case 5: {
                    System.out.println("Здравей, Ивче!");
                } break;
                case 4: {
                    System.out.println("Здравей, Пепи!");
                } break;
                case 3: {
                    System.out.println("Здравей, Мончи!");
                } break;
                case 2: {
                    System.out.println("Здравей, Крики!");
                } break;
                case 1: {
                    System.out.println("Здравей, Иванчо!");
                } break;
                case 0: {
                    System.out.println("Ауууч!");
                } break;
            }
        }
    }
}
