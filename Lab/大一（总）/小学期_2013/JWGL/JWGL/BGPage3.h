#pragma once


// BGPage3 对话框

class BGPage3 : public CPropertyPage
{
	DECLARE_DYNAMIC(BGPage3)

public:
	BGPage3();
	virtual ~BGPage3();

// 对话框数据
	enum { IDD = IDD_PROPPAGE_LARGE2 };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnBnClickedRadio1();
	afx_msg void OnEnChangeEdit2();
	afx_msg void OnEnChangeEdit3();
	afx_msg void OnBnClickedRadio2();
	afx_msg void OnBnClickedButton1();
	CString m_BGDeleteTCID;
	afx_msg void OnBnClickedButton2();
	long m_BGDeleteUserBegin;
	long m_BGDeleteUserEnd;
	afx_msg void OnPaint();
};
