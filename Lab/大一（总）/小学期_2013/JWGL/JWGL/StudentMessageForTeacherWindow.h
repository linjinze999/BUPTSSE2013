#pragma once


// StudentMessageForTeacherWindow 对话框

class StudentMessageForTeacherWindow : public CDialogEx
{
	DECLARE_DYNAMIC(StudentMessageForTeacherWindow)

public:
	StudentMessageForTeacherWindow(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~StudentMessageForTeacherWindow();

// 对话框数据
	enum { IDD = IDD_DIALOG6 };
	CString tcname;

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CListCtrl m_ctllistSDMessageForTC;
	virtual BOOL OnInitDialog();
};
