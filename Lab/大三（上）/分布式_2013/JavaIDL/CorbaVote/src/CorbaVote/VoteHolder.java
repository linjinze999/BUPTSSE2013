package CorbaVote;

/**
* CorbaVote/VoteHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从vote.idl
* 2015年12月29日 星期二 下午05时06分33秒 CST
*/

public final class VoteHolder implements org.omg.CORBA.portable.Streamable
{
  public CorbaVote.Vote value = null;

  public VoteHolder ()
  {
  }

  public VoteHolder (CorbaVote.Vote initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CorbaVote.VoteHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CorbaVote.VoteHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CorbaVote.VoteHelper.type ();
  }

}
