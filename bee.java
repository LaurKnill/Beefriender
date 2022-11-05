package Beefriender;

public class bee {
	private int honeydrops = 0; //currency
	private int partygear = 0; //health potions
	private int joy = 0; //health
	private int maxJoy = 0; //maximum health
	private int level = 0;
	private int exp = 0;
	private int maxExp = 0;
	
	//CONSTRUCTOR
	public bee(int beeLevel) {
		honeydrops = 0;
		partygear = 3;
		level = beeLevel;
		maxJoy = (level * 100) + ((level / 2) * 50);
		joy = maxJoy;
		exp = 0;
		maxExp = (int) (maxJoy * 1.5);
	}
	
	
	//GET METHODS
	public int getHoneydrops() {
		return honeydrops; }
	public int getPartygear() {
		return partygear; }
	public int getJoy() {
		return joy; }
	public int getMaxJoy() {
		return maxJoy; }
	public int getLevel() {
		return level; }
	public int getExp() {
		return exp; }
	public int getMaxExp() {
		return maxExp; }
	
	//ADD METHODS	
	public void addHoneydrops(int addThis) {
		honeydrops += addThis; }
	public void addPartygear(int addThis) {
		partygear += addThis; }
	public void addJoy(int addThis) {
		joy += addThis; }
	public void addExp(int addThis) {
		exp += addThis; }
	
	//REMOVE METHODS
	public void remHoneydrops(int remThis) {
		honeydrops -= remThis; }
	public void remJoy(int remThis) {
		joy -= remThis;
		if (joy < 0) {
			joy = 0; } }
	
	//TOSTRING METHODS
	public String stJoy() { return joy + "/" + maxJoy; }
	public String barJoy() {
		double fullBars = (double) (joy) / (double) (maxJoy); //decimal version of health out of 1; e.g. 80/100 = 0.8
		double remBars = 1 - fullBars; //Remaining bars (if 80/100 -> 0.8, remBars would be 0.2)
		String returnThis = "";
		for (double i = fullBars; i >= 0.05; i -= 0.05) { returnThis += "o"; }
		for (double i = remBars; i >= 0.05; i-= 0.05) { returnThis += "-"; }
		return returnThis; }
	public String stExp() { return exp + "/" + maxExp; }
	public String barExp() {
		double fullBars = (double) (exp) / (double) (maxExp); 
		double remBars = 1 - fullBars;
		String returnThis = "";
		for (double i = fullBars; i >= 0.05; i -= 0.05) { returnThis += "o"; }
		for (double i = remBars; i >= 0.05; i-= 0.05) { returnThis += "-"; }
		return returnThis; }
	
	//ACTION METHODS
	public int event() {
		int returnThis = (int) (Math.random() * 10);
		return returnThis; }
	public void dance(int dances) {
		partygear -= dances;
		int newjoy = (dances * 40) + joy;;
		if (newjoy > maxJoy) {
			joy = maxJoy; }
		if (newjoy <= maxJoy) {
			joy = newjoy;	} }
	public void levelUp() {
		level++;
		maxJoy = (level * 100) + ((level / 2) * 50);
		joy = maxJoy;
		exp = 0;
		maxExp = (int) (maxJoy * 1.5); }
//This attacks againstBee
	public int damage(bee againstBee) {
		int damage = (int) ((Math.random() * (maxJoy/3)) + 2);
		return damage; }
//Returns true if bee is alive, false if they have no more health (joy)
	public boolean checkLife() {
		if (joy <= 0) {
			return false; }
		return true; }
//Kills bee
	public void endBee() {
		joy = 0; }
}
