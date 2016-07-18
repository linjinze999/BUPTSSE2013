#pragma once


// BGPage2 对话框

class BGPage2 : public CPropertyPage
{
	DECLARE_DYNAMIC(BGPage2)

public:
	BGPage2();
	virtual ~BGPage2();

// 对话框数据
	enum { IDD = IDD_PROPPAGE_LARGE1 };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CString m_BGModifyIFMTID;
	afx_msg void OnBnClickedButton1();
	afx_msg void OnBnClickedButton2();
	afx_msg void OnPaint();
};
