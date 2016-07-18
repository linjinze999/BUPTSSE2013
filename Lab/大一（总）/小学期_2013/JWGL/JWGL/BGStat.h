#pragma once
#include "afxcmn.h"


// CBGStat 对话框

class CBGStat : public CDialogEx
{
	DECLARE_DYNAMIC(CBGStat)

public:
	CBGStat(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CBGStat();

// 对话框数据
	enum { IDD = IDD_DIALOG21 };
	int stattype;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CListCtrl m_BGStatCtllist;
	virtual BOOL OnInitDialog();
};
