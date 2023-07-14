package UniversitySystemGUI;

//AUTHOR: JARED KEKLAK (UNLESS ANNOTATED)
public class FormatCheck
{
	public FormatCheck()
	{

	}

	public boolean CourseNumber(String number)
	{
		if (number.length() != 3)
			return false;
		if (!number.matches("[0-9]+"))
			return false;

		return true;
	}

	public boolean CourseDept(String dept)
	{
		if (dept.length() != 4)
			return false;

		return true;
	}

}
