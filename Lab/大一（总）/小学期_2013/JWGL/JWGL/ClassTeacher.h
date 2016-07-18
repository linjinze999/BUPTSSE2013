#pragma once
#include<fstream>
#include <afx.h>
#include <locale>
#include "ClassCourseAndMessage.h"
using namespace std;

struct teacher:public CourseAndMessage
{
	CString name;
	CString id;
	CString password;
	CString telephone;
	CString sex;
	void setid(CString a){//设置老师信息
	    id = a;
	};
	 void setsex(CString a)
     {
	    sex=a;
     }
	void setname(CString a)
     {
	    name = a;
     }
	 void setpassword(CString a)
     {
	    password = a;
     }
    void settelephone(CString a)
     {
	    telephone = a;
     }
	void setinformation(CString sid,CString spw,CString sname,CString stp,CString ssex){
      setid(sid);
	  setpassword(spw);
	  setname(sname);
	  settelephone(stp);
	  setsex(ssex);
  };
	CString getid(){return id;}//获取老师信息
  CString getpassword(){return password;}
  CString getname(){return name;}
  CString getsex(){return sex;}
  CString gettelephone(){return telephone;}
  void out()//写入老师文件
{
	CString space,huiche;
	space = " ";
	huiche = '\n';
	CStdioFile file; 
	if(file.Open(_T("teacher.txt"),CFile::modeWrite|CFile::typeText) ){
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
		file.WriteString(huiche);
	    file.Close();
	}
	else {
	    AfxMessageBox(_T("写进文件失败"));
	}
};
void in(){//读取文件
	CString all;
	int a;
	CStdioFile file2; 
	if(file2.Open(_T("teacher.txt"),CFile::modeRead|CFile::typeText)){
		setlocale( LC_CTYPE, "chs" );
		file2.ReadString(all);
		for(int i = 0;i < 5;i++){
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
		}
		}
		file2.Close();
	}
	else {
	    AfxMessageBox(_T("读取文件失败"));
	}
};
bool serch(CString keyword){//关键字查询教师文件
	 CStdioFile myfile;
	 if(myfile.Open(_T("teacher.txt"),CFile::modeRead|CFile::typeText)){
		 setlocale(LC_CTYPE,"chs");
		 CString str1;
		 int j,b;
		 bool c;
		 for(;;){
			c = myfile.ReadString(str1);
        	j=str1.Find(keyword);
	        if(j!=-1){
				for(int i = 0;i < 5;i++){
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
					}
				}
				if(keyword == id){
				    return 1;
					break;
				}
	        }
			if(c == 0){
			    return 0;
				break;
			}
		 }
			 
	 }
	 else{
		return 0;
	 }
	 myfile.Close();
    
};
bool fuzzyserch(teacher *tcarray,CString idnumber){//模糊查找
    CStdioFile myfile;
	 if(myfile.Open(_T("teacher.txt"),CFile::modeRead|CFile::typeText)){
		 setlocale(LC_CTYPE,"chs");
		 CString str1;
		 int j,b,c = 0,d = 0;
		 bool e;
		 for(;;){
			e = myfile.ReadString(str1);
        	j=str1.Find(idnumber);
	        if(j!=-1){
				c = 1;
				for(int i = 0;i < 5;i++){
					b = str1.Find(' ');
					if( b>= 0){
						if(i == 0){
							tcarray[d].id = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 1){
			    			tcarray[d].password = str1.Left(b);
							str1= str1.Right(str1.GetLength()-b-1);
						}
						if(i == 2){
							tcarray[d].name = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 3){
							tcarray[d].sex = str1.Left(b);
							str1 = str1.Right(str1.GetLength()-b-1);
						}
						if(i == 4){
							tcarray[d].telephone = str1.Left(b);
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
			 return 0;
		 }
		 else{
			 return 1;
		 }
	 }
	 else{
		return 0;
	 }
	 myfile.Close();
};
bool deleteteacher(CString oldid){//删除老师
	CString arraysd[200];
	int j,b,c=0;
	int q,p = 0;
	bool d;
	CStdioFile myfile;
	if(myfile.Open(_T("teacher.txt"),CFile::modeRead|CFile::typeText)){
		 setlocale(LC_CTYPE,"chs");
		 for(;p<200;p++){
			d = myfile.ReadString(arraysd[p]);
        	j=arraysd[p].Find(oldid);
	        if(j!=-1){
				for(int i = 0;i < 5;i++){
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
		 myfile2.Open(_T("teacher.txt"),CFile::modeCreate|CFile::modeWrite|CFile::typeText);
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
bool modifyinformation(CString oldid,CString newid,CString newpass,CString newname,CString newsex,CString newphone){//修改老师信息
	bool a;
	a = deleteteacher(oldid);
	if(a){
		teacher b;
		b.setinformation(newid,newpass,newname,newphone,newsex);
	    b.out();
		return 1;
	}
	else{
		return 0;
	}
};

};