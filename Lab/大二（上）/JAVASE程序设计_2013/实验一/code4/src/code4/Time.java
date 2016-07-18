package code4;

public class Time {
	public static class Timeclass{
		private long hour;
	    private long minute;
	    private long second;

	    public Timeclass(){
	        second = (int)(System.currentTimeMillis()/1000)%60;
	        minute = (int)(System.currentTimeMillis()/1000/60)%60;
	        hour   = (int)(System.currentTimeMillis()/1000/60/60+8)%24;
	        }
	    public Timeclass(long elapseTime){
	        second = (elapseTime / 1000) % 60;
	        minute = (elapseTime / 1000 / 60) % 60;
	        hour   = (elapseTime / 1000 / 60 / 60) % 24;
	    }
	    public Timeclass(int hour, int minute, int second){
	         this.hour = hour;
	         this.minute = minute;
	         this.second = second;
	         if(this.hour < 0 || this.hour > 24)  this.hour = 0;
	         if(this.minute < 0 || this.minute > 60)  this.minute = 0;
	         if(this.second < 0 || this.second > 60)  this.second = 0;
	   }
	    
	   public void setTime(long elapseTime){
	         second = (int) (elapseTime / 1000) % 60 ;
	         minute = (int) ((elapseTime / (1000*60)) % 60);
	         hour   = (int) ((elapseTime / (1000*60*60)) % 24);
	   }
    	public void setTime(int hour, int minute, int second){
	        this.hour = hour;
		    this.minute = minute;
		    this.second = second;
		    if(this.hour < 0 || this.hour > 24)  this.hour = 0;
		    if(this.minute < 0 || this.minute > 60)  this.minute = 0;
		    if(this.second < 0 || this.second > 60)  this.second = 0;
	    }
    	
	    public long getHour() {return hour;}
	    public long getMinute() {return minute;}
	    public long getSecond() {return second;}

	   public String toString(){
	       return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
	   }
	}
	
	public static void main(String [] args){
	      Timeclass t1 = new Timeclass();
	      System.out.println("Now Time:"+t1);
	      Timeclass t2 = new Timeclass(555550000);
	      System.out.println("Set Time:"+t2);

	   }

}
