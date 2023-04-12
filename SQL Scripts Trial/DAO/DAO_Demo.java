//STEP 1. Import required packages
import java.sql.*;

public class DAO_Demo {
	public static DAO_Factory daoFactory;
	public static void main(String[] args) {
		try{
			daoFactory = new DAO_Factory();

			System.out.println("Running usecase1");
			usecase_insert1();
			usecase_display();

			System.out.println("Running usecase2");
			usecase_insert2();
			usecase_display();

		}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
		}
	}
	//end main
	public static void usecase_insert1()
	{
		Student s1 = new Student();
		Student s2 = new Student();

		s1.setRollno(1001);
		s1.setName("name-1001");
		s2.setRollno(1002);
		s2.setName("name-1002");

		try{
			// Start transaction boundary
			daoFactory.activateConnection();

			// Carry out DB operations using DAO
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.addStudent(s1);
			sdao.addStudent(s2);

			// End transaction boundary with success
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
				// End transaction boundary with failure
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
		}
	}

	public static void usecase_insert2()
	{
		Student s1 = new Student();
		Student s2 = new Student();

		s1.setRollno(1001);
		s1.setName("name-1001");
		s2.setRollno(1003);
		s2.setName("name-1003");

		try{
			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();
			sdao.addStudent(s1);
			sdao.addStudent(s2);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
		}
	}
	public static void usecase_display()
	{
		try{
			int rollno;

			daoFactory.activateConnection();
			StudentDAO sdao = daoFactory.getStudentDAO();

			rollno=1001;
			System.out.println("Trying Rollno=" + rollno);
			Student s1 = sdao.getStudentByKey(rollno);
			s1.print();

			rollno=1002;
			System.out.println("Trying Rollno=" + rollno);
			Student s2 = sdao.getStudentByKey(rollno);
			s2.print();

			rollno=1003;
			System.out.println("Trying Rollno=" + rollno);
			Student s3 = sdao.getStudentByKey(rollno);
			s3.print();

			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
		}
	}
}//end FirstExample
