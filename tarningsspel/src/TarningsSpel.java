import java.util.Scanner;

public class TarningsSpel {

    // Retrieves the current highscore array and displays it
    public static int gethighscore(int[] arrayHighScore, String name) {
        int maxValue=arrayHighScore[0];
        System.out.println(name + "highscores top 3:\n");
        for(int i=0;i < arrayHighScore.length;i++){
            maxValue = arrayHighScore[i];
            System.out.println("Plats " + (i+1) + ": " + maxValue);
        }
        System.out.println("");
        return maxValue;
    }

    // Search highscore array and replace with new higher values
    public static int sethighscore(int[] arrayHighScore, int newHighScore) {
        int maxValue=0;
        for(int i=0;i < arrayHighScore.length;i++){
            if(arrayHighScore[i] < newHighScore){
                maxValue = arrayHighScore[i];
                arrayHighScore[i] = newHighScore;
                break;
            }
        }
        return maxValue;
    }

    public static void main (String[] args)
            throws InterruptedException // Just for sleep to compile
    {

        int[] arrayHighScore = {0, 0, 0}; // Create highscore array setting all to zero

        while (1==1) { // Dummy loop that will break if user enter q or Q

            Scanner scan = new Scanner(System.in);

            System.out.print("Hur många tärningskast (q för att avsluta): ");

            String avsluta = scan.nextLine(); // Reading nextLine to be able to parse both int and string

            if (avsluta.equals("q") || avsluta.equals("Q")) { // Aborting main if user hit the q or Q
                System.out.print("Tack för att du spelade, välkommen åter!");
                break;
            }

            int antalKast=Integer.parseInt(avsluta); // Converting to int if user enter a number

            int tarningsSidor = 6;
            int i = 0;
            int[] arraySpelare1 = new int[antalKast]; // array for storing Player 1 dice rolls
            int[] arraySpelare2 = new int[antalKast]; // array for storing Player 2 dice rolls
            int kastSpelare1;
            int kastSpelare2;

            Thread.sleep(1000);

            while (antalKast > i) { // looping player dice rolls based on selected amount of rolls
                kastSpelare1 = (int) (1 + Math.random() * tarningsSidor);
                kastSpelare2 = (int) (1 + Math.random() * tarningsSidor);
                Thread.sleep(1000);
                // (i+1) done for display purpose player 1-3 instead 0-2
                System.out.println("Kast " + (i + 1) + " resultat spelare 1: " + kastSpelare1);
                Thread.sleep(1000);
                System.out.println("Kast " + (i + 1) + " resultat spelare 2: " + kastSpelare2);
                arraySpelare1[i] = kastSpelare1;
                arraySpelare2[i] = kastSpelare2;
                System.out.println("");
                //System.out.println(i + " " + kastSpelare1); // Debug printing array index and value
                i++;
            }

            int sumSpelare1 = 0;
            for (int num : arraySpelare1) { // Calc summary for Player 1, add all values from array
                sumSpelare1 = sumSpelare1 + num;
            }
            Thread.sleep(1000);
            System.out.println("Summa spelare 1: " + sumSpelare1 + " \n");

            int sumSpelare2 = 0;
            for (int num : arraySpelare2) { // Calc summary for Player 2, add all values from array
                sumSpelare2 = sumSpelare2 + num;
            }
            System.out.println("Summa spelare 2: " + sumSpelare2 + " \n");

            Thread.sleep(1000);
            if (sumSpelare1 > sumSpelare2) { // Checking who won with the highest score or if result was equal
                System.out.println("Spelare 1 vinner!");
            } else if (sumSpelare1 < sumSpelare2) {
                System.out.println("Spelare 2 vinner!\n");
            } else {
                System.out.println("Oavgjort!\n");
            }

            // Checking and display old highscores, first run all highscores will be zero
            String name = "Tidigare ";
            int index = gethighscore(arrayHighScore, name);

            int testHighScore;
            if (sumSpelare1 > sumSpelare2){
                testHighScore = sumSpelare1;
            }
            else{
                testHighScore = sumSpelare2;
            }
            // Testing only the current winner result if it should enter highscore
            index = sethighscore(arrayHighScore, testHighScore);

            // Testing the old highscore value returned above, this is for moving value to second place
            index = sethighscore(arrayHighScore, index);

            // Testing the old highscore value returned above, this is for moving value to third place
            index = sethighscore(arrayHighScore, index);

            // Checking and display new highscores
            name = "Nuvarande ";
            index = gethighscore(arrayHighScore, name);
        }

    }

}
