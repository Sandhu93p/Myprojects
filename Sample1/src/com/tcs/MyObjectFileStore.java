package com.tcs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class MyObjectFileStore {

	public void storeObject(Book book) {

		OutputStream ops = null;
		ObjectOutputStream objOps = null;
		try {
			ops = new FileOutputStream("Mybooks.txt");
			objOps = new ObjectOutputStream(ops);
			objOps.writeObject(book);
			objOps.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (objOps != null)
					objOps.close();
			} catch (Exception ex) {

			}
		}

	}

	public static class FileSearch {

		public void parseFile(String fileName, String searchStr) throws FileNotFoundException {
			Scanner scan = new Scanner(new File(fileName));
			while (scan.hasNext()) {
				String line = scan.nextLine().toLowerCase().toString();
				if (line.contains(searchStr)) {
					System.out.println(line);
				}
			}
		}
	}

	public void displayObjects() {

		InputStream fileIs = null;
		ObjectInputStream objIs = null;
		try {
			fileIs = new FileInputStream("Mybooks.txt");
			objIs = new ObjectInputStream(fileIs);
			Book book = (Book) objIs.readObject();
			System.out.println(book);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (objIs != null)
					objIs.close();
			} catch (Exception ex) {

			}
		}

	}

	public static void main(String a[]) {
		MyObjectFileStore mof = new MyObjectFileStore();
		Book b1 = new Book("Alchemist", 1, "100");
		Book b2 = new Book("Indias ancient past", 2, "250");
		Book b3 = new Book("Indian polity",3,"300");
		mof.storeObject(b1);
		mof.storeObject(b2);
		mof.storeObject(b3);
		mof.displayObjects();
		FileSearch fileSearch = new FileSearch();
		try {
			fileSearch.parseFile("Mybooks.txt", "alchemist");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
}

class Book implements Serializable {

	private String name;
	private int id;
	private String amount;

	public Book(String name, int id, String amount) {
		this.name = name;
		this.id = id;
		this.amount = amount;
	}

	public String toString() {
		return name + " ID : " + id + " Rate : " + amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getamount() {
		return amount;
	}

	public void setSalary(String amount) {
		this.amount = amount;
	}
}
