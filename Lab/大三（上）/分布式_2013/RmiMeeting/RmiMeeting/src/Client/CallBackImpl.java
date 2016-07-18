package Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Rmi.MeetingInfo;

public class CallBackImpl  extends UnicastRemoteObject implements CallBackInterface{
	private List<MeetingInfo> my_meetings = new ArrayList<MeetingInfo>();
	protected CallBackImpl() throws RemoteException {//客户回调
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getOneSearchResult(String description_label,String start_user,String book_user,Date start_time,Date end_time)
			throws RemoteException {//回调一个查询结果
		// TODO Auto-generated method stub
		MeetingInfo one_meeting = new MeetingInfo(description_label,start_user,book_user,start_time,end_time);
		this.my_meetings.add(one_meeting);
	}
	
	public List<MeetingInfo> getMyMeetings(){//返回所有结果
		return this.my_meetings;
	}

}
