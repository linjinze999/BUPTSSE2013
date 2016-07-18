#pragma once


// CTIP1 对话框

class CTIP1 : public CDialogEx
{
	DECLARE_DYNAMIC(CTIP1)

public:
	CTIP1(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CTIP1();

// 对话框数据
	enum { IDD = IDD_DIALOG1 };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
};
