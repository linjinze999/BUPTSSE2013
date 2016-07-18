// BGPage1.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGPage1.h"
#include "afxdialogex.h"
#include "BGAddTeacherUser.h"
#include "BGAddStudentUser.h"


// BGPage1 对话框

IMPLEMENT_DYNAMIC(BGPage1, CPropertyPage)

BGPage1::BGPage1()
	: CPropertyPage(BGPage1::IDD)
{

}

BGPage1::~BGPage1()
{
}

void BGPage1::DoDataExchange(CDataExchange* pDX)
{
	CPropertyPage::DoDataExchange(pDX);
}


BEGIN_MESSAGE_MAP(BGPage1, CPropertyPage)
	ON_BN_CLICKED(IDC_BUTTON2, &BGPage1::OnBnClickedButton2)
	ON_BN_CLICKED(IDC_BUTTON1, &BGPage1::OnBnClickedButton1)
	ON_WM_PAINT()
END_MESSAGE_MAP()


// BGPage1 消息处理程序


void BGPage1::OnBnClickedButton2()
{
	// TODO: 在此添加控件通知处理程序代码
	CBGAddTeacherUser atu;
	atu.DoModal();
}


void BGPage1::OnBnClickedButton1()
{
	// TODO: 在此添加控件通知处理程序代码
	CBGAddStudentUser asu;
	asu.DoModal();
}


void BGPage1::OnPaint()
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
