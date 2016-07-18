// TeacherPage4.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "TeacherPage4.h"
#include "afxdialogex.h"
#include "TeacherModifyPassword.h"
#include "ClassTeacher.h"

// CTeacherPage4 对话框

IMPLEMENT_DYNAMIC(CTeacherPage4, CPropertyPage)

CTeacherPage4::CTeacherPage4()
	: CPropertyPage(CTeacherPage4::IDD)
	, m_TCIFMTName(_T(""))
	, m_TCIFMTID(_T(""))
	, m_TCIFMTSex(_T(""))
	, m_TCIFMTTelephone(_T(""))
	, m_TCIFMTPassword(_T(""))
{

}

CTeacherPage4::~CTeacherPage4()
{
}

void CTeacherPage4::DoDataExchange(CDataExchange* pDX)
{
	CPropertyPage::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT4, m_TCIFMTName);
	DDX_Text(pDX, IDC_EDIT1, m_TCIFMTID);
	DDX_Text(pDX, IDC_EDIT5, m_TCIFMTSex);
	DDX_Text(pDX, IDC_EDIT2, m_TCIFMTTelephone);
	DDX_Text(pDX, IDC_EDIT3, m_TCIFMTPassword);
}


BEGIN_MESSAGE_MAP(CTeacherPage4, CPropertyPage)
	ON_BN_CLICKED(IDC_BUTTON1, &CTeacherPage4::OnBnClickedButton1)
	ON_WM_PAINT()
END_MESSAGE_MAP()


// CTeacherPage4 消息处理程序


void CTeacherPage4::OnBnClickedButton1()
{
	// TODO: 在此添加控件通知处理程序代码
	CTeacherModifyPassword tmp;
	tmp.m_TCModifyPWOld = m_TCIFMTPassword;
	tmp.modify.setinformation(m_TCIFMTID,m_TCIFMTPassword,m_TCIFMTName,m_TCIFMTTelephone,m_TCIFMTSex);
	tmp.DoModal();

}


void CTeacherPage4::OnPaint()
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
