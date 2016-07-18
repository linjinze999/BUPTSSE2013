
// JWGLDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "JWGL.h"
#include "JWGLDlg.h"
#include "afxdialogex.h"
#include "TIP1.h"
#include "TIP2.h"
#include "TIP3.h"
#include "BGWindow.h"
#include "TeacherWindow.h"
#include "StudentWindow.h"
#include "ClassStudent.h"
#include "ClassTeacher.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// 用于应用程序“关于”菜单项的 CAboutDlg 对话框

int usertype =0;

class CAboutDlg : public CDialogEx
{
public:
	CAboutDlg();

// 对话框数据
	enum { IDD = IDD_ABOUTBOX };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

// 实现
protected:
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialogEx(CAboutDlg::IDD)
{
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialogEx)
END_MESSAGE_MAP()


// CJWGLDlg 对话框




CJWGLDlg::CJWGLDlg(CWnd* pParent /*=NULL*/)
	: CDialogEx(CJWGLDlg::IDD, pParent)
	, m_idnumber(_T(""))
	, m_password(_T(""))
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CJWGLDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_idnumber);
	DDV_MaxChars(pDX, m_idnumber, 10);
	DDX_Text(pDX, IDC_EDIT2, m_password);
	DDV_MaxChars(pDX, m_password, 10);
}

BEGIN_MESSAGE_MAP(CJWGLDlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_BUTTON1, &CJWGLDlg::OnBnClickedButton1)
	ON_BN_CLICKED(IDC_RADIO1, &CJWGLDlg::OnBnClickedRadio1)
	ON_BN_CLICKED(IDC_RADIO2, &CJWGLDlg::OnBnClickedRadio2)
	ON_BN_CLICKED(IDC_RADIO3, &CJWGLDlg::OnBnClickedRadio3)
END_MESSAGE_MAP()


// CJWGLDlg 消息处理程序

BOOL CJWGLDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// 将“关于...”菜单项添加到系统菜单中。

	// IDM_ABOUTBOX 必须在系统命令范围内。
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != NULL)
	{
		BOOL bNameValid;
		CString strAboutMenu;
		bNameValid = strAboutMenu.LoadString(IDS_ABOUTBOX);
		ASSERT(bNameValid);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// 设置此对话框的图标。当应用程序主窗口不是对话框时，框架将自动
	//  执行此操作
	SetIcon(m_hIcon, TRUE);			// 设置大图标
	SetIcon(m_hIcon, FALSE);		// 设置小图标

	// TODO: 在此添加额外的初始化代码
	SkinH_Attach();

	return TRUE;  // 除非将焦点设置到控件，否则返回 TRUE
}

void CJWGLDlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialogEx::OnSysCommand(nID, lParam);
	}
}

// 如果向对话框添加最小化按钮，则需要下面的代码
//  来绘制该图标。对于使用文档/视图模型的 MFC 应用程序，
//  这将由框架自动完成。

void CJWGLDlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // 用于绘制的设备上下文

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// 使图标在工作区矩形中居中
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// 绘制图标
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

//当用户拖动最小化窗口时系统调用此函数取得光标
//显示。
HCURSOR CJWGLDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}



void CJWGLDlg::OnBnClickedButton1()//登陆界面
{
	// TODO: 在此添加控件通知处理程序代码
	UpdateData(TRUE); 
	if( usertype == 1){
		teacher tclogin;
		bool ser;
		ser = tclogin.serch(m_idnumber);
		if(ser && m_password == tclogin.getpassword()){
			this->ShowWindow(SW_HIDE);
	        TeacherWindow Propage(_T("教师用户端")); 
			Propage.m_tcpage0.m_TCIFMTID = tclogin.getid();
			Propage.m_tcpage0.m_TCIFMTPassword = tclogin.getpassword();
			Propage.m_tcpage0.m_TCIFMTName = tclogin.getname();
			Propage.m_tcpage0.m_TCIFMTSex = tclogin.getsex();
			Propage.m_tcpage0.m_TCIFMTTelephone = tclogin.gettelephone();
			Propage.m_tcpage1.m_TCCourseName = tclogin.getname();
			Propage.m_tcpage3.message.setname(tclogin.getname());
			Propage.m_tcpage2.tcname = tclogin.getname();
			Propage.DoModal();
			SendMessage(WM_CLOSE);
	    }
	    else{
	        CTIP2 tip2;
		    tip2.DoModal();
	    }
	}
	else{
		if( usertype == 2){
			student sdlogin;
			bool serc;
			serc = sdlogin.serch(m_idnumber);
			if(serc && m_password == sdlogin.getpassword()){
				this->ShowWindow(SW_HIDE);
	            StudentWindow Propage(_T("学生用户端")); 
				Propage.m_sdpage1.m_SDIFMTID = sdlogin.getid();
				Propage.m_sdpage1.m_SDIFMTPassword = sdlogin.getpassword();
				Propage.m_sdpage1.m_SDIFMTName = sdlogin.getname();
				Propage.m_sdpage1.m_SDIFMTSex = sdlogin.getsex();
				Propage.m_sdpage1.m_SDIFMTTelephone = sdlogin.gettelephone();
				Propage.m_sdpage1.m_SDIFMTMajor = sdlogin.getmajor();
				Propage.m_sdpage1.m_SDIFMTNoclass = sdlogin.getnoclass();
				Propage.m_sdpage2.sdname = sdlogin.getname();
				Propage.m_sdpage3.studentname = sdlogin.getname();
				Propage.m_sdpage4.sdname = sdlogin.getname();
				Propage.DoModal();
				SendMessage(WM_CLOSE);
	        }
	        else{
	            CTIP2 tip2;
		        tip2.DoModal();
	        }
		}
		else{
			if(usertype == 3){
			    if(m_idnumber == "1111" && m_password == "1111"){
					this->ShowWindow(SW_HIDE);
					BGWindow Propage(_T("后台用户管理")); 
			        Propage.DoModal();
					SendMessage(WM_CLOSE);
	            }
	            else{
	                CTIP2 tip2;
		            tip2.DoModal();
	            }
			}
			else{
			    CTIP3 tip3;
		        tip3.DoModal();
			}
		}
	}
	UpdateData(FALSE); 
}


void CJWGLDlg::OnBnClickedRadio1()
{
	// TODO: 在此添加控件通知处理程序代码
	usertype = 1;
}


void CJWGLDlg::OnBnClickedRadio2()
{
	// TODO: 在此添加控件通知处理程序代码
	usertype = 2;
}


void CJWGLDlg::OnBnClickedRadio3()
{
	// TODO: 在此添加控件通知处理程序代码
	usertype = 3;
}
