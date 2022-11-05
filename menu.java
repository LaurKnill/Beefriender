package Beefriender;

public class menu {
	private String tb = "__________________________________________________________________________";
	
	public menu() {}
	
	public String getSpaces(String[] spaceList) {
		String spaces = " ";
		for (int i = 0; i < spaceList.length; i++) { spaces+= " "; }
		return spaces; }
	
	public void printMenu(String line) {
		int num = tb.length() - line.length();
		String sp = "";
		String[] spaceList = new String[num/2 - 1];
		if (line.length() % 2 != 0) { sp = " "; }
		System.out.print("|" + getSpaces(spaceList) + line + getSpaces(spaceList) + sp + "|\n");
	}
	public String getMenu(String line) {
		int num = tb.length() - line.length();
		String sp = "";
		String[] spaceList = new String[num/2 - 1];
		if (line.length() % 2 != 0) { sp = " "; }
		return "|" + getSpaces(spaceList) + line + getSpaces(spaceList) + sp + "|\n";
	}
	
	public boolean check(String input, String check) {
		if(input.compareTo(check) == 0 || input.compareTo(check.toUpperCase()) == 0 || input.compareTo(check.toLowerCase()) == 0 || input.compareTo(check.substring(0, 1).toUpperCase() + check.substring(1)) == 0 || input.compareTo(check.substring(0, 1).toUpperCase()) == 0 || input.compareTo(check.substring(0,1).toLowerCase()) == 0 ) { return true; }
		return false; }
}
