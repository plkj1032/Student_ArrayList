package DTO;

public class SubjectDTO {
	private String sub_name;
	private int sub_score;
	
	public SubjectDTO(){};
	
	public void setSub_name(String sub_name)
	{
		this.sub_name = sub_name;
	}
	
	public String getSub_name()
	{
		return sub_name;
	}
	
	public void setSub_score(int sub_score)
	{
		this.sub_score = sub_score;
	}
	
	public int getSub_score()
	{
		return sub_score;
	}
}
