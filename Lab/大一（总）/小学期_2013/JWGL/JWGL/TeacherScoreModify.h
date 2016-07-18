#pragma once


// CTeacherScoreModify 对话框

class CTeacherScoreModify : public CDialogEx
{
	DECLARE_DYNAMIC(CTeacherScoreModify)

public:
	CTeacherScoreModify(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CTeacherScoreModify();

// 对话框数据
	enum { IDD = IDD_DIALOG8 };
	CString classname;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CString m_TCScoreModifyID;
	CString m_TCScoreModifyScore;
	afx_msg void OnBnClickedOk();
	CString m_TCScoreModifyOld;
	afx_msg void OnEnChangeEdit1();
};
