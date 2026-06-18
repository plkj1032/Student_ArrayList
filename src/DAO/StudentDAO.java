package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import DTO.StudentDTO;
import DTO.SubjectDTO;

public class StudentDAO {
	Scanner sc = new Scanner(System.in);
	
	ArrayList<StudentDTO> students = new ArrayList<>();
	
	public void menu_list()
	{
		String menu[] = {"학생 추가","학생 정보","학생 정보 변경",
				"과목 추가","과목 수 출력","점수 변경","파일저장","파일로드","종료"};
		
		for(int i = 0; i < menu.length; i++)
		{
			System.out.println((i+1) + "." + menu[i]);
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
				System.out.println("등록된 과목이 없습니다.");
				continue;
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
		
		StudentDTO target = null;
		
		for(StudentDTO s : students)
		{
			if(s.getName().equals(name))
			{
				target = s;
				break;
			}
		}
		
		if(target == null)
		{
			System.out.println("등록되지 않은 학생입니다!");
			return;
		}
		
		System.out.print("과목 수 입력 : ");
		int sub_num = check_num(sc);
		
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
					
					//System.out.println("과목 등록 완료!");
				}
			}
		}
		
	}
	public void editStuInfo(Scanner sc)
	{
		System.out.print("변경할려는 학생 이름 : ");
		String name = sc.nextLine();
		
		StudentDTO target = null;
		
		for(StudentDTO s : students)
		{
			if(s.getName().equals(name))
			{
				target = s;
			}
		}
		
		if(target == null)
		{
			System.out.println("등록되지 않은 학생!");
			return;
		}
		
		System.out.print("정보 변경 1번. 이름, 2번. 나이 : ");
		int opt = check_num(sc);
		
		switch(opt)
		{
			case 1:
				System.out.print("변경할 이름 : ");
				String new_name = sc.nextLine();
				
				target.setName(new_name);
				break;
			case 2:
				System.out.println("변경할 나이 : ");
				int new_age = check_num(sc);
				
				target.setAge(new_age);
				break;
		}
	}
	
	public void subject_print()
	{
		for(int i=0; i< students.size(); i++)
		{
			System.out.println((i+1) + "번째 과목 수 : " + students.get(i).getSubject().size());		
		}
	}
	
	public void changeScore(Scanner sc)
	{
		System.out.print("학생 이름 : ");
		String name = sc.nextLine();
		
		StudentDTO target = null;
		
		for(StudentDTO s : students)
		{
			if(name.equals(s.getName()))
			{
				target = s;
				break;
			}
		}

		if(target == null)
		{
			System.out.println("등록되지 않은 학생!");
			return;
		}
		
		System.out.print("과목 정보 : ");
		for( int i = 0; i < target.getSubject().size(); i++)
		{
			System.out.print(target.getSubject().get(i).getSub_name() + " ");
		}
		System.out.println();
		
		System.out.print("점수를 변경할 과목 이름 : ");
		String sub_name = sc.nextLine();
		
		SubjectDTO targetSub = null;
		
		for(SubjectDTO sub : target.getSubject())
		{
			if(sub_name.equals(sub.getSub_name()))
			{
				targetSub = sub;
				break;
			}
		}
		
		if(targetSub == null)
		{
			System.out.println("과목 정보에 있는 과목 중 골라주세요");
			return;
		}
		
		System.out.print("변경할 점수 : ");
		int sub_score = check_num(sc);
		
		targetSub.setSub_score(sub_score);
	}
	
	public void saveToFile()
	{
		try(FileWriter fw = new FileWriter("students.txt"))
		{
			for(StudentDTO st : students)
			{
				StringBuilder sb = new StringBuilder();
				
				// 학생 이름, 나이 저장
				sb.append(st.getName());
				sb.append(",");
				sb.append(st.getAge());
				
				// 과목 저장 
				for(SubjectDTO sub : st.getSubject())
				{
					sb.append(",");
					sb.append(sub.getSub_name());
					sb.append(":");
					sb.append(sub.getSub_score());
				}
				fw.write(sb.toString() + "\n");
			}
			System.out.println("저장 완료");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void LoadToFile(){
		try(BufferedReader br = new BufferedReader(new FileReader("students.txt"))){
			
			String line;
			
			while((line = br.readLine()) != null)
			{
				String arr[] = line.split(",");
				
				//arr[0] => st_name;
				//arr[1] => 수학:90
				//arr[2] => 과학:70 ... arr[n]
				StudentDTO s = new StudentDTO();
				
				s.setName(arr[0]);
				s.setAge(Integer.parseInt(arr[1]));
				
				for(int i=2; i<arr.length; i++)
				{
					String arr2[] = arr[i].split(":");
					//arr2[0] = 수학
					//arr2[1] = 90 ( String )
					String sub_name = arr2[0];
					int sub_score = Integer.parseInt(arr2[1]);
					
					SubjectDTO sub = new SubjectDTO();
					sub.setSub_name(sub_name);
					sub.setSub_score(sub_score);
					
					s.getSubject().add(sub);
				}
				students.add(s);
			}
			System.out.println("로드 완료!");
		}catch(IOException e){
			System.out.println("저장된 파일이 없습니다.");
		}
	}
}
