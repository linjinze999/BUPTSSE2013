#pragma once


// CBGSeeTeacherInformation 对话框

class CBGSeeTeacherInformation : public CDialogEx
{
	DECLARE_DYNAMIC(CBGSeeTeacherInformation)

public:
	CBGSeeTeacherInformation(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CBGSeeTeacherInformation();

// 对话框数据
	enum { IDD = IDD_DIALOG14 };

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnEnChangeEdit4();
	CString m_BGSearchTeacherExactID;
	CString m_BGSearchTeacherExactPassword;
	CString m_BGSearchTeacherExactName;
	CString m_BGSearchTeacherExactSex;
	CString m_BGSearchTeacherExactTelephone;
};
