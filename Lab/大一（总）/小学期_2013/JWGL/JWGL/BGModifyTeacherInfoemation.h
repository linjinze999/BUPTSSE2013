#pragma once
#include "ClassTeacher.h"

// CBGModifyTeacherInfoemation 对话框

class CBGModifyTeacherInfoemation : public CDialogEx
{
	DECLARE_DYNAMIC(CBGModifyTeacherInfoemation)

public:
	CBGModifyTeacherInfoemation(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CBGModifyTeacherInfoemation();

// 对话框数据
	enum { IDD = IDD_DIALOG12 };
	teacher modifyinformation;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnEnChangeEdit3();
	CString m_BGModifyTCID;
	CString m_BGModifyTCPassword;
	CString m_BGModifyTCName;
	CString m_BGModifyTCSex;
	CString m_BGModifyTCTelephone;
	afx_msg void OnBnClickedOk();
};
