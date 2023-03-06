package in.ineuron.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;

public class TestApp {

	public static void main(String[] args) throws Exception {
		System.out.println("WELCOME TO STUDENT DATA MANAGEMENT APP");
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. EXIT");
			System.out.print("ENTER UR CHOICE, PRESS[1/2/3/4/5]::  ");
			String option = br.readLine();

			switch (option) {
			case "1":
				insertOperation();
				break;
			case "2":
				selectOperation();
				break;
			case "3":
				updateOperation();
				break;
			case "4":
				deleteOperation();
				break;
			case "5":
				System.out.println("******* Thanks for using the application *****");
				System.exit(0);
			default:
				System.out.println("Invalid option plz try agin with valid options....");
				break;
			}

		}
	}
	
	public static void deleteOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the student id :: ");
		Integer sid = scanner.nextInt();
		
		String msg = studentService.deleteStudent(sid);
		
		if(msg.equalsIgnoreCase("success")) {
			System.out.println("Record deleted successfully...");
		} else {
			System.out.println("Record deletikon failed...");
		}
	}
	
	public static void updateOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter id of student :: ");
		Integer sid = scanner.nextInt();
		
		Student std = studentService.searchStudent(sid);
		
		System.out.print("The old name of student is :: " +std.getSname()+ " Enter the new name of student :: ");
		String sname = scanner.next();
		
		System.out.print("The old age of student is :: " +std.getSage()+ " Enter the new age of student :: ");
		Integer sage = scanner.nextInt();
		
		System.out.print("The old address of student is :: " +std.getSaddress()+ " Enter the new address of student :: ");
		String saddress = scanner.next();
		
		String msg = studentService.updateStudent(sid, sname, sage, saddress);
		
		if(msg.equalsIgnoreCase("success")) {
			System.out.println("Record inserted successfully... ");
		} else {
			System.out.println("Record insertion failed...");
		}
	}
	public static void selectOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService(); 
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Student Id :: ");
		Integer sid = scanner.nextInt();
		
		Student std = studentService.searchStudent(sid);
		
		if(std != null) {
			System.out.println("NAME\tAGE\tADDRESS");
			System.out.println(std.getSname() +"\t"+ std.getSage() +"\t"+ std.getSaddress());
		} else {
			System.out.print("Record not found for student id :: "+sid);
		}
	}
	
	public static void insertOperation() {
		IStudentService studentService = StudentServiceFactory.getStudentService();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the name of student :: ");
		String sname = scanner.next();
		
		System.out.print("Enter the age of student ");
		Integer sage = scanner.nextInt();
		
		System.out.print("Enter the address of student :: ");
		String saddress = scanner.next();
		
		String msg = studentService.addStudent(sname, sage, saddress);
		
		if(msg.equalsIgnoreCase("success")) {
			System.out.println("Record inserted successfully...");
		} else {
			System.out.println("Record insertion failed...");
		}
	}

}
