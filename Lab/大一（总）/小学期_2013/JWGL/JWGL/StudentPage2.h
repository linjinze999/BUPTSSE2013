#pragma once
#include "afxcmn.h"


// StudentPage2 对话框

class StudentPage2 : public CPropertyPage
{
	DECLARE_DYNAMIC(StudentPage2)

public:
	StudentPage2();
	virtual ~StudentPage2();

// 对话框数据
	enum { IDD = IDD_PROPPAGE_LARGE9 };
	CString sdname;
	CString chooseclassname,dropclassname;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnBnClickedButton1();
	afx_msg void OnBnClickedButton2();
	CListCtrl m_SDDropCourse;
	CListCtrl m_SDChooseCourse;
	virtual BOOL OnInitDialog();
	afx_msg void OnNMClickList1(NMHDR *pNMHDR, LRESULT *pResult);
	afx_msg void OnNMClickList2(NMHDR *pNMHDR, LRESULT *pResult);
	afx_msg void OnPaint();
};
