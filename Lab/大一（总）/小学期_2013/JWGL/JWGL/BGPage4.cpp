// BGPage4.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGPage4.h"
#include "afxdialogex.h"
#include "ClassStudent.h"
#include "ClassTeacher.h"
#include "BGSearchSDFuzzy.h"
#include "BGSearchTCFuzzy.h"
#include "BGSeeStudentInformation.h"
#include "BGSeeTeacherInformation.h"


// BGPage4 对话框

int serchtype = 0;

IMPLEMENT_DYNAMIC(BGPage4, CPropertyPage)

BGPage4::BGPage4()
	: CPropertyPage(BGPage4::IDD)
	, m_BGSerchID(_T(""))
	, m_BGSerchKeyword(_T(""))
{

}

BGPage4::~BGPage4()
{
}

void BGPage4::DoDataExchange(CDataExchange* pDX)
{
	CPropertyPage::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_BGSerchID);
	DDX_Text(pDX, IDC_EDIT2, m_BGSerchKeyword);
}


BEGIN_MESSAGE_MAP(BGPage4, CPropertyPage)
	ON_BN_CLICKED(IDC_RADIO2, &BGPage4::OnBnClickedRadio2)
	ON_BN_CLICKED(IDC_RADIO1, &BGPage4::OnBnClickedRadio1)
	ON_BN_CLICKED(IDC_BUTTON2, &BGPage4::OnBnClickedButton2)
	ON_BN_CLICKED(IDC_BUTTON1, &BGPage4::OnBnClickedButton1)
	ON_WM_PAINT()
END_MESSAGE_MAP()


// BGPage4 消息处理程序


void BGPage4::OnBnClickedRadio2()
{
	// TODO: 在此添加控件通知处理程序代码
	serchtype = 2;
}


void BGPage4::OnBnClickedRadio1()
{
	// TODO: 在此添加控件通知处理程序代码
	serchtype = 1;
}


void BGPage4::OnBnClickedButton2()//后台——查询——查询学生
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	student stdt;
	bool serch;
	if(serchtype == 1)
	{
		if(m_BGSerchID == _T("")){
		     MessageBox(_T("请输入查找账号！"));
		}
		else{
			serch = stdt.serch(m_BGSerchID);
			if(serch){
		        CBGSeeStudentInformation sesdi;
				sesdi.m_BGSearchExactID = stdt.getid();
				sesdi.m_BGSearchExactPassword = stdt.getpassword();
				sesdi.m_BGSearchExactName = stdt.getname();
				sesdi.m_BGSearchExactSex = stdt.getsex();
				sesdi.m_BGSearchExactTelephone = stdt.gettelephone();
				sesdi.m_BGSearchExactMajor = stdt.getmajor();
				sesdi.m_BGSearchExactNoclass = stdt.getnoclass();
				sesdi.DoModal();
			}
			else{
				 MessageBox(_T("查无此人！"));
			}
		}
	}
	else{
	    if(serchtype == 2)
		{
			if(m_BGSerchKeyword == _T("")){
		        MessageBox(_T("请输入查找关键字！"));
		    }
			else{
				CBGSearchSDFuzzy bgssdf;
				serch = stdt.fuzzyserch(bgssdf.searchsdfuzzy,m_BGSerchKeyword);
				if(serch){
					bgssdf.DoModal();
				}
				else{
					 MessageBox(_T("查无此人！"));
				}
		    }
		}
		else{
		     MessageBox(_T("请选择查询类型！"));
		}
	}
}





void BGPage4::OnBnClickedButton1()//后台——查询——查询教师
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	teacher tch;;
	teacher tcharray[100];
	bool serch;
	if(serchtype == 1)
	{
		if(m_BGSerchID == _T("")){
		     MessageBox(_T("请输入查找账号！"));
		}
		else{
			serch = tch.serch(m_BGSerchID);
			if(serch){
		         CBGSeeTeacherInformation sti;
				 sti.m_BGSearchTeacherExactID = tch.getid();
				 sti.m_BGSearchTeacherExactPassword = tch.getpassword();
				 sti.m_BGSearchTeacherExactName = tch.getname();
				 sti.m_BGSearchTeacherExactSex = tch.getsex();
				 sti.m_BGSearchTeacherExactTelephone = tch.gettelephone();
				 sti.DoModal();
			}
			else{
				 MessageBox(_T("查无此人！"));
			}
		}
	}
	else{
	    if(serchtype == 2)
		{
			if(m_BGSerchKeyword == _T("")){
		        MessageBox(_T("请输入查找关键字！"));
		    }
			else{
				CBGSearchTCFuzzy bgstcf;
				serch = tch.fuzzyserch(bgstcf.searchtcfuzzy,m_BGSerchKeyword);
				if(serch){
					bgstcf.DoModal();
				}
				else{
					 MessageBox(_T("查无此人！"));
				}
		    }
		}
		else{
		     MessageBox(_T("请选择查询类型！"));
		}
	}
}


void BGPage4::OnPaint()
{
	CPaintDC dc(this); // device context for painting
	// TODO: 在此处添加消息处理程序代码
	CRect   rect;   
    GetClientRect(&rect);   
    CDC   dcMem;   
    dcMem.CreateCompatibleDC(&dc);   
    CBitmap   bmpBackground;   
    bmpBackground.LoadBitmap(IDB_BITMAP1);   
    BITMAP   bitmap;   
    bmpBackground.GetBitmap(&bitmap);   
    CBitmap   *pbmpOld=dcMem.SelectObject(&bmpBackground);   
    dc.StretchBlt(0,0,rect.Width(),rect.Height(),&dcMem,0,0,   
    bitmap.bmWidth,bitmap.bmHeight,SRCCOPY); 
	// 不为绘图消息调用 CPropertyPage::OnPaint()
}
