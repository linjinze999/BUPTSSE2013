#pragma once
#include "afxcmn.h"
#include "afxwin.h"


// StudentPage4 对话框

class StudentPage4 : public CPropertyPage
{
	DECLARE_DYNAMIC(StudentPage4)

public:
	StudentPage4();
	virtual ~StudentPage4();

// 对话框数据
	enum { IDD = IDD_PROPPAGE_LARGE11 };
	CString sdname;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CListCtrl m_SDMessageTC;
	virtual BOOL OnInitDialog();
	CString m_SDMessageToTC;
	CString m_SDMessageCourse;
	CComboBox m_SDMessageCourseName;
	afx_msg void OnBnClickedButton1();
	afx_msg void OnPaint();
};
