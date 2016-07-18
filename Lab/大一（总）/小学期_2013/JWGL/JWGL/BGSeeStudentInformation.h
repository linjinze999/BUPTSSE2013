#pragma once


// CBGSeeStudentInformation 对话框

class CBGSeeStudentInformation : public CDialogEx
{
	DECLARE_DYNAMIC(CBGSeeStudentInformation)

public:
	CBGSeeStudentInformation(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CBGSeeStudentInformation();

// 对话框数据
	enum { IDD = IDD_DIALOG15 };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	CString m_BGSearchExactID;
	CString m_BGSearchExactPassword;
	CString m_BGSearchExactName;
	CString m_BGSearchExactSex;
	CString m_BGSearchExactTelephone;
	CString m_BGSearchExactMajor;
	CString m_BGSearchExactNoclass;
};
