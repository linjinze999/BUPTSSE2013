// TeacherWindow.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "TeacherWindow.h"


// TeacherWindow

IMPLEMENT_DYNAMIC(TeacherWindow, CPropertySheet)

TeacherWindow::TeacherWindow(UINT nIDCaption, CWnd* pParentWnd, UINT iSelectPage)
	:CPropertySheet(nIDCaption, pParentWnd, iSelectPage)
{
	this->m_psh.dwFlags |= PSH_NOAPPLYNOW;
	this->m_psh.dwFlags &= ~(PSH_HASHELP);
	m_tcpage1.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_tcpage2.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_tcpage3.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_tcpage0.m_psp.dwFlags &= ~(PSP_HASHELP);
	AddPage(&m_tcpage0);
	AddPage(&m_tcpage1);
	AddPage(&m_tcpage2);
	AddPage(&m_tcpage3);
}

TeacherWindow::TeacherWindow(LPCTSTR pszCaption, CWnd* pParentWnd, UINT iSelectPage)
	:CPropertySheet(pszCaption, pParentWnd, iSelectPage)
{
	this->m_psh.dwFlags |= PSH_NOAPPLYNOW;
	this->m_psh.dwFlags &= ~(PSH_HASHELP);
	m_tcpage1.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_tcpage2.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_tcpage3.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_tcpage0.m_psp.dwFlags &= ~(PSP_HASHELP);
	AddPage(&m_tcpage0);
	AddPage(&m_tcpage1);
	AddPage(&m_tcpage2);
	AddPage(&m_tcpage3);
}

TeacherWindow::~TeacherWindow()
{
}


BEGIN_MESSAGE_MAP(TeacherWindow, CPropertySheet)
END_MESSAGE_MAP()


// TeacherWindow 消息处理程序
