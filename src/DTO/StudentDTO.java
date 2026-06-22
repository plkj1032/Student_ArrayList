package DTO;

import java.util.ArrayList;

public class StudentDTO {
	private String name;
	private int age;
	private ArrayList<SubjectDTO> subjects = new ArrayList<>();
	
	public StudentDTO () {};
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return age;
	}
	
	public ArrayList<SubjectDTO> getSubject()
	{
		return subjects;
	}
	
}
