// BGWindow.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGWindow.h"


// BGWindow

IMPLEMENT_DYNAMIC(BGWindow, CPropertySheet)

BGWindow::BGWindow(UINT nIDCaption, CWnd* pParentWnd, UINT iSelectPage)
	:CPropertySheet(nIDCaption, pParentWnd, iSelectPage)
{
	this->m_psh.dwFlags |= PSH_NOAPPLYNOW;
	this->m_psh.dwFlags &= ~(PSH_HASHELP);
	m_page1.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_page2.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_page3.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_page4.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_page5.m_psp.dwFlags &= ~(PSP_HASHELP);
	AddPage(&m_page1);
	AddPage(&m_page2);
	AddPage(&m_page3);
	AddPage(&m_page4);
	AddPage(&m_page5);
}

BGWindow::BGWindow(LPCTSTR pszCaption, CWnd* pParentWnd, UINT iSelectPage)
	:CPropertySheet(pszCaption, pParentWnd, iSelectPage)
{
	this->m_psh.dwFlags |= PSH_NOAPPLYNOW;
	this->m_psh.dwFlags &= ~(PSH_HASHELP);
	m_page1.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_page2.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_page3.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_page4.m_psp.dwFlags &= ~(PSP_HASHELP);
	m_page5.m_psp.dwFlags &= ~(PSP_HASHELP);
	AddPage(&m_page1);
	AddPage(&m_page2);
	AddPage(&m_page3);
	AddPage(&m_page4);
	AddPage(&m_page5);
}

BGWindow::~BGWindow()
{
}


BEGIN_MESSAGE_MAP(BGWindow, CPropertySheet)
END_MESSAGE_MAP()


// BGWindow 消息处理程序
