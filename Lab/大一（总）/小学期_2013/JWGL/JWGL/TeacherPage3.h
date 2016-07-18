#pragma once
#include "ClassTeacher.h"

// TeacherPage3 对话框

class TeacherPage3 : public CPropertyPage
{
	DECLARE_DYNAMIC(TeacherPage3)

public:
	TeacherPage3();
	virtual ~TeacherPage3();

// 对话框数据
	enum { IDD = IDD_PROPPAGE_LARGE7 };
	teacher message;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnBnClickedButton1();
	afx_msg void OnBnClickedButton2();
	afx_msg void OnPaint();
};
