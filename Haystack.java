package haystack;
import java.lang.Object;
import java.util.Scanner;

public class Haystack{
    
    
    
    
    public static int countWords(String sen, String needle) {
        int i = 0;
        int count = 0;
     
        //while(i == sen.length() || !emptyCheck ){
        
        if (sen.isEmpty()) {
           return 0;
        } 
            if (!sen.isEmpty()) {
                Scanner scan = new Scanner(sen).useDelimiter("\\W");
                    while(scan.hasNext()){
                        String sub = scan.next().toLowerCase();

                        //System.out.print(sub + " ");
                        if (sub.equals(needle.toLowerCase())){

                            count++;
                            //System.out.println(count);
                            i++;
                        }
                        else
                            i++;
                
            }
        }
        System.out.println();
        return count;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        String str = "The western American city of San Francisco, California suffered a huge earthquake on April 18th, 1906.\n" +
                    "More than three thousand people are known to have died. The true number of dead will never be known. Two\n" +
                    "hundred fifty thousand people lost their homes. Just a few hours after the terrible earthquake, a magazine named\n" +
                    "Collier's sent a telegraph message to the famous American writer Jack London. They asked Mr. London to go to\n" +
                    "San Francisco and report about what he saw.\n";
        
        String needle = "earthquake";
        
        System.out.println("Input: " + str);
        System.out.println("Find: " + needle);
        System.out.println("Number of Occurances: " + countWords(str,needle));
        
        Scanner scanIn = new Scanner(System.in);
        String input = "";
        System.out.println("Enter a new Needle or n To continue");
        input = scanIn.nextLine();
        
        while(input != "n"){
            
            String newStr = "";
            newStr = input;
            System.out.println("Number of Occurances: " + countWords(str,newStr)+ "\n");
            System.out.println("Enter a new Needle or n To continue");
            scanIn.nextLine();
            input.toLowerCase();
            
        }
        
    }
    
}
