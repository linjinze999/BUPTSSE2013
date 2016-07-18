#pragma once
#include "ClassStudent.h"

// CSDModifySDPassword 对话框

class CSDModifySDPassword : public CDialogEx
{
	DECLARE_DYNAMIC(CSDModifySDPassword)

public:
	CSDModifySDPassword(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CSDModifySDPassword();

// 对话框数据
	enum { IDD = IDD_DIALOG16 };
	student modifypw;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CString m_SDIFMTOld;
	CString m_SDIFMTNew1;
	CString m_SDIFMTNew2;
	afx_msg void OnBnClickedOk();
};
