import java.util.Scanner;


public class Lab9 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("What is the first word?");
        String fw = scan.nextLine();

        System.out.print("What is the second word?");
        String sw = scan.nextLine();
        if(fw.charAt(1)==sw.charAt(1)){
            System.out.println("These letters are the same");
        }
        else{
            System.out.println("the letters are not the same");
        }
        System.out.print("The edit distance between " +fw +" and "+sw+" is");

    }


    //function to find the lowest value of two strings
    public static Integer dist(String fw, String sw){
            /*
            Int to 
             */
            int [][]matrix = new int[fw.length()][sw.length()];
            matrix[0][0] = 0;



        return 5;
    }
}