#pragma once
#include "afxwin.h"

// TeacherMessageForStudentWindow 对话框

class TeacherMessageForStudentWindow : public CDialogEx
{
	DECLARE_DYNAMIC(TeacherMessageForStudentWindow)

public:
	TeacherMessageForStudentWindow(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~TeacherMessageForStudentWindow();

// 对话框数据
	enum { IDD = IDD_DIALOG7 };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CString m_TCMessageForSD;
	CString m_TCMessageClassName;
	CString m_TCMessageMessage;
	afx_msg void OnBnClickedOk();
	virtual BOOL OnInitDialog();
	CComboBox m_TCMessageCourseName;
};
