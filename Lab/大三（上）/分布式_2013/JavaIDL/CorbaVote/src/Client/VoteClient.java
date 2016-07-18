package Client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import CorbaVote.Vote;
import CorbaVote.VoteHelper;

public class VoteClient {
	static Vote voteImpl;
	public static void main(String[] args){
		try {
            //创建一个ORB实例 
            ORB orb = ORB.init(args, null); 

            //获取根名称上下文 
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService"); 
            // Use NamingContextExt instead of NamingContext. This is 
            // part of the Interoperable naming Service. 
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef); 

            //从命名上下文中获取接口实现对象 
            String name = "Vote"; 
            voteImpl = VoteHelper.narrow(ncRef.resolve_str(name)); 

            //调用接口对象的方法 
            voteImpl.castVote("candidate1");
        	System.out.println("为 candidate1 投票完成！");
        	voteImpl.castVote("candidate3");
        	System.out.println("为 candidate3 投票完成！");
        	voteImpl.castVote("candidate9");
        	System.out.println("为 candidate9 投票完成！");
        	voteImpl.castVote("candidate1");
        	System.out.println("为 candidate1 投票完成！");
        	voteImpl.castVote("candidate7");
        	System.out.println("为 candidate7 投票完成！");
            String[] result = voteImpl.getList().split(";");
        	System.out.println("最终得票结果如下所示：");
        	for(int i=0;i<result.length;i++){
        		System.out.println(result[i]);
        	}

		} catch (Exception e) { 
            System.out.println("ERROR : " + e); 
            e.printStackTrace(System.out); 
		}
	}
}
