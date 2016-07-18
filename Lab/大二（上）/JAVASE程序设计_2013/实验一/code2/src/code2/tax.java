package code2;

public class tax {
	public static void main(String[] args){
		double tax1 = 0;
		double tax2 = 0;
		double tax3 = 0;
		double tax4 = 0;
		int status = 0;
		System.out.print(
				"taxable	Single	Married	 Married    Head of\n"+
		        "Income		Joint	 Separate   a House\n");
		for(int income = 50000;income <= 60000;income += 50){
		    if(status == 0){
			    if(income <= 7300)
				    tax1 = income * 0.10;
			    else if(income <= 29700)
				    tax1 = 7300 * 0.10 + (income - 7300) * 0.15;
			    else if(income <= 71950)
				    tax1 = 7300 * 0.10 + (29700 - 7300) * 0.15 + 
				    (income - 29700) * 0.25;
			    else if(income <= 150150)
				    tax1 = 7300 * 0.10 + (29700 - 7300) * 0.15 + 
				    (71950 - 29700) * 0.25 + (income - 71950) * 0.28;
			    else if(income <= 326450)
				    tax1 = 7300 * 0.10 + (29700 - 7300) * 0.15 + 
				    (71950 - 29700) * 0.25 + (150150 - 71950) * 0.28 +
				    (income - 150150) * 0.33;
			    else
				    tax1 = 7300 * 0.10 + (29700 - 7300) * 0.15 + 
				    (71950 - 29700) * 0.25 + (150150 - 71950) * 0.28 +
				    (326450 - 150150) * 0.33 + (income - 326450) * 0.35;
			    status = 1;
		    }
		    if(status == 1){
			    if(income <= 14600)
				    tax2 = income * 0.10;
			    else if(income <= 59400)
				    tax2 = 14600 * 0.10 + (income - 14600) * 0.15;
			    else if(income <= 119950)
				    tax2 = 14600 * 0.10 + (59400 - 14600) * 0.15 + 
				    (income - 59400) * 0.25;
			    else if(income <= 182800)
				    tax2 = 14600 * 0.10 + (59400 - 14600) * 0.15 + 
				    (119950 - 59400) * 0.25 + (income - 119950) * 0.28;
			    else if(income <= 326450)
				    tax2 = 14600 * 0.10 + (59400 - 14600) * 0.15 + 
				    (119950 - 59400) * 0.25 + (182800 - 119950) * 0.28 +
				    (income - 182800) * 0.33;
			    else
				    tax2 = 14600 * 0.10 + (59400 - 14600) * 0.15 + 
				    (119950 - 59400) * 0.25 + (182800 - 119950) * 0.28 +
				    (326450 - 182800) * 0.33 + (income - 326450) * 0.35;
			    status = 2;
		    }
		    if(status == 2){
			    if(income <= 7300)
				    tax3 = income * 0.10;
			    else if(income <= 29700)
				    tax3 = 7300 * 0.10 + (income - 7300) * 0.15;
			    else if(income <= 59975)
				    tax3 = 7300 * 0.10 + (29700 - 7300) * 0.15 + 
				    (income - 29700) * 0.25;
			    else if(income <= 91400)
				    tax3 = 7300 * 0.10 + (29700 - 7300) * 0.15 + 
				    (59975 - 29700) * 0.25 + (income - 59975) * 0.28;
			    else if(income <= 163225)
				    tax3 = 7300 * 0.10 + (29700 - 7300) * 0.15 + 
				    (59975 - 29700) * 0.25 + (91400 - 59975) * 0.28 +
				    (income - 91400) * 0.33;
			    else
				    tax3 = 7300 * 0.10 + (29700 - 7300) * 0.15 + 
				    (59975 - 29700) * 0.25 + (91400 - 59975) * 0.28 +
				    (163225 - 91400) * 0.33 + (income - 163225) * 0.35;
			    status = 3;
		    }
		    if(status == 3){
			    if(income <= 10450)
				    tax4 = income * 0.10;
			    else if(income <= 39800)
				    tax4 = 10450 * 0.10 + (income - 10450) * 0.15;
			    else if(income <= 102800)
				    tax4 = 10450 * 0.10 + (39800 - 10450) * 0.15 + 
				    (income - 39800) * 0.25;
			    else if(income <= 166450)
				    tax4 = 10450 * 0.10 + (39800 - 10450) * 0.15 + 
				    (1028005 - 39800) * 0.25 + (income - 102800) * 0.28;
			    else if(income <= 326450)
				    tax4 = 10450 * 0.10 + (39800 - 10450) * 0.15 + 
				    (102800 - 39800) * 0.25 + (166450 - 102800) * 0.28 +
				    (income - 166450) * 0.33;
			    else
				    tax4 = 10450 * 0.10 + (39800 - 10450) * 0.15 + 
				    (102800 - 39800) * 0.25 + (166450 - 102800) * 0.28 +
				    (326450 - 166450) * 0.33 + (income - 326450) * 0.35;
			    status = 0;
		    }
		    System.out.println(income+"	"+(int)(tax1*100)/100.0+"	"+
		    		(int)(tax2*100)/100.0+"   "+(int)(tax3*100)/100.0+"     "+
		    		(int)(tax4*100)/100.0);
		}
	}

}
