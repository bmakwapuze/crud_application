package zw.co.financialparadise;
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.util.logging.Logger;

public class Account {
	static final Logger log = Logger.getLogger(String.valueOf(Account.class));
	String url = "jdbc:mysql://localhost:3306/Account?useSSL=false";
	String userName = "root";
	String psw = "B@0783369391m1";
	String query;

	public static void main(String[] args) {
		Account user = new Account();
		Scanner input = new Scanner(System.in);

		while (true) {
			log.info("Select Option");
			log.info("1-----to enter new record \n" + "2-----to view record \n" + "3-----to update record \n"
					+ "4-----delete a record \n" + "-----------------------------------------------------------------");
			try {
				int userInput = input.nextInt();
				if (userInput == 1) {

					try {
						user.create();
					} catch (Exception e) {

						log.severe("got exception");
					}

				} else if (userInput == 2) {
					try {
						user.read();
					} catch (Exception e) {
						log.severe("got exception");
					}

				}

				else if (userInput == 3) {

					try {
						user.update();
					} catch (Exception e) {
						log.severe("got exception");

					}

				} else if (userInput == 4) {

					try {
						user.delete();
					} catch (Exception e) {

						log.severe("got exception");

					}

				} else {
					log.info("incorrect input");

				}

			} catch (Exception e) {
				log.severe("got exception");

			}

		}

	}

	public void create() {
		Scanner input = new Scanner(System.in);
		log.info("enter email: ");
		String email = input.nextLine();

		log.info("enter first name: ");
		String fName = input.nextLine();

		log.info("enter surname: ");
		String surname = input.nextLine();
		log.info("enter date of birth: ");
		String dateOfBirth = input.nextLine();
		log.info("enter phone number: ");
		String phoneNumber = input.nextLine();
		log.info("enter address: ");
		String address = input.nextLine();

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, userName, psw);
			query = "INSERT INTO details (email,firstName,surname,dateOfBirth,PhoneNumber,address )"
					+ "VALUES(?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, email);
			preparedStmt.setString(2, fName);
			preparedStmt.setString(3, surname);
			preparedStmt.setString(4, dateOfBirth);
			preparedStmt.setString(5, phoneNumber);
			preparedStmt.setString(6, address);

			preparedStmt.execute();
			log.info("data added successfully"
					+ "---------------------------------------------------------------------------------");

			con.close();

		} catch (Exception e) {

			log.severe("got exception");

		}

	}

	public void read() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, userName, psw);
			Statement st = con.createStatement();
			query = "SELECT * FROM details";
			st.executeQuery(query);
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {

				log.info(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4)
						+ "  " + rs.getString(5) + "  " + rs.getString(6));
			}
			log.info("" + "*******************************************************************");

		} catch (Exception e) {
			log.severe("got exception");

		}
	}

	public void update() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, userName, psw);

			Scanner input = new Scanner(System.in);
			Scanner option = new Scanner(System.in);
			log.info("enter the email of the record  to be updated: ");
			String email = input.nextLine();
			log.info("1-----to change address \n" + "2-----to change phone number");
			int opt = option.nextInt();

			String query;
			if (opt == 1) {

				log.info("enter new  address:");
				String newAddress = input.nextLine();

				query = "UPDATE details SET address= ? where email = ?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, newAddress);
				preparedStmt.setString(2, email);
				preparedStmt.execute();
				System.out
						.println("address updated \n" + "____________________________________________________________");

			} else if (opt == 2) {
				log.info("enter new  phone number:");
				String phoneNumber = input.nextLine();

				query = "UPDATE details SET phoneNumber= ? where email = ?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, phoneNumber);
				preparedStmt.setString(2, email);
				preparedStmt.execute();
				log.info("phone number updated \n" + "____________________________________________________________");

			} else {
				log.info("incorrect input");
			}

		} catch (Exception e) {

			log.severe("got exception");

		}

	}

	public void delete() {

		try {
			Scanner input = new Scanner(System.in);
			log.info("enter name: ");
			String name = input.nextLine();

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, userName, psw);
			query = "DELETE FROM details WHERE email = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.execute();
			log.info("/n" + "row removed" + "-----------------------------------------------------------------/n"
					+ "/n");

		} catch (Exception e) {
			log.severe("got exception");

		}

	}
}
