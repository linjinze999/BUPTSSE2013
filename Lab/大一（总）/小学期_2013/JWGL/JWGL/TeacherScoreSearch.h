#pragma once


// CTeacherScoreSearch 对话框

class CTeacherScoreSearch : public CDialogEx
{
	DECLARE_DYNAMIC(CTeacherScoreSearch)

public:
	CTeacherScoreSearch(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CTeacherScoreSearch();

// 对话框数据
	enum { IDD = IDD_DIALOG17 };
	CString classname;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnBnClickedButton1();
	CString m_TCScoreSearchID;
};
