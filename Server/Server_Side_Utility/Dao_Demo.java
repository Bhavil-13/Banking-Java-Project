package Server_Side_Utility;

//STEP 1. Import required packages
import java.sql.*;

import models.Person;
import models.PersonDAO;

public class Dao_Demo {
	public static DAO_Factory daoFactory;
	public static void main(String[] args) {
		try{
			daoFactory = new DAO_Factory();

			System.out.println("Running usecase1");
			usecase_insert1();
			// usecase_display();

			// System.out.println("Running usecase2");
			// usecase_insert2();
			// usecase_display();

		}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
		}
	}
	//end main
	public static void usecase_insert1()
	{
		Person s1 = new Person();
		Person s2 = new Person();

		s1.setAddress("Goa");
		s1.setName("name-1001");
		s2.setAddress("Hyserabad");
		s2.setName("name-1002");

		try{
			// Start transaction boundary
			daoFactory.activateConnection();

			// Carry out DB operations using DAO
			PersonDAO sdao = daoFactory.getpersonDAO();
			sdao.createPerson(s1);
			sdao.createPerson(s2);

			// End transaction boundary with success
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
				// End transaction boundary with failure
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
		}
	}

	// public static void usecase_insert2()
	// {
	// 	Person s1 = new Person();
	// 	Person s2 = new Person();

	// 	s1.setRollno(1001);
	// 	s1.setName("name-1001");
	// 	s2.setRollno(1003);
	// 	s2.setName("name-1003");

	// 	try{
	// 		daoFactory.activateConnection();
	// 		PersonDAO sdao = daoFactory.getpersonDAO();
	// 		sdao.addPerson(s1);
	// 		sdao.addPerson(s2);
	// 		daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
	// 	}catch(Exception e){
	// 			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
	// 			e.printStackTrace();
	// 	}
	// }
	// public static void usecase_display()
	// {
	// 	try{
	// 		int rollno;

	// 		daoFactory.activateConnection();
	// 		PersonDAO sdao = daoFactory.getpersonDAO();

	// 		rollno=1001;
	// 		System.out.println("Trying Rollno=" + rollno);
	// 		Person s1 = sdao.getPersonByKey(rollno);
	// 		s1.print();

	// 		rollno=1002;
	// 		System.out.println("Trying Rollno=" + rollno);
	// 		Person s2 = sdao.getPersonByKey(rollno);
	// 		s2.print();

	// 		rollno=1003;
	// 		System.out.println("Trying Rollno=" + rollno);
	// 		Person s3 = sdao.getPersonByKey(rollno);
	// 		s3.print();

	// 		daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
	// 	}catch(Exception e){
	// 			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
	// 			e.printStackTrace();
	// 	}
	// }
}//end FirstExample
