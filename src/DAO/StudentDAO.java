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
				"과목 추가","과목 수 출력","점수 변경","과목삭제",
				"학생 평균","학생별 평균","특정 과목 평균","총점 랭킹",
				"파일저장","파일로드","종료"};
		
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
		
		int idx = 0;
		
		for( int i = 0; i < students.size(); i++ )
		{			
			if(name.equals(students.get(i).getName()))
			{
				idx = i;
				target = students.get(i);
				break;
			}
		}
		
//		for(StudentDTO s : students)
//		{
//			if(s.getName().equals(name))
//			{
//				target = s;
//			}
//		}
		
		if(target == null)
		{
			System.out.println("등록되지 않은 학생!");
			return;
		}
		
		System.out.print("정보 변경 1번. 이름, 2번. 나이, 3번. 삭제 : ");
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
			case 3:
				students.remove(idx);
				System.out.println("삭제 완료!");
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
	
	public void deleteSubject(Scanner sc)
	{
		System.out.print("학생 이름 입력 : ");
		String name = sc.nextLine();
		
		boolean sub_found = false;
		
		int idx = 0;
		int sdx = 0;
		
		StudentDTO target = new StudentDTO();
		
		for(int i = 0; i < students.size(); i++)
		{
			if(name.equals(students.get(i).getName()))
			{
				idx = i;
				target = students.get(i);
			}
		}
		
		if(target == null)
		{
			System.out.println("등록되지 않은 학생입니다.");
			return;
		}
		
		System.out.println("과목 목록 : ");
		for(SubjectDTO sub : target.getSubject())
		{
			System.out.print(sub.getSub_name() + "\t");
		}
		
		System.out.print("\n삭제할 과목 선택 : ");
		String sub_name = sc.nextLine();
		
		for(int i = 0; i < target.getSubject().size(); i++)
		{
			if(sub_name.equals(target.getSubject().get(i).getSub_name()))
			{
				sub_found = true;
				sdx = i;
				break;
			}
		}
		
		if(!sub_found)
		{
			System.out.println("등록된 과목만 선택하세요!");
			return;
		}
		
		target.getSubject().remove(sdx);
	}
	
	public void sub_avg_score(Scanner sc)
	{
		System.out.print("학생 이름 입력 : ");
		String name = sc.nextLine();
		
		int sum = 0;
		
		StudentDTO target = new StudentDTO();
		
		for(StudentDTO s : students)
		{
			if(name.equals(s.getName()))
			{
				target = s;
			}
		}
		
		if(target == null)
		{
			System.out.println("등록되지 않은 학생!");
			return;
		}
		
		for(SubjectDTO sub : target.getSubject())
		{
			sum += sub.getSub_score();
		}
		
		double avg = sum / target.getSubject().size();
		
		System.out.println(target.getName() + "학생의 평균 : " + avg); 
	}
	
	public void all_stu_avg()
	{
		for(StudentDTO s : students)
		{
			int sum = 0;
			
			for(SubjectDTO sub : s.getSubject())
			{
				sum += sub.getSub_score();
			}
			
			double avg = sum / students.size();
			
			System.out.println(s.getName() + "의 평균 : " + avg);
		}	
	}
	
	public void each_sub_avg(Scanner sc)
	{
		ArrayList<String> sub_list = new ArrayList<>();
		
		System.out.print("과목 정보 : ");
		for(StudentDTO s : students)
		{
			for(SubjectDTO sub : s.getSubject())
			{
				if(!sub_list.contains(sub.getSub_name()))
				{
					sub_list.add(sub.getSub_name());
				}
			}
		}
		
		for(String s : sub_list)
		{
			System.out.print(s + "\t");
		}
		
		System.out.println();
		
		System.out.print("과목 입력 : ");
		String sub_name = sc.nextLine();
		
		int sum = 0;
		int count = 0;
		double avg = 0;
		
		for(StudentDTO s : students)
		{
			for(SubjectDTO sub : s.getSubject())
			{
				if(sub_name.equals(sub.getSub_name()))
				{
					sum += sub.getSub_score();
					count++;
				}
			}
		}
		
		if(count == 0)
		{
			System.err.println("등록되지 않은 과목입니다!");
			return;
		}
		
		avg = sum / count;
		
		System.out.println(sub_name + "의 평균은 : " + avg);
	}
	
	public int getTotalScore(StudentDTO st)
	{
		int sum = 0;
		
		for(SubjectDTO sub : st.getSubject())
		{
			sum += sub.getSub_score();
		}
		
		return sum;
	}
	
	public void sum_score_rank()
	{
		for(int i = 0; i < students.size() - 1; i++)
		{
			for(int j = i + 1; j < students.size(); j++)
			{
				if(getTotalScore(students.get(i)) 
						< getTotalScore(students.get(j)))
				{
					StudentDTO tmp = students.get(i);
					students.set(i,students.get(j));
					students.set(j, tmp);
				}
			}
		}
		
		for(int i = 0; i < students.size(); i++)
		{
			System.out.println((i + 1) + "등 "
	                + students.get(i).getName()
	                + " 총점 : "
	                + getTotalScore(students.get(i)));
		}
		
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
