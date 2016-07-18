#pragma once
#include "ClassTeacher.h"


// CTeacherModifyPassword 对话框

class CTeacherModifyPassword : public CDialogEx
{
	DECLARE_DYNAMIC(CTeacherModifyPassword)

public:
	CTeacherModifyPassword(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CTeacherModifyPassword();

// 对话框数据
	enum { IDD = IDD_DIALOG18 };
	teacher modify;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CString m_TCModifyPWOld;
	CString m_TCModifyPWnew1;
	CString m_TCModifyPWnew2;
	afx_msg void OnBnClickedOk();
};
