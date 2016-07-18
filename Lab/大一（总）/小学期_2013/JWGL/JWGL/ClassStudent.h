//bool变量未初始化时为1
#pragma once
#include<fstream>
#include <afx.h>
#include <locale>
#include "ClassCourseAndMessage.h"
using namespace std;

struct student:public CourseAndMessage
{
  CString name;
  CString id;
  CString password;
  CString  telephone;
  CString sex;
  CString major;
  CString noclass;
  void setid(CString a)//设置学生信息
  {
	  id=a;
  };
  void setpassword(CString a)
  {
	  password=a;
  };
  void setname(CString a)
  {
	  name = a;
  };
  void settelephone(CString a)
  {
	  telephone=a;
  };
  void setsex(CString a)
  {
	  sex=a;
  };
  void setmajor(CString a){
      major = a;
  };
  void setnoclass(CString a){
      noclass =a;
  };
  void setinformation(CString sid,CString spw,CString sname,CString stp,CString ssex,CString smj,CString scl){
      setid(sid);
	  setpassword(spw);
	  setname(sname);
	  settelephone(stp);
	  setsex(ssex);
	  setmajor(smj);
	  setnoclass(scl);
  };
  CString getid(){return id;};//获取学生信息
  CString getpassword(){return password;};
  CString getname(){return name;};
  CString getsex(){return sex;};
  CString gettelephone(){return telephone;};
  CString getmajor(){return major;};
  CString getnoclass(){return noclass;};
void out()//写入学生文件(增加学生用户)
{
	CString space,huiche;
	space = " ";
	huiche = '\n';
	CStdioFile file; 
	if(file.Open(_T("student.txt"),CFile::modeWrite|CFile::typeText) ){
	    setlocale( LC_CTYPE, "chs" );
		file.SeekToEnd();
	    file.WriteString(id);
		file.WriteString(space);
	    file.WriteString(password);
		file.WriteString(space);
	    file.WriteString(name);
		file.WriteString(space);
	    file.WriteString(sex);
		file.WriteString(space);
	    file.WriteString(telephone); 
		file.WriteString(space);
		file.WriteString(major); 
		file.WriteString(space);
		file.WriteString(noclass);
		file.WriteString(space);
		file.WriteString(huiche);
	    file.Close();
	}
};
void in(){//读取文件
	CString all;
	int a;
	CStdioFile file2; 
	if(file2.Open(_T("student.txt"),CFile::modeRead|CFile::typeText)){
		setlocale( LC_CTYPE, "chs" );
		file2.ReadString(all);
		for(int i = 0;i < 7;i++){
		a = all.Find(' ');
		if( a>= 0){
			if(i == 0){
				id = all.Left(a);
				all = all.Right(all.GetLength()-a-1);
			}
		    if(i == 1){
				password = all.Left(a);
				all = all.Right(all.GetLength()-a-1);
			}
		    if(i == 2){
				name = all.Left(a);
				all = all.Right(all.GetLength()-a-1);
			}
		    if(i == 3){
				sex = all.Left(a);
				all = all.Right(all.GetLength()-a-1);
			}
		    if(i == 4){
				telephone = all.Left(a);
				all = all.Right(all.GetLength()-a-1);
			}
			if(i == 5){
				major = all.Left(a);
				all = all.Right(all.GetLength()-a-1);
			}
			if(i == 6){
				noclass = all.Left(a);
				all = all.Right(all.GetLength()-a-1);
			}
		}
		}
		file2.Close();
	}
};
bool serch(CString idword){//精确查找
	 CStdioFile myfile;
	 if(myfile.Open(_T("student.txt"),CFile::modeRead|CFile::typeText)){
		 setlocale(LC_CTYPE,"chs");
		 CString str1;
		 int j,b,c=0;
		 bool d;
		 for(;;){
			d = myfile.ReadString(str1);
        	j=str1.Find(idword);
	        if(j!=-1){
				for(int i = 0;i < 7;i++){
					b = str1.Find(' ');
					if( b>= 0){
						if(i == 0){
							id = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 1){
			    			password = str1.Left(b);
							str1= str1.Right(str1.GetLength()-b-1);
						}
						if(i == 2){
							name = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 3){
							sex = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 4){
							telephone = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 5){
							major = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 6){
							noclass = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
					}
				}
				if(idword == id ||idword == name){
					myfile.Close();
					return 1;
					break;
				}
	        }
			if (d == 0){
				break;
			}
		 }
		 myfile.Close();
		 return 0;
	 }
	 else{
		return 0;
	 }
};
bool fuzzyserch(student *sdarray,CString idnumber){//模糊查找
    CStdioFile myfile;
	 if(myfile.Open(_T("student.txt"),CFile::modeRead|CFile::typeText)){
		 setlocale(LC_CTYPE,"chs");
		 CString str1;
		 int j,b,c = 0,d = 0;
		 bool e;
		 for(;;){
			e = myfile.ReadString(str1);
        	j=str1.Find(idnumber);
	        if(j!=-1){
				c = 1;
				for(int i = 0;i < 7;i++){
					b = str1.Find(' ');
					if( b>= 0){
						if(i == 0){
							sdarray[d].id = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 1){
			    			sdarray[d].password = str1.Left(b);
							str1= str1.Right(str1.GetLength()-b-1);
						}
						if(i == 2){
							sdarray[d].name = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 3){
							sdarray[d].sex = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 4){
							sdarray[d].telephone = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 5){
							sdarray[d].major = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 6){
							sdarray[d].noclass = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
					}
				}
				d++;
	        }
			if(e == 0){
			    break;
			}
		 }
		 if(c == 0){
			 myfile.Close();
			 return 0;
		 }
		 else{
			 myfile.Close();
			 return 1;
		 }
	 }
	 else{
		return 0;
	 }
};
bool deletestudent(CString oldid){//删除学生
	CString arraysd[200];
	int j,b,c=0;
	int q,p = 0;
	bool d;
	CStdioFile myfile;
	if(myfile.Open(_T("student.txt"),CFile::modeRead|CFile::typeText)){
		 setlocale(LC_CTYPE,"chs");
		 for(;p<200;p++){
			d = myfile.ReadString(arraysd[p]);
        	j=arraysd[p].Find(oldid);
	        if(j!=-1){
				for(int i = 0;i < 7;i++){
					b = arraysd[p].Find(' ');
					if( b>= 0){
						if(i == 0){
							id = arraysd[p].Left(b);
							arraysd[p] = arraysd[p].Right(arraysd[p].GetLength()-b-1);
						}
						if(i == 1){
			    			password = arraysd[p].Left(b);
							arraysd[p]= arraysd[p].Right(arraysd[p].GetLength()-b-1);
						}
						if(i == 2){
							name = arraysd[p].Left(b);
							arraysd[p] = arraysd[p].Right(arraysd[p].GetLength()-b-1);
						}
						if(i == 3){
							sex = arraysd[p].Left(b);
							arraysd[p] = arraysd[p].Right(arraysd[p].GetLength()-b-1);
						}
						if(i == 4){
							telephone = arraysd[p].Left(b);
							arraysd[p] = arraysd[p].Right(arraysd[p].GetLength()-b-1);
						}
						if(i == 5){
							major = arraysd[p].Left(b);
							arraysd[p] = arraysd[p].Right(arraysd[p].GetLength()-b-1);
						}
						if(i == 6){
							noclass = arraysd[p].Left(b);
							arraysd[p] = arraysd[p].Right(arraysd[p].GetLength()-b-1);
						}
					}
				}
				if(oldid == id){
					c = 1;
					q = p;
				}
	        }
			if (d == 0){
				break;
			}
		 }
		 myfile.Close();
	 }
	 if(c == 1){
		 CStdioFile myfile2;
		 myfile2.Open(_T("student.txt"),CFile::modeCreate|CFile::modeWrite|CFile::typeText);
		 setlocale(LC_CTYPE,"chs");
		 for(int n=0;n<p;n++){
			 if(n!=q){
			 myfile2.WriteString(arraysd[n]);
			 myfile2.WriteString(_T("\n"));
			 }
		 }
		 return 1;
	 }
	 else {
		 return 0;
	 }
};
bool modifyinformation(CString oldid,CString newid,CString newpass,CString newname,CString newsex,CString newphone,CString newmajor,CString newclass){//修改学生信息
	bool a;
	a = deletestudent(oldid);
	if(a){
		student b;
		b.setinformation(newid,newpass,newname,newphone,newsex,newmajor,newclass);
	    b.out();
		return 1;
	}
	else{
		return 0;
	}
};
void getallstudent(CString *studentid){//得到所有学生的ID
	bool end;
	int b;
	CString str;
	CStdioFile myfile;
	if(myfile.Open(_T("student.txt"),CFile::modeRead|CFile::typeText)){
	    setlocale(LC_CTYPE,"chs");
		for(int i = 0;i<400;i++){
			end = myfile.ReadString(str);
			b = str.Find(' ');
			studentid[i] = str.Left(b);
			if(end == 0){
				break;
			}
		}
		myfile.Close();
	}
}

};