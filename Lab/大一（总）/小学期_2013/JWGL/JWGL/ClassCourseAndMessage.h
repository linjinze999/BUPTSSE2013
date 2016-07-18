#pragma once
#include <afx.h>
#include <locale>
using namespace std;


struct CourseAndMessage{
	void SDChooseCourse(CString classname,CString sdname){//学生选课
		int j,b,k,c=0;
		CString str1,str2,str3,str4,str5;
		bool end;
		CStdioFile myfile,myfile2;
		if(myfile.Open(_T("courseandmessage.txt"),CFile::modeReadWrite|CFile::typeText)){
		    setlocale(LC_CTYPE,"chs");
			for(;;){
			    end = myfile.ReadString(str1);
				j = str1.Find(classname);
				if(j != -1){
					b = str1.Find(' ');
					if(b>=0){
						str2 = str1.Left(b);
						if(str2 == classname){
							str3 = str1.Right(str1.GetLength()-b-1);
							c=1;
							str4 = str1;
						}
					}
				}
				if(end == 0){
				    break;
				}
			}
			myfile.Close();
		}
		if(c==1){
			k = str3.Find(' ');
			str3 = str3.Right(str3.GetLength()-k-1);
			k = str3.Find(' ');
			str3 = str3.Left(k);
			TCDeleteCourse(classname,str3);
			if(myfile2.Open(_T("courseandmessage.txt"),CFile::modeReadWrite|CFile::typeText)){
				myfile2.SeekToEnd();
				myfile2.WriteString(str4);
				myfile2.WriteString(sdname);
				myfile2.WriteString(_T(" "));
				myfile2.WriteString(_T("0"));
				myfile2.WriteString(_T(" "));
				myfile2.WriteString(_T("\n"));
				myfile2.Close();
			}
		}
	}
	void TCAddCourse(CString classname,CString mmessage,CString tcname){//老师增加课程
		CString space,huiche;
		space = " ";
		huiche = "\n";
		CStdioFile file; 
		if(file.Open(_T("courseandmessage.txt"),CFile::modeWrite|CFile::typeText) ){
			setlocale( LC_CTYPE, "chs" );
			file.SeekToEnd();
			if(_T("") == mmessage){
			    file.WriteString(classname);
			    file.WriteString(_T(" "));
			    file.WriteString(_T("0"));
			    file.WriteString(_T(" "));
			    file.WriteString(tcname);
			    file.WriteString(_T(" "));
			    file.WriteString(huiche);
			}
			else{
			    file.WriteString(classname);
			    file.WriteString(_T(" "));
			    file.WriteString(mmessage);
			    file.WriteString(_T(" "));
			    file.WriteString(tcname);
			    file.WriteString(_T(" "));
			    file.WriteString(huiche);
			}
			file.Close();
			TCAddMessage(classname,tcname);
	    }
	}
	bool TCDeleteCourse(CString classname,CString tcname){//老师删除课程
		CString arraysd[200],str,str1;
		int j,b,c=0;
		int q,p = 0;
		bool d;
		CStdioFile myfile;
		if(myfile.Open(_T("courseandmessage.txt"),CFile::modeRead|CFile::typeText)){
			 setlocale(LC_CTYPE,"chs");
			 for(;p<200;p++){
				d = myfile.ReadString(arraysd[p]);
        		j=arraysd[p].Find(classname);
				if(j!=-1){
					b = arraysd[p].Find(' ');
					str = arraysd[p].Left(b);
					if(str == classname){
						arraysd[p] = arraysd[p].Right(arraysd[p].GetLength()-b-1);
						b = arraysd[p].Find(' ');
						arraysd[p] = arraysd[p].Right(arraysd[p].GetLength()-b-1);
						b = arraysd[p].Find(' ');
						str1 = arraysd[p].Left(b);
						if(str1 == tcname){
						    c = 1;
						    q = p;
						}
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
			 myfile2.Open(_T("courseandmessage.txt"),CFile::modeCreate|CFile::modeWrite|CFile::typeText);
			 setlocale(LC_CTYPE,"chs");
			 for(int n=0;n<p;n++){
				 if(n!=q){
				 myfile2.WriteString(arraysd[n]);
				 myfile2.WriteString(_T("\n"));
				 }
			 }
			 myfile2.Close();
			 TCDeleteMessage(classname);
			 return 1;
		 }
		 else {
			 return 0;
		 }
	}
	bool TCMessageForStudent(CString classname,CString mmessage){//老师给学生发留言
		CString arraysd[200],str1,str2,str3;
		int j,b,c=0;
		int q,p = 0;
		bool d;
		CStdioFile myfile;
		if(myfile.Open(_T("courseandmessage.txt"),CFile::modeRead|CFile::typeText)){
			 setlocale(LC_CTYPE,"chs");
			 for(p = 0;p<200;p++){
				d = myfile.ReadString(arraysd[p]);
        		j=arraysd[p].Find(classname);
				if(j!=-1){
					b = arraysd[p].Find(' ');
					str1 = arraysd[p].Left(b);
					str2 = arraysd[p].Right(arraysd[p].GetLength()-b-1);
					if(str1 == classname){
						b = str2.Find(' ');
					    str3 = str2.Right(str2.GetLength()-b-1);
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
			 myfile2.Open(_T("courseandmessage.txt"),CFile::modeCreate|CFile::modeWrite|CFile::typeText);
			 setlocale(LC_CTYPE,"chs");
			 for(int n=0;n<p;n++){
				 if(n!=q){
			    	 myfile2.WriteString(arraysd[n]);
				     myfile2.WriteString(_T("\n"));
				 }
				 else{
				     myfile2.WriteString(classname);
					 myfile2.WriteString(_T(" "));
					 myfile2.WriteString(mmessage);
					 myfile2.WriteString(_T(" "));
					 myfile2.WriteString(str3);
					 myfile2.WriteString(_T("\n"));
				 }
			 }
			 return 1;
		 }
		 else {
			 return 0;
		 }
	}
	bool setscore(CString classname,CString sdname,CString score){//老师给学生赋成绩
	    CString arraysd[200],str1,str2,str3,str4,str5;
		int j,b,h,k,l,c=0;
		int q,p = 0;
		bool d;
		CStdioFile myfile;
		if(myfile.Open(_T("courseandmessage.txt"),CFile::modeRead|CFile::typeText)){
			 setlocale(LC_CTYPE,"chs");
			 for(;p<200;p++){
				d = myfile.ReadString(arraysd[p]);
        		j=arraysd[p].Find(classname);
				if(j!=-1){
					b = arraysd[p].Find(' ');
					str1 = arraysd[p].Left(b);
					if(str1 == classname){
						h = arraysd[p].Find(sdname);
						if(h!=-1){
						    str2 = arraysd[p].Left(h);//名字之前的字符串
						    str3 = arraysd[p].Right(arraysd[p].GetLength()-h);
							l = str3.Find(' ');
							str5 = str3.Left(l);
							str3 = str3.Right(str3.GetLength()-l-1);
							if(sdname == str5){
						        k = str3.Find(' ');
					    	    str4 = str3.Right(str3.GetLength()-k-1);//成绩之后的字符串
					    	    c = 1;
						        q = p;
							}
							else{
							    return 0;
							}
						}
						else{
						    return 0;
						}
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
			 myfile2.Open(_T("courseandmessage.txt"),CFile::modeCreate|CFile::modeWrite|CFile::typeText);
			 setlocale(LC_CTYPE,"chs");
			 for(int n=0;n<p;n++){
				 if(n!=q){
			    	 myfile2.WriteString(arraysd[n]);
				     myfile2.WriteString(_T("\n"));
				 }
				 else{
					 myfile2.WriteString(str2);
				     myfile2.WriteString(sdname);
					 myfile2.WriteString(_T(" "));
					 myfile2.WriteString(score);
					 myfile2.WriteString(_T(" "));
					 myfile2.WriteString(str4);
					 myfile2.WriteString(_T("\n"));
				 }
			 }
			 return 1;
		 }
		 else {
			 return 0;
		 }
	}
	CString GetScoreMessage(CString classname){//得到开设课程的老师的留言
		CString str,str1,str2;
		int j,b,c=0;
		int p = 0;
		bool d;
		CStdioFile myfile;
		if(myfile.Open(_T("courseandmessage.txt"),CFile::modeRead|CFile::typeText)){
			 setlocale(LC_CTYPE,"chs");
			 for(;p<200;p++){
				d = myfile.ReadString(str);
        		j=str.Find(classname);
				if(j!=-1){
					b = str.Find(' ');
					str1 = str.Left(b);
					if(str1 == classname){
						str = str.Right(str.GetLength()-b-1);
						b = str.Find(' ');
						str2 = str.Left(b);
						c = 1;
					}
				}
				if (d == 0){
					break;
				}
			 }
			 myfile.Close();
		 }
		if(c == 1){
		    return str2;
		}
		else {
		    return _T("0");
		}
	}
	void getyourcourse(CString yourcourse[],CString yourname){//得到你所拥有的课程
	    CString str;
		int j,b;
		int p = 0,q = 0;
		bool d;
		CStdioFile myfile;
		if(myfile.Open(_T("courseandmessage.txt"),CFile::modeRead|CFile::typeText)){
			 setlocale(LC_CTYPE,"chs");
			 for(;p<200;p++){
				d = myfile.ReadString(str);
        		j=str.Find(yourname);
				if(j!=-1){
					b = str.Find(' ');
					yourcourse[q] = str.Left(b);
					q++;
					}
				if (d == 0){
					break;
				}
			 }
			 myfile.Close();
		 }
	}
	bool SDDeleteCourse(CString classname,CString sdname){//学生退课
	    CString arraysd[200],str1,str2,str3,str4,str5;
		int j,b,h,k,l,c=0;
		int q,p = 0;
		bool d;
		CStdioFile myfile;
		if(myfile.Open(_T("courseandmessage.txt"),CFile::modeRead|CFile::typeText)){
			 setlocale(LC_CTYPE,"chs");
			 for(;p<200;p++){
				d = myfile.ReadString(arraysd[p]);
        		j=arraysd[p].Find(classname);
				if(j!=-1){
					b = arraysd[p].Find(' ');
					str1 = arraysd[p].Left(b);
					if(str1 == classname){
						h = arraysd[p].Find(sdname);
						if(h!=-1){
						    str2 = arraysd[p].Left(h);//名字之前的字符串
						    str3 = arraysd[p].Right(arraysd[p].GetLength()-h);
							l = str3.Find(' ');
							str5 = str3.Left(l);
							str3 = str3.Right(str3.GetLength()-l-1);
							if(sdname == str5){
						        k = str3.Find(' ');
					    	    str4 = str3.Right(str3.GetLength()-k-1);//成绩之后的字符串
					    	    c = 1;
						        q = p;
							}
						}
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
			 myfile2.Open(_T("courseandmessage.txt"),CFile::modeCreate|CFile::modeWrite|CFile::typeText);
			 setlocale(LC_CTYPE,"chs");
			 for(int n=0;n<=p;n++){
				 if(n!=q){
			    	 myfile2.WriteString(arraysd[n]);
				     myfile2.WriteString(_T("\n"));
				 }
				 else{
					 myfile2.WriteString(str2);
					 myfile2.WriteString(str4);
					 myfile2.WriteString(_T("\n"));
				 }
			 }
			 return 1;
		 }
		 else {
			 return 0;
		 }
	}
	CString getsdscore(CString classname,CString sdname){//得到学生成绩
	    CString str,str1,str2,str3;
		int j,b,k,c=0;
		int p = 0;
		bool d;
		CStdioFile myfile;
		if(myfile.Open(_T("courseandmessage.txt"),CFile::modeRead|CFile::typeText)){
			 setlocale(LC_CTYPE,"chs");
			 for(;p<200;p++){
				d = myfile.ReadString(str);
        		j=str.Find(classname);
				if(j!=-1){
					b = str.Find(' ');
					str1 = str.Left(b);
					if(str1 == classname){
						k = str.Find(sdname);
						str = str.Right(str.GetLength()-k);
						k = str.Find(' ');
						str3 = str.Left(k);
						if(str3 == sdname){
							str = str.Right(str.GetLength()-k-1);
						    k = str.Find(' ');
						    str2 = str.Left(k);
						    c = 1;
						}
					}
				}
				if (d == 0){
					break;
				}
			 }
			 myfile.Close();
		 }
		if(c == 1){
		    return str2;
		}
		else {
		    return _T("0");
		}
	}
	void getallcourse(CString allcourse[]){//得到所有课程的名字
		CString str;
		int b;
		bool end;
	    CStdioFile myfile;
		if(myfile.Open(_T("courseandmessage.txt"),CFile::modeRead|CFile::typeText)){
			for(int i=0;i<200;i++){
				end = myfile.ReadString(str);
				b = str.Find(' ');
				allcourse[i] = str.Left(b);
				if(end == 0){
					break;
				}
			}
			myfile.Close();
		}
	}
	void StudentAddMessage(CString classname,CString message){//学生给老师留言
		int p,q,j,b,c=0;
		bool d;
		CString str[200];
		CString str1;
	    CStdioFile myfile;
		if(myfile.Open(_T("SDMessageForTC.txt"),CFile::modeRead|CFile::typeText)){
			setlocale(LC_CTYPE,"chs");
			for(p = 0;p<200;p++){
				d = myfile.ReadString(str[p]);
				j = str[p].Find(classname);
				if(j!=-1){
					b = str[p].Find(' ');
					str1 = str[p].Left(b);
					if(str1 == classname){
					    c = 1;
						q = p;
					}
				}
				if(d == 0){break;}
			}
			myfile.Close();
		}
		if(c == 1){
		    CStdioFile myfile2;
			myfile2.Open(_T("SDMessageForTC.txt"),CFile::modeCreate|CFile::modeWrite|CFile::typeText);
			setlocale(LC_CTYPE,"chs");
			for(int n=0;n<p;n++){
				 if(n!=q){
				     myfile2.WriteString(str[n]);
				     myfile2.WriteString(_T("\n"));
				 }
				 else{
				     myfile2.WriteString(str[n]);
					 myfile2.WriteString(message);
					 myfile2.WriteString(_T(" "));
					 myfile2.WriteString(_T("\n"));
				 }
			}
			myfile2.Close();
		}

	};
	bool TCDeleteMessage(CString classname){//删除该课程的学生给老师留言
		CString arraysd[200],str,str1;
		int j,b,c=0;
		int q,p = 0;
		bool d;
		CStdioFile myfile;
		if(myfile.Open(_T("SDMessageForTC.txt"),CFile::modeRead|CFile::typeText)){
			 setlocale(LC_CTYPE,"chs");
			 for(;p<200;p++){
				d = myfile.ReadString(arraysd[p]);
        		j=arraysd[p].Find(classname);
				if(j!=-1){
					b = arraysd[p].Find(' ');
					str = arraysd[p].Left(b);
					if(str == classname){
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
			 myfile2.Open(_T("SDMessageForTC.txt"),CFile::modeCreate|CFile::modeWrite|CFile::typeText);
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
	void TCAddMessage(CString classname,CString tcname){//老师增加课程后创建学生留言
	    CString huiche;
		huiche = "\n";
		CStdioFile file; 
		if(file.Open(_T("SDMessageForTC.txt"),CFile::modeWrite|CFile::typeText) ){
			setlocale( LC_CTYPE, "chs" );
			file.SeekToEnd();
			file.WriteString(classname);
			file.WriteString(_T(" "));
			file.WriteString(tcname);
			file.WriteString(_T(" "));
			file.WriteString(huiche);
			file.Close();
	    }
	}
	void TCGetSDMessage(CString *message,CString classname){//老师得到学生留言
		bool d;
		int j,b,k;
		CString str,str1;
	    CStdioFile myfile; 
		if(myfile.Open(_T("SDMessageForTC.txt"),CFile::modeRead|CFile::typeText)){
		    setlocale( LC_CTYPE, "chs" );
			for(int p = 0;p<200;p++){
				d = myfile.ReadString(str);
				j = str.Find(classname);
				if(j != -1){
					b = str.Find(' ');
					str1 = str.Left(b);
					if(str1 == classname){
						str = str.Right(str.GetLength()-b-1);
						b = str.Find(' ');
						str = str.Right(str.GetLength()-b-1);
						for(int i = 0;i<200;i++){
							k=str.Find(' ');
							message[i] = str.Left(k);
							str = str.Right(str.GetLength()-k-1);
							if(k == -1){break;}
						}
					}
				}
			}
			myfile.Close();
		}
	}
	void GetTheCourseStudent(CString *students,CString classname){//得到拥有该课程的学生
	    bool d;
		int j,b,k;
		CString str,str1;
	    CStdioFile myfile; 
		if(myfile.Open(_T("courseandmessage.txt"),CFile::modeRead|CFile::typeText)){
		    setlocale( LC_CTYPE, "chs" );
			for(int p = 0;p<200;p++){
				d = myfile.ReadString(str);
				j = str.Find(classname);
				if(j != -1){
					b = str.Find(' ');
					str1 = str.Left(b);
					if(str1 == classname){
						str = str.Right(str.GetLength()-b-1);
						b = str.Find(' ');
						str = str.Right(str.GetLength()-b-1);
						b = str.Find(' ');
						str = str.Right(str.GetLength()-b-1);
						for(int i = 0;i<400;i++){
							k=str.Find(' ');
							students[i] = str.Left(k);
							str = str.Right(str.GetLength()-k-1);
							k=str.Find(' ');
							str = str.Right(str.GetLength()-k-1);
							if(k == -1){break;}
						}
					}
				}
			}
			myfile.Close();
		}
	}
};