// BGPage2.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGPage2.h"
#include "afxdialogex.h"
#include "BGModifyTeacherInfoemation.h"
#include "BGModifyStudentInformation.h"


// BGPage2 对话框

IMPLEMENT_DYNAMIC(BGPage2, CPropertyPage)

BGPage2::BGPage2()
	: CPropertyPage(BGPage2::IDD)
	, m_BGModifyIFMTID(_T(""))
{

}

BGPage2::~BGPage2()
{
}

void BGPage2::DoDataExchange(CDataExchange* pDX)
{
	CPropertyPage::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_BGModifyIFMTID);
}


BEGIN_MESSAGE_MAP(BGPage2, CPropertyPage)
	ON_BN_CLICKED(IDC_BUTTON1, &BGPage2::OnBnClickedButton1)
	ON_BN_CLICKED(IDC_BUTTON2, &BGPage2::OnBnClickedButton2)
	ON_WM_PAINT()
END_MESSAGE_MAP()


// BGPage2 消息处理程序


void BGPage2::OnBnClickedButton1()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	CBGModifyTeacherInfoemation tcr;
	bool modifytc;
	modifytc = tcr.modifyinformation.serch(m_BGModifyIFMTID);
	if(modifytc){
		tcr.m_BGModifyTCID = tcr.modifyinformation.getid();
		tcr.m_BGModifyTCPassword = tcr.modifyinformation.getpassword();
		tcr.m_BGModifyTCName = tcr.modifyinformation.getname();
		tcr.m_BGModifyTCSex = tcr.modifyinformation.getsex();
		tcr.m_BGModifyTCTelephone = tcr.modifyinformation.gettelephone();
		tcr.DoModal();
	}
	else{
	    MessageBox(_T("查无此人！"));
	}
}


void BGPage2::OnBnClickedButton2()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	CBGModifyStudentInformation sdt;
	bool modifysdt;
	modifysdt = sdt.modifysd.serch(m_BGModifyIFMTID);
	if(modifysdt){
		sdt.m_BGModifySDID = sdt.modifysd.getid();
        sdt.m_BGModifySDPassword = sdt.modifysd.getpassword();
		sdt.m_BGModifySDName = sdt.modifysd.getname();
		sdt.m_BGModifySDSex = sdt.modifysd.getsex();
		sdt.m_BGModifySDTelephone = sdt.modifysd.gettelephone();
		sdt.m_BGModifySDMajor = sdt.modifysd.getmajor();
		sdt.m_BGModifySDClass = sdt.modifysd.getnoclass();
		sdt.DoModal();
	}
	else{
	    MessageBox(_T("查无此人！"));
	}
}


void BGPage2::OnPaint()
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
