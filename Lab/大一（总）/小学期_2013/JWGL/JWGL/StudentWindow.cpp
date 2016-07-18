// StudentWindow.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "StudentWindow.h"


// StudentWindow

IMPLEMENT_DYNAMIC(StudentWindow, CPropertySheet)

StudentWindow::StudentWindow(UINT nIDCaption, CWnd* pParentWnd, UINT iSelectPage)
	:CPropertySheet(nIDCaption, pParentWnd, iSelectPage)
{
	this->m_psh.dwFlags |= PSH_NOAPPLYNOW;
	this->m_psh.dwFlags &= ~(PSH_HASHELP);
	m_sdpage1.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_sdpage2.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_sdpage3.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_sdpage4.m_psp.dwFlags &= ~(PSP_HASHELP);
	AddPage(&m_sdpage1);
	AddPage(&m_sdpage2);
	AddPage(&m_sdpage3);
	AddPage(&m_sdpage4);
}

StudentWindow::StudentWindow(LPCTSTR pszCaption, CWnd* pParentWnd, UINT iSelectPage)
	:CPropertySheet(pszCaption, pParentWnd, iSelectPage)
{
	this->m_psh.dwFlags |= PSH_NOAPPLYNOW;
	this->m_psh.dwFlags &= ~(PSH_HASHELP);
	m_sdpage1.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_sdpage2.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_sdpage3.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_sdpage4.m_psp.dwFlags &= ~(PSP_HASHELP);
	AddPage(&m_sdpage1);
	AddPage(&m_sdpage2);
	AddPage(&m_sdpage3);
	AddPage(&m_sdpage4);
}

StudentWindow::~StudentWindow()
{
}


BEGIN_MESSAGE_MAP(StudentWindow, CPropertySheet)
END_MESSAGE_MAP()


// StudentWindow 消息处理程序
