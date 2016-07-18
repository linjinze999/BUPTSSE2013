package code1;
import javax.swing.*;
import java.util.Random;

public class sum extends JFrame{
    public sum(){
    	Random rand = new Random();
    	int addnumber1 = rand.nextInt() % 10;
    	int addnumber2 = rand.nextInt() % 10;
    	String input = JOptionPane.showInputDialog("what is " + 
    	        addnumber1 + "+" + addnumber2 + "?");
        int answer = Integer.parseInt(input);
        int result = addnumber1 + addnumber2;
        String output = "" + (result == answer);
        JOptionPane.showMessageDialog(null,addnumber1 + 
        		"+" + addnumber2 + "=" + answer + " is " + output + "£¡");
    }
    
    public static void main(String[] args){
    	new sum();
    }
    
}
