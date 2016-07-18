#pragma once
#include "ClassStudent.h"

// CBGSearchSDFuzzy 对话框

class CBGSearchSDFuzzy : public CDialogEx
{
	DECLARE_DYNAMIC(CBGSearchSDFuzzy)

public:
	CBGSearchSDFuzzy(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CBGSearchSDFuzzy();

// 对话框数据
	enum { IDD = IDD_DIALOG19 };
	student searchsdfuzzy[200];

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持
	DECLARE_MESSAGE_MAP()
public:
	CListCtrl m_ctllistBGSearchSDFuzzy;
	virtual BOOL OnInitDialog();
};
