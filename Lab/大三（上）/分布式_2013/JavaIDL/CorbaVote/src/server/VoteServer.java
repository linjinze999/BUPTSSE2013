package server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import CorbaVote.Vote;
import CorbaVote.VoteHelper;

public class VoteServer {
	public static void main(String[] args){
		try{
            //创建一个ORB实例 
            ORB orb = ORB.init(args, null); 

            //得到一个RootPOA的引用，并激活POAManager 
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA")); 
            rootpoa.the_POAManager().activate(); 

            // create servant and register it with the ORB 
            //创建一个HelloImpl实例（servant），并注册到ORB上 
            VoteImpl voteImpl = new VoteImpl(); 
            voteImpl.setORB(orb); 

            //从服务中得到对象的引用 
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(voteImpl); 
            Vote href = VoteHelper.narrow(ref); 

            //得到一个根名称的上下文 
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService"); 
            // Use NamingContextExt which is part of the Interoperable 
            // Naming Service (INS) specification. 
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef); 

            //在命名上下文中绑定这个对象 
            String name = "Vote"; 
            NameComponent path[] = ncRef.to_name(name); 
            ncRef.rebind(path, href); 

            System.out.println("VoteServer ready and waiting ..."); 

            //启动线程服务，等待客户端的调用 
            orb.run();
            
            //等待停止
            System.in.read();
            orb.shutdown(false);
		}catch (Exception e) { 
            System.err.println("ERROR: " + e); 
            e.printStackTrace(System.out);
		}
		System.out.println("VoteServer Exiting ..."); 
	}
}
