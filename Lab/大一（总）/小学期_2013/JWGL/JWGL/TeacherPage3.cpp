// TeacherPage3.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "TeacherPage3.h"
#include "afxdialogex.h"
#include "StudentMessageForTeacherWindow.h"
#include "TeacherMessageForStudentWindow.h"


// TeacherPage3 对话框

IMPLEMENT_DYNAMIC(TeacherPage3, CPropertyPage)

TeacherPage3::TeacherPage3()
	: CPropertyPage(TeacherPage3::IDD)
{

}

TeacherPage3::~TeacherPage3()
{
}

void TeacherPage3::DoDataExchange(CDataExchange* pDX)
{
	CPropertyPage::DoDataExchange(pDX);
}


BEGIN_MESSAGE_MAP(TeacherPage3, CPropertyPage)
	ON_BN_CLICKED(IDC_BUTTON1, &TeacherPage3::OnBnClickedButton1)
	ON_BN_CLICKED(IDC_BUTTON2, &TeacherPage3::OnBnClickedButton2)
	ON_WM_PAINT()
END_MESSAGE_MAP()


// TeacherPage3 消息处理程序


void TeacherPage3::OnBnClickedButton1()
{
	// TODO: 在此添加控件通知处理程序代码
	StudentMessageForTeacherWindow smftw;
	smftw.tcname = message.getname();
	smftw.DoModal();
}


void TeacherPage3::OnBnClickedButton2()
{
	// TODO: 在此添加控件通知处理程序代码
	TeacherMessageForStudentWindow tmfsw;
	tmfsw.m_TCMessageForSD = message.getname();
	tmfsw.DoModal();
}


void TeacherPage3::OnPaint()
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
