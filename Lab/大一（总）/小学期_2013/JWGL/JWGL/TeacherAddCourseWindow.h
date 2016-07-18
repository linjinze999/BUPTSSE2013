#pragma once
#include "ClassTeacher.h"

// TeacherAddCourseWindow 对话框

class TeacherAddCourseWindow : public CDialogEx
{
	DECLARE_DYNAMIC(TeacherAddCourseWindow)

public:
	TeacherAddCourseWindow(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~TeacherAddCourseWindow();

// 对话框数据
	enum { IDD = IDD_DIALOG4 };
	teacher addcourse;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnEnChangeEdit2();
	CString m_TCAddCourseClassname;
	CString m_TCAddCourseClassMessage;
	afx_msg void OnBnClickedOk();
};
