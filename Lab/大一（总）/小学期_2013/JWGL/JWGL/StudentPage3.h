#pragma once
#include "afxcmn.h"


// StudentPage3 对话框

class StudentPage3 : public CPropertyPage
{
	DECLARE_DYNAMIC(StudentPage3)

public:
	StudentPage3();
	virtual ~StudentPage3();

// 对话框数据
	enum { IDD = IDD_PROPPAGE_LARGE10 };
	CString studentname;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CListCtrl m_SDSeeScore;
	virtual BOOL OnInitDialog();
};
