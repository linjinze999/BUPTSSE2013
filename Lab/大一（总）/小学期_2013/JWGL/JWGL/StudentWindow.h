#pragma once
#include "StudentPage1.h"
#include "StudentPage2.h"
#include "StudentPage3.h"
#include "StudentPage4.h"


// StudentWindow

class StudentWindow : public CPropertySheet
{
	DECLARE_DYNAMIC(StudentWindow)

public:
	StudentWindow(UINT nIDCaption, CWnd* pParentWnd = NULL, UINT iSelectPage = 0);
	StudentWindow(LPCTSTR pszCaption, CWnd* pParentWnd = NULL, UINT iSelectPage = 0);
	virtual ~StudentWindow();
public:
	StudentPage1 m_sdpage1;
	StudentPage2 m_sdpage2;
	StudentPage3 m_sdpage3;
	StudentPage4 m_sdpage4;

protected:
	DECLARE_MESSAGE_MAP()
};


