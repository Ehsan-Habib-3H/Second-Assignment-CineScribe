import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Done --> complete main function
        while (true) {
            int choice = runMenu();
            if (choice == '1') {
                System.out.println("Enter the name of movie:");
                Scanner scanner = new Scanner(System.in);
                String title = scanner.nextLine();
                //Clear the console
                System.out.print("\033[H\033[2J");
                Movie movie = new Movie(new ArrayList<>(),"",0);
                try {
                    String s = movie.getMovieData(title);
                    System.out.println("\nVᴏᴛᴇ: " + movie.getImdbVotesViaApi(s) + "\n");
                    System.out.println(movie.getRatingViaApi(s));
                    movie.getActorListViaApi(s);
                } catch (Exception e) {
                    System.out.println("F*** u: " + e);
                }
                System.out.println("[B].Back");
                char input = scanner.next().charAt(0);
                while (input != 'B') {
                    input = scanner.next().charAt(0);
                }
            }
            if (choice == '2') {
                System.out.println("Enter the name of actor:");
                Scanner scanner = new Scanner(System.in);
                String title = scanner.nextLine();
                //Clear the console
                System.out.print("\033[H\033[2J");
                try {
                    Actors actors = new Actors("",false);
                    String s2 = actors.getActorData(title);
                    if (actors.isAlive(s2))
                        System.out.println("Sᴛᴀᴛᴜs: Is dead and date: " + actors.getDateOfDeathViaApi(s2));
                    else
                        System.out.println("Sᴛᴀᴛᴜs: Is alive!");
                    System.out.println("Nᴇᴛ ᴡᴏʀᴛʜ: " + actors.getNetWorthViaApi(s2));
                    System.out.println("[B].Back");
                    char input = scanner.next().charAt(0);
                    while (input != 'B') {
                        input = scanner.next().charAt(0);
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Exception :( "+e);
                }
            }
            if(choice == '3')
                System.exit(0);

        }

//        Movie movie = new Movie();
//        Actors actors = new Actors();
//        try {
//            String s = movie.getMovieData("green book");
//            System.out.println(s);
//            System.out.println("vote: " + movie.getImdbVotesViaApi(s));
//            System.out.println(movie.getRatingViaApi(s));
//            movie.getActorListViaApi(s);
//            String s2 = actors.getActorData("Mortensen");
//            System.out.println(s2);
//            System.out.println((actors.isAlive(s2)));
//            System.out.println(actors.getNetWorthViaApi(s2));
//
//        } catch (Exception e) {
//            System.out.println("Fuck u: " + e);
//        }
    }

    public static int runMenu() {
        // Done
        System.out.println("\uD835\uDC74\uD835\uDC90\uD835\uDC97\uD835\uDC8A\uD835\uDC86 \uD835\uDC95\uD835\uDC93\uD835\uDC82\uD835\uDC84\uD835\uDC8C");
        System.out.println("[1].Search for movie");
        System.out.println("[2].Search for actor");
        System.out.println("[3].Exit");
        System.out.println("Choose option and press enter:");
        Scanner scanner = new Scanner(System.in);
        char input = scanner.next().charAt(0);
        while (input != '1' && input != '2' && input != '3') {
            input = scanner.next().charAt(0);
        }
        return (int) input;
    }
}