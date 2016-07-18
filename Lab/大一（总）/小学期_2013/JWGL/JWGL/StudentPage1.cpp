// StudentPage1.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "StudentPage1.h"
#include "afxdialogex.h"
#include "SDModifySDPassword.h"
#include "ClassStudent.h"

// StudentPage1 对话框

IMPLEMENT_DYNAMIC(StudentPage1, CPropertyPage)

StudentPage1::StudentPage1()
	: CPropertyPage(StudentPage1::IDD)
	, m_SDIFMTName(_T(""))
	, m_SDIFMTID(_T(""))
	, m_SDIFMTSex(_T(""))
	, m_SDIFMTTelephone(_T(""))
	, m_SDIFMTMajor(_T(""))
	, m_SDIFMTNoclass(_T(""))
	, m_SDIFMTPassword(_T(""))
{
	
}

StudentPage1::~StudentPage1()
{
}

void StudentPage1::DoDataExchange(CDataExchange* pDX)
{
	CPropertyPage::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_SDIFMTName);
	DDX_Text(pDX, IDC_EDIT4, m_SDIFMTID);
	DDX_Text(pDX, IDC_EDIT3, m_SDIFMTSex);
	DDX_Text(pDX, IDC_EDIT2, m_SDIFMTTelephone);

	DDX_Text(pDX, IDC_EDIT5, m_SDIFMTMajor);
	DDX_Text(pDX, IDC_EDIT6, m_SDIFMTNoclass);
	DDX_Text(pDX, IDC_EDIT7, m_SDIFMTPassword);
}


BEGIN_MESSAGE_MAP(StudentPage1, CPropertyPage)
	ON_EN_CHANGE(IDC_EDIT3, &StudentPage1::OnEnChangeEdit3)
	ON_EN_CHANGE(IDC_EDIT1, &StudentPage1::OnEnChangeEdit1)
	ON_BN_CLICKED(IDC_BUTTON1, &StudentPage1::OnBnClickedButton1)
	ON_WM_PAINT()
END_MESSAGE_MAP()


// StudentPage1 消息处理程序


void StudentPage1::OnEnChangeEdit3()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CPropertyPage::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
}


void StudentPage1::OnEnChangeEdit1()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CPropertyPage::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
}


void StudentPage1::OnBnClickedButton1()
{
	// TODO: 在此添加控件通知处理程序代码
	CSDModifySDPassword msdp;
	msdp.m_SDIFMTOld = m_SDIFMTPassword;
	msdp.modifypw.setinformation(m_SDIFMTID,m_SDIFMTPassword,m_SDIFMTName,m_SDIFMTTelephone,m_SDIFMTSex,m_SDIFMTMajor,m_SDIFMTNoclass);
	msdp.DoModal();
}


void StudentPage1::OnPaint()
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
