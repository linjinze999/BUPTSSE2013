#pragma once


// CTIP2 对话框

class CTIP2 : public CDialogEx
{
	DECLARE_DYNAMIC(CTIP2)

public:
	CTIP2(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CTIP2();

// 对话框数据
	enum { IDD = IDD_DIALOG2 };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
};
