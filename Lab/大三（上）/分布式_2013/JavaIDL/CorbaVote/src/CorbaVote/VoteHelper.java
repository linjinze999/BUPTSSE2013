package CorbaVote;


/**
* CorbaVote/VoteHelper.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从vote.idl
* 2015年12月29日 星期二 下午05时06分33秒 CST
*/

abstract public class VoteHelper
{
  private static String  _id = "IDL:CorbaVote/Vote:1.0";

  public static void insert (org.omg.CORBA.Any a, CorbaVote.Vote that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static CorbaVote.Vote extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (CorbaVote.VoteHelper.id (), "Vote");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static CorbaVote.Vote read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_VoteStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, CorbaVote.Vote value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static CorbaVote.Vote narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof CorbaVote.Vote)
      return (CorbaVote.Vote)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      CorbaVote._VoteStub stub = new CorbaVote._VoteStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static CorbaVote.Vote unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof CorbaVote.Vote)
      return (CorbaVote.Vote)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      CorbaVote._VoteStub stub = new CorbaVote._VoteStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
