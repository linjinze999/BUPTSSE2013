#pragma once
#include "afxcmn.h"
#include "ClassTeacher.h"

// CBGSearchTCFuzzy 对话框

class CBGSearchTCFuzzy : public CDialogEx
{
	DECLARE_DYNAMIC(CBGSearchTCFuzzy)

public:
	CBGSearchTCFuzzy(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CBGSearchTCFuzzy();

// 对话框数据
	enum { IDD = IDD_DIALOG20 };
	teacher searchtcfuzzy[200];

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CListCtrl m_ctllistBGSearchTCfUZZY;
	virtual BOOL OnInitDialog();
};
