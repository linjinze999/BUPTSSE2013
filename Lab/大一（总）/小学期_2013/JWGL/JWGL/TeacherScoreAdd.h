#pragma once
#include "afxcmn.h"


// CTeacherScoreAdd 对话框

class CTeacherScoreAdd : public CDialogEx
{
	DECLARE_DYNAMIC(CTeacherScoreAdd)

public:
	CTeacherScoreAdd(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CTeacherScoreAdd();

// 对话框数据
	enum { IDD = IDD_DIALOG5 };
	CString scorename;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CString m_TCScoreAddName;
	CString m_TCScoreAddScore;
	CListCtrl m_TCScoreAddCtllist;
	virtual BOOL OnInitDialog();
	afx_msg void OnBnClickedButton1();
	afx_msg void OnNMClickList1(NMHDR *pNMHDR, LRESULT *pResult);
};
