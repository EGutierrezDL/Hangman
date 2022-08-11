import java.util.Scanner;

public class Hangman
{
    private static String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static String[] buildTower = {" +---+", " |   |", "     |", "     |", "     |", "     |", "     |", "======"};
    private static int tries = 5;

    
    
    private static String[] words = {"ability","absence","academy","account","backing","balance","banking","barrier","cabinet","caliber","calling","capable","dealing","decided","decline","default","eastern","economy","edition","elderly"};
    private static String chosenWord = words[(int)(Math.random() * words.length)];
    private static String[] chosenWordOutline = new String[chosenWord.length()];
    
    
    public static void guess(){
        Scanner input = new Scanner(System.in);
        chosenWord = chosenWord;
        
            System.out.println("Welcome to Hangman! Your word has " + chosenWord.length() + " letters and starts with " + chosenWord.substring(0, 1));
        
            for(int i = 0; i < chosenWord.length(); i++){
               chosenWordOutline[i] = (" __ "); 
               System.out.print(chosenWordOutline[i]);
            }
        
        System.out.println();
        System.out.println();
        printLetters();
        System.out.println();
        System.out.println();
        build();
        whichGuess();
    }
        //then do for loop for adding body parts, if guessesLeft = 1, print whole body
    public static void whichGuess(){
        Scanner input = new Scanner(System.in);
        
        if(tries >= 0) {
            System.out.println();

                System.out.print("Would you like to: \"1\" Guess a letter or \"2\" Guess the word? ");

            String guessType = input.nextLine();
            //System.out.println();
            
            while(guessType != "1" || guessType != "2"){
                if(guessType.equals("1")){
                    letterGuesser();
                    break;
                }else if(guessType.equals("2")){
                    wordGuesser();
                    break;
                }else{

                        System.out.println("Please select \"1\" or \"2\"");

                    guessType = input.nextLine();
                }
            }
        }else{

                System.out.println("You killed him");
                System.out.print("The word was " + chosenWord);
            }
        }
    
    public static void letterGuesser(){
        Scanner input = new Scanner(System.in);
        boolean inAlph = false;

            System.out.print("What letter would you like to guess? ");
            String letterGuess = input.nextLine();
            System.out.println();
        
      
        
            for(int i = 0; i < 26; i++){
                if(alphabet[i].equals(letterGuess)){
                    alphabet[i] = "-";
                    inAlph = true;
                }
            }
        
            if(inAlph == false){
                System.out.println("Not in alphabet / Previously guessed");
                printLetters();
                whichGuess();
            }
            
            System.out.println();
            int inArray = 0;


            while(tries >= 0){
                
                for(int i = 0; i < chosenWord.length(); i++){
                    if(chosenWord.substring(i,i + 1).equals(letterGuess.toLowerCase())){
                        chosenWordOutline[i] = " " + letterGuess + " ";
                    }
                }
                //68 and 73 are connected
                for(int i = 0; i < chosenWord.length(); i++){
                    if(chosenWordOutline[i].equals(" " + letterGuess + " ")){
                       inArray = 1; 
                    }
                }
            
            if(inArray == 1){   
                
                for(int i = 0; i < chosenWord.length(); i++){
                        System.out.print(chosenWordOutline[i]);
                }
                
                System.out.println();
                System.out.println(letterGuess + " is in the word!");

                
                System.out.println();
                printLetters();
                System.out.println();
                System.out.println();
                whichGuess();
                
            }else{
                
                for(int i = 0; i < chosenWord.length(); i++){
                    System.out.print(chosenWordOutline[i]);
                }
                
                System.out.println();
                System.out.println(letterGuess + " is not in the word...");
                System.out.println();
                
                
                if(tries == 5){
                    buildTower[2] = " O   |";
                    build();
                    printLetters();
                    tries--;
                    whichGuess();
                }
                if(tries == 4){
                    buildTower[2] = " O   |";
                    buildTower[3] = " |   |";
                    build();
                    printLetters();
                    tries--;
                    whichGuess();
                }
                if(tries == 3){
                    buildTower[2] = " O   |";
                    buildTower[3] = "/|   |";
                    build();
                    printLetters();
                    tries--;
                    whichGuess();
                }
                if(tries == 2){
                    buildTower[2] = " O   |";
                    buildTower[3] = "/|\\  |";
                    build();
                    printLetters();
                    tries--;
                    whichGuess();
                }
                if(tries == 1){
                    buildTower[2] = " O   |";
                    buildTower[3] = "/|\\  |";
                    buildTower[4] = "/    |";
                    build();
                    printLetters();
                    tries--;
                    whichGuess();
                }
                if(tries == 0){
                    buildTower[2] = " O   |";
                    buildTower[3] = "/|\\  |";
                    buildTower[4] = "/ \\  |";
                    build();
                    tries--;
                    whichGuess();
                }
            }
        }
    }
    
    public static void wordGuesser(){
        Scanner input = new Scanner(System.in);
        
        System.out.print("What is your guess? ");
        String wordGuess = input.nextLine();
        System.out.println();
        
        if(wordGuess.equals(chosenWord)){
            System.out.print("Good one");
            tries = -1;
        }else{
            if(tries == 5){
                System.out.println("Maybe guess more letters first");
                System.out.println();
                whichGuess();
            }else{
                System.out.println("Nope");
                System.out.println();
                whichGuess();
            }
        }
    }
    
    
    public static void printLetters(){
        
        System.out.print("Letters: "); 
        for(int i = 0; i < alphabet.length; i++){
            System.out.print(alphabet[i]);
        }
        System.out.println();
        System.out.println();
    }
    
    public static void build(){
        System.out.println(buildTower[0]);
        System.out.println(buildTower[1]);
        System.out.println(buildTower[2]);
        System.out.println(buildTower[3]);
        System.out.println(buildTower[4]);
        System.out.println(buildTower[5]);
        System.out.println(buildTower[6]);
        System.out.println(buildTower[7]);
        System.out.println();
    }
}
