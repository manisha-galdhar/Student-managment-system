package com.test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Login{

    public static void main(String[] args ) 
    		throws NumberFormatException, IOException, SQLException, ClassNotFoundException, ParseException {
        
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
    	System.out.println("==============================================================================");
		System.out.println("=====================  WELCOME STUDENT MG ================================");
		System.out.println("==============================================================================");
		System.out.println("\t\t 1 --> STUDENT");
		System.out.println("\t\t 2 --> ADMIN");
		System.out.println("==============================================================================");
		System.out.println("Enter your choice:");
		int choice = Integer.parseInt(br.readLine());

		if (choice == 1) {

			System.out.println("==============================================================================");
			System.out.println("===========================    LOGIN DETAILS  ================================");
			System.out.println("==============================================================================");

			System.out.print("\t Enter your sname:");
			String sdname = br.readLine();
			System.out.print("\t Enter your id:");
			int sdaid = Integer.parseInt(br.readLine());
		
			try {
				Connection conn = MysqlConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("select sdaid from students where sdname=?");
				ps.setString(1, sdname);
				ResultSet result = ps.executeQuery();
				int id = 0;
				boolean login = false;
				while (result.next()) {
					id = result.getInt("sdaid");
					login = true;
				}

				if (sdaid==id) {
					System.out
							.println("==============================================================================");
					System.out
							.println("===========================   Login successful ================================");
					System.out
							.println("==============================================================================");
					System.out.println("============================== WELCOME " + sdname.toUpperCase()
							+ " ===============================");
					System.out
							.println("==============================================================================");

					String status ="Y";
					do {
					
						System.out.println("\t\t  1 --> Change sdiad");
						System.out.println("\t\t  2 --> Exit/Logout");
						System.out.println(
								"==============================================================================");
					
							
						case1:
							System.out.println("Enter the id:");
							int oldid =Integer.parseInt( br.readLine());

							System.out.println("Enter the new id:");
							int newid = Integer.parseInt( br.readLine());
							
							System.out.println("Re-enter the new id:");
							int reid = Integer.parseInt( br.readLine());

							ps = conn.prepareStatement("select * from students where sdname=?");
							ps.setString(1, sdname);

							result = ps.executeQuery();
							int existingid = 0;
							while (result.next()) {
								existingid = result.getInt("sdaid");

							}

							if (existingid==(newid)) {
								if (newid==(reid)) {
									ps = conn.prepareStatement("update students set sdaid=? where sdname=?");
									ps.setInt(1, newid);
									ps.setString(2, sdname);

									if (ps.executeUpdate() > 0) {
										System.out.println(
												"==============================================================================");
										System.out.println("Password changed successfully!!");
										System.out.println(
												"==============================================================================");

									} else {
										System.out.println(
												"==============================================================================");
										System.out.println("Problem in password changed!!");
										System.out.println(
												"==============================================================================");

									}

								} else {
									System.out.println(
											"==============================================================================");
									System.out.println("New password and retype password must be same!!");
									System.out.println(
											"==============================================================================");

								}
							} else {
								System.out.println(
										"==============================================================================");
								System.out.println("Old id is wrong!!");
								System.out.println(
										"==============================================================================");

							}
							System.out.println("Do you want to continue?(Y/N)");
							status = br.readLine();

							if (status.equals("n") || status.equals("N")) {
								login = false;
							}
							break;

						

						

					} while (login);
					System.out.println("==============================================================================");
					System.out.println("Bye. Have a nice day!!");
					System.out.println("==============================================================================");

				} else {
					System.out
							.println("==============================================================================");
					System.out
							.println("================================  Wrong password  ============================");
					System.out
							.println("==============================================================================");
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("==============================================================================");
				System.out.println("===========================  Wrong username/password  ========================");
				System.out.println("==============================================================================");

			}

		} else if (choice == 2) {

			System.out.println("==============================================================================");
			System.out.println("===========================    LOGIN DETAILS  ================================");
			System.out.println("==============================================================================");

			System.out.print("\t Enter your username:");
			String userName = br.readLine();
			System.out.print("\t Enter your password:");
			String userPassword = br.readLine();

			Connection conn = MysqlConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from admin where username=?");
			ps.setString(1, userName);
			ResultSet result = ps.executeQuery();
			String password = null;
			boolean login = false;
			while (result.next()) {
				password = result.getString("password");
				login = true;
			}

			if (password.equals(userPassword)) {

				String status = "y";
				System.out.println("==============================================================================");
				System.out.println("=============================   WELCOME ADMIN    =============================");
				System.out.println("==============================================================================");

				do {
					System.out.println("==============================================================================");
					System.out.println("\t\t  1 --> open new student info");
					System.out.println("\t\t  2 --> delet student sc");
					System.out.println("\t\t  3 --> update");
					System.out.println("\t\t  4 --> Change Password");
					System.out.println("\t\t  5 --> Exit/Logout");
					System.out.println("==============================================================================");

					System.out.println("Enter your choice:");
					int operation = Integer.parseInt(br.readLine());

					switch (operation) {
					case 1:
						System.out.println("Enter student id:");
						int sdaid =Integer.parseInt( br.readLine());

						System.out.println("Enter student sname:");
						String sdname = br.readLine();
						
						System.out.println("Date of Birth:(dd/MM/YYYY)");
						String dob = br.readLine();


						System.out.println("Gender:");
						String gender = br.readLine();
						
						System.out.println("Enter address: ");
						String address = br.readLine();
						

						System.out.println("Enter phone number:");
						int phone = Integer.parseInt(br.readLine());


						System.out.println("Enter email id:");
						String email = br.readLine();

				
			
						System.out.println("Enter course: ");
						String course = br.readLine();

						System.out.println("Enter class:");
						String sd = br.readLine();

					

						ps = conn.prepareStatement("insert into students values(?,?,?,?,?,?,?,?,?)");
						ps.setInt(1, sdaid);
						ps.setString(2, sdname);
					
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
						java.util.Date utilDate = format.parse(dob);
						java.sql.Date date = new java.sql.Date(utilDate.getTime());
						ps.setDate(3, date);
						ps.setString(4, gender);
						ps.setString(5, address);
						ps.setInt(6, phone);
						ps.setString(7, email);
						ps.setString(8, course);
						ps.setString(9, sd);

						if (ps.executeUpdate() > 0) {
							System.out.println(
									"==============================================================================");
							System.out.println(" create student  ac successfully!!");
							System.out.println(
									"==============================================================================");

						}

						System.out.println("Do you want to continue?(Y/N)");
						status = br.readLine();

						if (status.equals("n") || status.equals("N")) {
							login = false;
						}
						break;

					case 2:
						System.out.println("Enter student id:");
						int sdaid1 = Integer.parseInt(br.readLine());

						ps = conn.prepareStatement("delete from students where sdaid=?");
						ps.setLong(1, sdaid1);

						if (ps.executeUpdate() > 0) {
							System.out.println(
									"==============================================================================");
							System.out.println("stunt ac delete successfully!!");
							System.out.println(
									"==============================================================================");

						} else {
							System.out.println(
									"==============================================================================");
							System.out.println("Problem in account closing!!");
							System.out.println(
									"==============================================================================");

						}
						System.out.println("Do you want to continue?(Y/N)");
						status = br.readLine();

						if (status.equals("n") || status.equals("N")) {
							login = false;
						}
						break;
					   case 3:
						   System.out.println("Enter student id:");
						   int id =  Integer.parseInt(br.readLine());
						   
							System.out.println("Enter student sdname:");
							String  sdname1 = br.readLine();


							ps = conn.prepareStatement("update students set sdname=? where sdaid=?");
							ps.setInt(2,id);
							ps.setString(1,sdname1) ;
							ps.executeUpdate();
							

							if (ps.executeUpdate() > 0) {
								System.out.println(
										"==============================================================================");
								System.out.println("stunt ac update successfully!!");
								System.out.println(
										"==============================================================================");

							} else {
								System.out.println(
										"==============================================================================");
								System.out.println("Problem in account closing!!");
								System.out.println(
										"==============================================================================");

							}
							System.out.println("Do you want to continue?(Y/N)");
							status = br.readLine();

							if (status.equals("n") || status.equals("N")) {
								login = false;
							}
						
						
					case 4:
						System.out.println("Enter the old password:");
						String oldPassword = br.readLine();

						System.out.println("Enter the new password:");
						String newPassword = br.readLine();

						System.out.println("Re-enter the new password:");
						String rePassword = br.readLine();

						ps = conn.prepareStatement("select * from admin where username=?");
						ps.setString(1, userName);

						result = ps.executeQuery();
						String existingPassword = null;
						while (result.next()) {
							existingPassword = result.getString("password");

						}

						if (existingPassword.equals(oldPassword)) {
							if (newPassword.equals(rePassword)) {
								ps = conn.prepareStatement("update admin set password=? where username=?");
								ps.setString(1, newPassword);
								ps.setString(2, userName);

								if (ps.executeUpdate() > 0) {
									System.out.println(
											"==============================================================================");
									System.out.println("Password changed successfully!!");
									System.out.println(
											"==============================================================================");

								} else {
									System.out.println(
											"==============================================================================");
									System.out.println("Problem in password changed!!");
									System.out.println(
											"==============================================================================");

								}

							} else {
								System.out.println(
										"==============================================================================");
								System.out.println("New password and retype password must be same!!");
								System.out.println(
										"==============================================================================");

							}
						} else {
							System.out.println(
									"==============================================================================");
							System.out.println("Old password is wrong!!");
							System.out.println(
									"==============================================================================");

						}
						System.out.println("Do you want to continue?(Y/N)");
						status = br.readLine();

						if (status.equals("n") || status.equals("N")) {
							login = false;
						}
						break;

				
						
					case 5:
						login = false;
						break;

					default:
						System.out.println("Wrong Choice!!");
						break;

					}

				} while (login);
				
				System.out.println("==============================================================================");
				System.out.println("Bye. Have a nice day!!");
				System.out.println("==============================================================================");
				

			} else {
				System.out.println("Enter a valid input!!");
			}
		}
	}
}

    
    
            
    
    




      
    		
                   
		 

