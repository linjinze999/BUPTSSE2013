package com.buptsse.spm.dao.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.buptsse.spm.dao.ISelectCourseDao;
import com.buptsse.spm.domain.Course;



/**
 * @author xinyu han
 * @date 2015年11月01日 下午3:08
 * @description	选课持久层接口定义，包括课程信息的增加，修改，保存，删除，以及根据条件查询
 * @modify
 * @modifyDate 
 */
@Repository
public class SelectCourseDaoImpl extends BaseDAOImpl<Course> implements ISelectCourseDao {
	private static Logger LOG = Logger.getLogger(SelectCourseDaoImpl.class);//我也不懂这句话是什么意思
	/*
	 * (non-Javadoc)
	 * @see com.buptsse.dao.BaseDAO#find(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<Course> find(String hql, Course[] param) {
		// TODO Auto-generated method stub
		return super.find("from Course where studentId= :param", param);
	}
	/*
	 * (non-Javadoc)
	 * @see com.buptsse.dao.BaseDAO#get(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Course get(String hql, Course[] param) {
		// TODO Auto-generated method stub
		return super.get(Course.class, param);
	}
	/*
	 * (non-Javadoc)
	 * @see com.buptsse.dao.BaseDAO#count(java.lang.String, java.lang.Object[])
	 */
	
/*	@Override
	public Long count(String hql, Object[] param) {
		// TODO Auto-generated method stub
		
		return super.count(hql, param);
	}*/
	/*
	 * (non-Javadoc)
	 * @see com.buptsse.dao.BaseDAO#executeHql(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Integer executeHql(String hql, Course[] param) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.buptsse.dao.SelectCourseDao#insertCourse(com.buptsse.domain.Course)
	 */
	@Override
	public boolean insertCourse(Course course) {
		// TODO Auto-generated method stub
		try{
			super.save(course);
			System.out.println("%%%%%%%%%%%");
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	

	
	
	/*
	 * (non-Javadoc)
	 * @see com.buptsse.dao.SelectCourseDao#findCourse(java.lang.String)
	 */
	@Override
	public Course findCourse(String studentId) {
		// TODO Auto-generated method stub
		Course Course = new Course();
		try{
			List<Course> list = new ArrayList<Course>();
			list = super.find("from Course where studentId ='"+studentId+"'");
						
			if(list!=null && list.size()>0){
				return list.get(0);
			}
			
/*			for(int i = 0;i < list.size();i++){
				System.out.println(list.get(i).getName());
				if(studentId.equals(list.get(i).getStudentId())){
					return list.get(i);
				}
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return null;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.buptsse.dao.SelectCourseDao#saveCourse(com.buptsse.domain.Course)
	 */
	@Override
	public boolean updateCourse(Course course) {
		// TODO Auto-generated method stub
		try{
			super.update(course);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
	}

	
	public boolean saveOrUpdateCourse(Course course){
		
		try{
			super.saveOrUpdate(course);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
		return true;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.buptsse.dao.SelectCourseDao#deleteCourse(java.lang.String)
	 */
	@Override
	public boolean deleteCourse(Course course) {
		// TODO Auto-generated method stub
		try{
			super.delete(course);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.buptsse.dao.SelectCourseDao#findAllCourse()
	 */
	@Override
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		try{
			List<Course> list = new ArrayList<Course>();
			list = super.find("from Course");
			return list;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(e);
			return null;
		}
		//return null;
	}
	
	
	
	public List findPage( String hql,List param ,Integer page, Integer rows) {
		// TODO Auto-generated method stub
		
		System.out.println("***开始 分页查询****");
		return super.find(hql, param, page, rows);
		
	}
	@Override
	public Long count(String hql, Course[] param) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Long countCourse(String hql, List param) {
		// TODO Auto-generated method stub
		return super.count(hql, param);
	}
	

	
	/*public void saveCourse(Course course) {
		String sql = "insert into course(studentId,name,classId,email,telno,status) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, course.getStudentId());
			pstmt.setString(2, course.getName());
			pstmt.setString(3, course.getClassId());
			pstmt.setString(4, course.getEmail());
			pstmt.setString(5, course.getTelno());
			pstmt.setString(6, course.getStatus());
			pstmt.executeUpdate();
			
			
			System.err.println("保存选课信息已打印");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbc.close();
		}
	}

	public List<Course> findAllCourse() {
		List<Course> courseList = new ArrayList<Course>();
		String sql = "select * from course order by studentId";
		PreparedStatement pstmt = null;
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			pstmt = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Course course = new Course();

				course.setStudentId(rs.getString("studentId"));
				course.setName(rs.getString("name"));
				course.setClassId(rs.getString("classId"));
				course.setEmail(rs.getString("email"));
				course.setTelno(rs.getString("telno"));
				course.setStatus(rs.getString("status"));
				course.setDailyGrade(rs.getInt("dailyGrade"));
				course.setMidGrade(rs.getInt("midGrade"));
				course.setFinalGrade(rs.getInt("finalGrade"));
				course.setTotalGrade(rs.getInt("totalGrade"));
				course.setPracticeGrade(rs.getInt("practiceGrade"));
				courseList.add(course);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbc.close();
		}
		return courseList;
	}

	public void changeStatus(String studentId,int pre_status,int next_status) {
		String new_status=null;
		// List<Course> courseList= new ArrayList<Course>();
		//check 是否是合法性转换状态
		if(pre_status != next_status - 1){
			return;
		}
		switch (pre_status) {
		case 1:
			new_status="待确认";
			break;
		case 2:
			new_status="选中";
			break;
		case 3:
			new_status="删除";
		default:
			break;
		}
		String sql = "update course set status= ? where name= ? ";
		PreparedStatement pstmt = null;
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			pstmt = dbc.getConnection().prepareStatement(
					"update course set status=? where name= ?");
			pstmt.setString(1, new_status);
			pstmt.setString(2, studentId);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbc.close();
		}
	}

	public void deleteCourse(int studentId) {
		PreparedStatement pstmt = null;
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			pstmt = dbc.getConnection().prepareStatement(
					"delete from course where studentId= ?");
			// pstmt.setString(1, "ѡ�γɹ�");
			pstmt.setInt(1, studentId);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbc.close();
		}
	}

	public List<Course> search(String choose) {
		PreparedStatement pstmt = null;
		DBConnect dbc = null;
		dbc = new DBConnect();
		List<Course> courseList = new ArrayList<Course>();
		try {
			if ((choose != null) && (isNum(choose))) {
				String sql = "select * from course where studentId= ? or classId= ? order by studentId";

				pstmt = dbc.getConnection().prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(choose));
				pstmt.setString(2, choose);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					Course course = new Course();

					course.setStudentId(rs.getString("studentId"));
					course.setName(rs.getString("name"));
					course.setClassId(rs.getString("classId"));
					course.setEmail(rs.getString("email"));
					course.setTelno(rs.getString("telno"));
					course.setStatus(rs.getString("status"));
					course.setDailyGrade(rs.getInt("dailyGrade"));
					course.setMidGrade(rs.getInt("midGrade"));
					course.setFinalGrade(rs.getInt("finalGrade"));
					course.setTotalGrade(rs.getInt("totalGrade"));
					course.setPracticeGrade(rs.getInt("practiceGrade"));
					courseList.add(course);
				}
			}else{
				String sql = "select * from course where classId= ? or name= ? order by studentId";
				pstmt = dbc.getConnection().prepareStatement(sql);
				pstmt.setString(1,choose);
				pstmt.setString(2,choose);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					Course course = new Course();
					course.setStudentId(rs.getString("studentId"));
					course.setName(rs.getString("name"));
					course.setClassId(rs.getString("classId"));
					course.setEmail(rs.getString("email"));
					course.setTelno(rs.getString("telno"));
					course.setStatus(rs.getString("status"));
					courseList.add(course);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbc.close();
		}
		return courseList;
	}

	public boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}*/
}
