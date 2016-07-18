package server;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.ORB;

import CorbaVote.VotePOA;

public class VoteImpl extends VotePOA{
	private List<candidate> candidates = new ArrayList<candidate>();
	private ORB orb; 
	
	public VoteImpl(){
		for(int i=1;i<=10;i++){
			candidate new_candidate = new candidate("candidate"+i,0);
			candidates.add(new_candidate);
		}
	}
	
    public void setORB(ORB orb_val) { 
            orb = orb_val; 
    }
    
	@Override
	public String getList() {
		// TODO Auto-generated method stub
		String result="";
		for(int i = 0;i < candidates.size();i++){
			result += candidates.get(i).getName() + ":"+candidates.get(i).getVote()+";";
		}
		return result;
	}

	@Override
	public void castVote(String name) {
		// TODO Auto-generated method stub
		for(int i=0;i<candidates.size();i++){
			if(candidates.get(i).getName().compareTo(name)==0)
				candidates.get(i).setVote(candidates.get(i).getVote()+1);
		}
	}
	
	public class candidate{
		private String name;
		private int vote;
		public candidate(){
			this.name = "unknown";
			this.vote = 0;
		}
		public candidate(String name,int vote){
			this.name = name;
			this.vote = vote;
		}
		public String getName(){
			return this.name;
		}
		public void setName(String name){
			this.name = name;
		}
		public int getVote(){
			return this.vote;
		}
		public void setVote(int vote){
			this.vote = vote;
		}
	}
	
}
