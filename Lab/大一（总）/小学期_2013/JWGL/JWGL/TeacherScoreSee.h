#pragma once
#include "afxcmn.h"


// CTeacherScoreSee 对话框

class CTeacherScoreSee : public CDialogEx
{
	DECLARE_DYNAMIC(CTeacherScoreSee)

public:
	CTeacherScoreSee(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CTeacherScoreSee();

// 对话框数据
	enum { IDD = IDD_DIALOG9 };
	CString classname;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CListCtrl m_TCScoreSeeCtllist;
	virtual BOOL OnInitDialog();
	afx_msg void OnBnClickedButton1();
};
