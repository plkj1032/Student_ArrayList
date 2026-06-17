package DAO;

import java.util.ArrayList;
import java.util.Scanner;

import DTO.StudentDTO;
import DTO.SubjectDTO;

public class StudentDAO {
	Scanner sc = new Scanner(System.in);
	
	ArrayList<StudentDTO> students = new ArrayList<>();
	
	public void menu_list()
	{
		String menu[] = {"학생 추가","학생 정보","과목 추가","종료"};
		
		for(int i = 0; i < menu.length; i++)
		{
			System.out.println(i+1 + menu[i]);
		}
	}
	
	public int check_num(Scanner sc)
	{
		while(true)
		{
			try
			{
				return Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e)
			{
				System.out.println("숫자만 입력하세요 !");
				return 0;
			}
		}
	}
	
	public void insertSt()
	{	
		StudentDTO st = new StudentDTO();
		
		System.out.print("학생 이름 입력 : ");
		String name = sc.nextLine();
		
		System.out.print("나이 입력 : ");
		int age = check_num(sc);
		
		st.setName(name);
		st.setAge(age);
		
		students.add(st);
	}
	public void printInfo()
	{
		for(int i = 0; i < students.size(); i++)
		{
			System.out.println("학생 이름 : " + students.get(i).getName()
					+ " 학생 나이 : " + students.get(i).getAge());
			
			if(students.get(i).getSubject() == null ||
					students.get(i).getSubject().isEmpty())
			{
				System.out.println("과목을 먼저 입력해주세요!");
				return;
			}
			
			for( int j = 0; j < students.get(i).getSubject().size(); j++)
			{
				System.out.println(
						(j + 1) + "번째 과목 : " + students.get(i).getSubject().get(j).getSub_name()
						+ " 점수 : " + students.get(i).getSubject().get(j).getSub_score());
			}
		}
	}
	public void insertSub()
	{
		System.out.print("학생 이름 입력 : ");
		String name = sc.nextLine();
		
		//StudentDTO target = null;
		
		boolean found = false;
		
		for(StudentDTO s : students)
		{
			if(s.getName().equals(name))
			{
				found = true;
				//target = s;
				break;
			}
			
			if(!found)
			{
				System.out.println("등록되지 않은 학생입니다!");
				return;
			}
		}
		
		System.out.print("과목 수 입력 : ");
		int sub_num = check_num(sc);
		
		
		//System.out.println("과목 : " + sub_name + "점수 : " + sub_score);
		
		for(int i = 0; i < students.size(); i++)
		{
			if(students.get(i).getName().equals(name))
			{
				for( int j = 0; j < sub_num; j++)
				{
					SubjectDTO subject = new SubjectDTO();
					
					System.out.print("과목 명 입력 : ");
					String sub_name = sc.nextLine();
					System.out.print("과목 점수 입력 : ");
					int sub_score = check_num(sc);
					
					subject.setSub_name(sub_name);
					subject.setSub_score(sub_score);
					
					students.get(i).getSubject().add(subject);
	//				students.get(i).getSubject().get(i).setSub_name(sub_name);
	//				students.get(i).getSubject().get(i).setSub_score(sub_score);
					
					System.out.println("과목 등록 완료!");
				}
			}
		}
		
	}
}
