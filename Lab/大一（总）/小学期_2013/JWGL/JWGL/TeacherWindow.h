#pragma once
#include "TeacherPage1.h"
#include "TeacherPage2.h"
#include "TeacherPage3.h"
#include "TeacherPage4.h"


// TeacherWindow

class TeacherWindow : public CPropertySheet
{
	DECLARE_DYNAMIC(TeacherWindow)

public:
	TeacherWindow(UINT nIDCaption, CWnd* pParentWnd = NULL, UINT iSelectPage = 0);
	TeacherWindow(LPCTSTR pszCaption, CWnd* pParentWnd = NULL, UINT iSelectPage = 0);
	virtual ~TeacherWindow();
public:
	CTeacherPage4 m_tcpage0;
	TeacherPage1 m_tcpage1;
	TeacherPage2 m_tcpage2;
	TeacherPage3 m_tcpage3;

protected:
	DECLARE_MESSAGE_MAP()
};


