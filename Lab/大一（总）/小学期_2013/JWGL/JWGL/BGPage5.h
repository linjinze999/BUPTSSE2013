#pragma once


// BGPage5 对话框

class BGPage5 : public CPropertyPage
{
	DECLARE_DYNAMIC(BGPage5)

public:
	BGPage5();
	virtual ~BGPage5();

// 对话框数据
	enum { IDD = IDD_PROPPAGE_LARGE4 };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CString m_BGStatType;
	afx_msg void OnBnClickedButton1();
	afx_msg void OnPaint();
};
