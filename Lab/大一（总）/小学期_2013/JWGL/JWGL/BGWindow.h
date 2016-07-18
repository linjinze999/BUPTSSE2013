#pragma once
#include"BGPage1.h"
#include"BGPage2.h"
#include"BGPage3.h"
#include"BGPage4.h"
#include"BGPage5.h"

// BGWindow

class BGWindow : public CPropertySheet
{
	DECLARE_DYNAMIC(BGWindow)

public:
	BGWindow(UINT nIDCaption, CWnd* pParentWnd = NULL, UINT iSelectPage = 0);
	BGWindow(LPCTSTR pszCaption, CWnd* pParentWnd = NULL, UINT iSelectPage = 0);
	virtual ~BGWindow();
public:
	BGPage1 m_page1;
	BGPage2 m_page2;
	BGPage3 m_page3;
	BGPage4 m_page4;
	BGPage5 m_page5;
protected:
	DECLARE_MESSAGE_MAP()
};


