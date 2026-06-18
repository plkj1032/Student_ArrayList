package MAIN;

import java.util.Scanner;
import DAO.StudentDAO;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean check = true;
		
		StudentDAO dao = new StudentDAO();
		
		Scanner sc = new Scanner(System.in);
		
		while(check != false)
		{
			System.out.println("옵션 선택");
			
			dao.menu_list();
			
			int opt = dao.check_num(sc);
			
			switch(opt)
			{
				case 1 :
					dao.insertSt();
					break;
				case 2 :
					dao.printInfo();
					break;
				case 3 :
					dao.editStuInfo(sc);
					break;
				case 4 :
					dao.insertSub();
					break;
				case 5 :
					dao.subject_print();
					break;
				case 6:
					dao.changeScore(sc);
					break;
				case 7 :
					dao.saveToFile();
					break;
				case 8 :
					dao.LoadToFile();
					break;
				case 9 :
					check = false;
					break;
			}
		}
	}

}
