package code3;
import javax.swing.*;
public class LeastCommon {
	static final int NUM = 100;
	static int[] prime_array = new int[NUM]; 
	static void createPrimeArray(){
		int n = 2;
		int k = 0;
		while(k < 100){
			if( isPrime (n)){
				prime_array[k] = n;
				k++;
			}
			n++;
		}
	}
	static boolean isPrime(int n)  {
		if( n < 2)    
			return false;
		for(int i = 2; i < n; i++)    
			if( n % i == 0)     
				return false;
		return true;  
	}
	
	public static int[][] getPrimeFactors( int m )  { 
		System.out.println("======== getPrimeFactors ============");
		System.out.println(m); 
		int[][] factors = new int[NUM][2];
	    while( m > 1)  
	    	for(int i = 0; i < NUM; i++){
	    		factors[i][0] = prime_array[i];
	    		if( m % prime_array[i] == 0){
	    			factors[i][1] += 1;
	    			m = m / prime_array[i];
	    		}
	    	} 
	    for(int i = 0; i < NUM; i++){
		    if(factors[i][1] != 0) 
			    System.out.println(factors[i][0] + "\t" + factors[i][1]);  
		    } 
	    return factors;
	}
	
	
	static int getLCM( int[][] factors1, int[][] factors2){
		System.out.println("======== getLCM ============");   
		int lcm = 1;
		for(int i = 0;i < NUM;i++){
			int max = (factors2[i][1] > factors1[i][1]) ? factors2[i][1] : factors1[i][1]; 
			if(max >= 1){
				lcm *= Math.pow(prime_array[i],max ); 
				System.out.println(prime_array[i] + "\t" + max); 
			}
		}
		return lcm;   
	}
	
	public static void  main(String[] args)  {
		createPrimeArray();   
		String s1 = JOptionPane.showInputDialog(null, "The first integer :"); 
		String s2 = JOptionPane.showInputDialog(null, "The second integer :"); 
		int k1 = Integer.parseInt(s1);
		int k2 = Integer.parseInt(s2);  
		int[][] factors1 = getPrimeFactors(k1); 
		int[][] factors2 = getPrimeFactors(k2);
		int lcm = getLCM(factors1, factors2); 
		System.out.println(lcm);     
	}
}


