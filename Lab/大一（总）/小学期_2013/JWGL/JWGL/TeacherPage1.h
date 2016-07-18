#pragma once
#include "afxcmn.h"


// TeacherPage1 对话框

class TeacherPage1 : public CPropertyPage
{
	DECLARE_DYNAMIC(TeacherPage1)

public:
	TeacherPage1();
	virtual ~TeacherPage1();
	CString tcdeleteclass;
// 对话框数据
	enum { IDD = IDD_PROPPAGE_LARGE5 };


protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnBnClickedButton1();
	afx_msg void OnBnClickedButton2();
	CString m_TCCourseName;
	CListCtrl m_TCCourseClassList;
	virtual BOOL OnInitDialog();
	afx_msg void OnNMClickList1(NMHDR *pNMHDR, LRESULT *pResult);
	afx_msg void OnPaint();
};
