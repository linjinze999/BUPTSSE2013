// BGModifyStudentInformation.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "BGModifyStudentInformation.h"
#include "afxdialogex.h"


// CBGModifyStudentInformation 对话框

IMPLEMENT_DYNAMIC(CBGModifyStudentInformation, CDialogEx)

CBGModifyStudentInformation::CBGModifyStudentInformation(CWnd* pParent /*=NULL*/)
	: CDialogEx(CBGModifyStudentInformation::IDD, pParent)
	, m_BGModifySDID(_T(""))
	, m_BGModifySDPassword(_T(""))
	, m_BGModifySDName(_T(""))
	, m_BGModifySDSex(_T(""))
	, m_BGModifySDMajor(_T(""))
	, m_BGModifySDClass(_T(""))
	, m_BGModifySDTelephone(_T(""))
{

}

CBGModifyStudentInformation::~CBGModifyStudentInformation()
{
}

void CBGModifyStudentInformation::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_BGModifySDID);
	DDX_Text(pDX, IDC_EDIT2, m_BGModifySDPassword);
	DDX_Text(pDX, IDC_EDIT3, m_BGModifySDName);
	DDX_CBString(pDX, IDC_COMBO1, m_BGModifySDSex);
	DDX_CBString(pDX, IDC_COMBO2, m_BGModifySDMajor);
	DDX_CBString(pDX, IDC_COMBO3, m_BGModifySDClass);
	DDX_Text(pDX, IDC_EDIT5, m_BGModifySDTelephone);
}


BEGIN_MESSAGE_MAP(CBGModifyStudentInformation, CDialogEx)
	ON_BN_CLICKED(IDOK, &CBGModifyStudentInformation::OnBnClickedOk)
END_MESSAGE_MAP()


// CBGModifyStudentInformation 消息处理程序


void CBGModifyStudentInformation::OnBnClickedOk()
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(true);
	CString oldid;
	oldid = modifysd.getid();
	if(m_BGModifySDID == _T("")){MessageBox(_T("账号不能为空！"));}
	else{
		if(m_BGModifySDPassword == _T("")){MessageBox(_T("密码不能为空！"));}
		else{
			if(m_BGModifySDName == _T("")){MessageBox(_T("名字不能为空！"));}
			else{
				if(m_BGModifySDSex == _T("")){MessageBox(_T("性别不能为空！"));}
				else{
					if(m_BGModifySDTelephone == _T("")){MessageBox(_T("电话不能为空！"));}
					else{
						if(m_BGModifySDMajor == _T("")){MessageBox(_T("专业不能为空！"));}
						else{
							if(m_BGModifySDClass == _T("")){MessageBox(_T("班级不能为空！"));}
							else{
								modifysd.modifyinformation(oldid,m_BGModifySDID,m_BGModifySDPassword,m_BGModifySDName,m_BGModifySDSex,m_BGModifySDTelephone,m_BGModifySDMajor,m_BGModifySDClass);
								MessageBox(_T("修改成功！"));
								CDialogEx::OnOK();
							}
						}
					}
				}
			}
		}
	}
}
