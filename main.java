package Beefriender;
import java.util.Scanner;

public class main {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		String input = "";
		int inputInt = 0;
		String top = " __________________________________________________________________________\n";
		String bot = "|__________________________________________________________________________|";
		menu m = new menu();
		
		bee player = new bee(2);
		bee enebee = new bee(0);
		int friendsMade = 0;
		
//Unlockables
		boolean uVisit = false;
		
		System.out.println(" __________________________________________________________________________");
		System.out.println("|             ***.-'-.-'-.-'-.-'-.-'-.-'-.-'-.-'-.-'-.-'-.-'-.-            |");
		System.out.println(m.getMenu("Welcome to Beefriender! You are currently a level 1 bee.")
				+         m.getMenu("Would you like to see the instructions? (Y/N)")
				+         bot);
		input = in.next();
		if (input.compareTo("Y") == 0 || input.compareTo("y") == 0) {
			System.out.println(" __________________________________________________________________________");
			System.out.println("|            ***.-'-.-'-.-'-.-'-.-'-.-'-.-'-.-'-.-'-.-'-.-'-.-             |");
			System.out.println(m.getMenu("In Beefriender, you're a bee who has to go around befriending")
		             +  m.getMenu("other bees. Find bees to befriend by wandering the land using")
		             +  m.getMenu("the Explore action; but beware, the bees you'll find are very")
		             +  m.getMenu("grumpy, and their rudeness will decrease your Joy. If")
		             +  m.getMenu("you run out of joy, you'll be too sad to make friends,")
		             +  m.getMenu("and it's game over! Raise your Joy by Dancing using")
		             +  m.getMenu("Partygear, which you can purchase from the Shop for 20 Honey-")
		             +  m.getMenu("drops. Find Honeydrops while exploring or as gifts from friends.")
		             +  m.getMenu("That's all! Goodluck Beefriending!")
		             +  m.getMenu("You've been given 3 Partygear to start with.")
		             + bot);
		}
		else {
			System.out.println(top
							+ m.getMenu("")
					       + m.getMenu("Then let's begin!")
					       + m.getMenu("You've been given 3 Partygear to start with.")
					       + bot);
		}
		
		while (player.checkLife() == true) {
			if (player.getLevel() >= 3) { uVisit = true; }
			
			//Each turn
			System.out.print(top
							+ m.getMenu("[ NEW TURN ]")
					        + m.getMenu("What would you like to do?")
					        + m.getMenu("Enter an action or an action's first letter."));
			if (uVisit == true) { System.out.println(m.getMenu("Explore   Shop   Dance   Check   Visit   Help   Leave")+bot); }
			else { System.out.println(m.getMenu("Explore      Shop      Dance      Check      Help      Leave")+bot); }
			
			input = in.next();
//ActionExplore
			if (m.check(input, "Explore")) {
				int event = player.event();
				System.out.print(top
								+ m.getMenu("***-.-'-.-'-[ EXPLORE ]-.-'-.-'-.-")
						       +  m.getMenu("You go out exploring and..."));
	//found Partygear
				if (event == 0) {
					player.addPartygear(1);
					System.out.println( m.getMenu("You found a Partygear! You now have " + player.getPartygear() + " Partygear!")
							+ bot);}
	//found Honeydrops
				if (event > 0 && event <= 4) {
					int find = (int) ((Math.random() * 5) + 5);
					player.addHoneydrops(find);
					System.out.println(m.getMenu("you found " + find + " honeydrops!")
					+ m.getMenu("You now have " + player.getHoneydrops() + " honeydrops.")
					+ bot); }
	//found bee			
				if (event > 4 && event <= 7) {
					int enebeeLevel = (int) ((Math.random() * player.getLevel() + (player.getLevel() /2) + 1 ));
					enebee = new bee(enebeeLevel);
					System.out.println(m.getMenu("You found a level " + enebee.getLevel() + " bee!")
									+ m.getMenu("Attempt to befriend? (Y/N)")
									+ bot);
					input = in.next();
	   //Bee engagement
					if (m.check(input, "Yes")) {
						while (player.checkLife() == true && enebee.checkLife() == true) {
							boolean enebeeAttacks = false;
							boolean end = false;
							System.out.println(top
											  + m.getMenu("-'.-'-.***        ***.-'-.-'-")
											  + m.getMenu(enebee.barJoy())
										      + m.getMenu("Enemy Grouchiness: " + enebee.stJoy())
										      + m.getMenu("")
											  + m.getMenu("You are attempting to befriend an enemy bee!")
											  + m.getMenu("What would you like to do?")
											  + m.getMenu("Befriend      Dance      Leave")
											  + m.getMenu("")
											  + m.getMenu("Your Joy: " + player.stJoy())
											  + m.getMenu(player.barJoy())
											  + bot);
							String input1 = in.next();
							if (m.check(input1, "Befriend")) {
								enebeeAttacks = true;
								int damage = player.damage(enebee);
								enebee.remJoy(damage);
								int statement = (int) (Math.random() * 2);
								if (enebee.getJoy() == 0) {
									System.out.println(" __________________________________________________________________________");
									m.printMenu("-'.-'-.***   <3   ***.-'-.-'-"); }
								else {
									System.out.print(top
											+ m.getMenu("")
											+ m.getMenu(enebee.barJoy())
											+ m.getMenu("Enemy Grouchiness: " + enebee.stJoy())
											+ m.getMenu(""));}
								if (statement == 0) {
									m.printMenu("You complimented the enemy bee and removed " + damage + " Grouchiness!"); }
								if (statement == 1) {
									m.printMenu("You told the enemy bee a funny joke and removed " + damage + " Grouchiness!"); }
								m.printMenu("");
			//Tests if the enebee is alive
								if (enebee.getJoy() == 0) {
									enebeeAttacks = false;
									end = true;
									friendsMade++;
									int exp = (int) (Math.random() * (enebee.getLevel() * 50) + (enebee.getLevel() * 40));
									int loot = (int) ((Math.random() * (enebee.getLevel() * 10)) + (enebee.getLevel() * 3));
									player.addHoneydrops(loot);
									System.out.print(m.getMenu("Congratulations! You've befriended the enemy bee!")
											+ m.getMenu("They've given you " + loot + " Honeydrops as a gift!")
											+ m.getMenu(" ")
											+ m.getMenu("You've gained " + exp + " exp!"));
									if (player.getExp() + exp >= player.getMaxExp()) {
										int remainder = (player.getExp() + exp) - player.getMaxExp();
										player.levelUp();
										player.addExp(remainder);
										System.out.print(m.getMenu("Whoa, that's enough to level up!")
												+ m.getMenu("You are now level "  + player.getLevel() + " and have " + player.stExp() + " Exp.")
												+ m.getMenu("Your joy has been reset to full!")); }
									else {
										player.addExp(exp);}
								}
								
							}
							if (m.check(input1, "Dance")) {
								if (player.getPartygear() == 0) {
									m.printMenu("You attempted to dance but you didn't have any Partygear!");
									m.printMenu("");}
								else {
									enebeeAttacks = true;
									player.dance(1);
									System.out.print(m.getMenu("You danced once and gained 40 Joy!")
											+ m.getMenu("You now have " + player.stJoy() + " Joy and " + player.getPartygear() + " Partygear.")); }}
							if (m.check(input1, "Leave")) {
								System.out.print(top
									+ m.getMenu("")
									+ m.getMenu("You leave the bee alone.")); 
								enebee.endBee(); } //engagement loop ends
			//Enebee Attacks
							if ( enebeeAttacks == true ) {
								int damage = enebee.damage(player);
								//Weakens enebee damage.
								damage = (int) (damage * 0.6);
								player.remJoy(damage);
								int statement = (int) (Math.random() * 2);
								if (statement == 0) { m.printMenu("The enemy bee was very rude to you and you lost " + damage + " Joy!");}
								if (statement == 1) { m.printMenu("The enemy bee said something mean and you lost " + damage + " Joy!"); }
								if (player.getJoy() <= 0) {
								m.printMenu("Oh no, now you're too upset to make any friends! Game over.");
								player.endBee(); }
							}
							System.out.print(m.getMenu("")
										+ m.getMenu("Your Joy: " + player.stJoy())
										+ m.getMenu(player.barJoy()));
							if (end == true) { System.out.print(m.getMenu("Your Exp: " + player.stExp()) + m.getMenu(player.barExp()));}
							System.out.println(bot);
						} //End of engagement loop
					}
				}
				if (event > 7 && event <= 9) { //found nothing
					System.out.println(m.getMenu("didn't find anything!")
							+ bot); }
			}
//ActionShop
			if (m.check(input, "Shop")) {
				System.out.print(top
							  + m.getMenu("$$$  ***-.-'-.-")
							  + m.getMenu("Welcome to the shop! You have " + player.getHoneydrops() + " Honeydrops.")
							  + m.getMenu("Would you like to purchase Partygear for 20 Honeydrops each? (Y/N)")
							  + bot);
				input = in.next();
				System.out.print(top + m.getMenu("[ SHOP ]"));
	//ActionPurchase
				if (m.check(input, "Yes")) {
					if (player.getHoneydrops() < 20){
						m.printMenu("You don't have enough Honeydrops! You only have " + player.getHoneydrops() + "."); }
					else {
						System.out.print(m.getMenu("How many Partygear would you like to purchase?")
								+ m.getMenu("Each Partygear costs 20 Honeydrops, and you have " + player.getHoneydrops() + ".")
								+ m.getMenu("You can buy a maximum of " + player.getHoneydrops()/20 + " Partygear.")
								+ bot);						
						inputInt = in.nextInt();
						System.out.print(top + m.getMenu("[ SHOP ]"));
								if ((inputInt * 20) <= player.getHoneydrops()) {
									player.remHoneydrops(inputInt * 20);
									player.addPartygear(inputInt);
									System.out.print(m.getMenu("You purchased " + inputInt + " Partygear!")
												 +  m.getMenu("You now have " + player.getPartygear() + " Party gear and " + player.getHoneydrops() + " Honeydrops."));
								}
								else if((inputInt*20) >= player.getHoneydrops()) {
									System.out.print(m.getMenu("You don't have enough Honeydrops to purchase " + inputInt + " Partygear!")
											+ m.getMenu("You only have " + player.getHoneydrops() + ", and need " + ((inputInt * 20)-player.getHoneydrops() + " more.")));
								}
						} }
				if (m.check(input, "No")) { m.printMenu("Okiedokie!"); }
				System.out.println(bot);
			}
//ActionDance
			if (m.check(input, "Dance")) {
				if (player.getPartygear() == 0) {
					System.out.println(top + m.getMenu("[ DANCE ]")
							+ m.getMenu("You don't have any Partygear to dance with!")
							+ bot); }
				else {
					System.out.println(top  + m.getMenu("[ DANCE ]")
							+ m.getMenu("How many dances would you like to perform? (Numbers only).")
							+ m.getMenu("")
							+ m.getMenu("Each dance takes 1 Partygear and gives 40 Joy.")
							+ m.getMenu("You have " + player.stJoy() + " Joy and "+ player.getPartygear() + " Partygear.")
							+ m.getMenu(player.barJoy())
							+ bot);
					inputInt = in.nextInt();
					if (inputInt > player.getPartygear()) {
						System.out.println(top
								+ m.getMenu("[ DANCE ]")
								+ m.getMenu("You don't have enough Partygear to dance that much!")
								+ m.getMenu("You wanted to dance " + inputInt + " times, but you only have " + player.getPartygear() + " Partygear.")
								+ bot);}
					else if (inputInt <= 0) {
						System.out.println(top
								+ m.getMenu("[ DANCE ]")
								+ m.getMenu("Please enter a positive number if you'd like to dance!")
								+ bot); }
					else {
						player.dance(inputInt);
						if (inputInt == 1) {
							System.out.println(top
									+ m.getMenu("[ DANCE ]")
									+ m.getMenu("You danced 1 time and gained 40 joy!")
									+ m.getMenu("You now have " + player.stJoy() + " Joy and " + player.getPartygear() + " Partygear.")
									+ m.getMenu(player.barJoy())
									+ bot); }
						else {
							System.out.println(top
									+ m.getMenu("[ DANCE ]")
									+ m.getMenu("You danced " + inputInt + " times and gained " + (inputInt * 40) + " Joy!" )
									+ m.getMenu("You now have " + player.stJoy() + " Joy and " + player.getPartygear() + " Partygear.")
									+ m.getMenu(player.barJoy())
									+ bot); }
					}
				}
				
			}
//ActionCheck
			if (m.check(input, "Check")) {
				System.out.println(top
						+ m.getMenu("[ CHECK ]")
						+ m.getMenu("What would you like to check?")
						+ m.getMenu("Enter a name or the first letter of the name.")
						+ m.getMenu("Honeydrops    Partygear    Joy    Level    Exp    All    Nothing")
						+ bot);
				String input1 = in.next();
				if (m.check(input1, "Honeydrops")) {
					System.out.println(top
							+ m.getMenu("[ CHECK ]")
							+ m.getMenu("You have " + player.getHoneydrops() + " honeydrops.")
							+ bot);}
				if (m.check(input1, "Partygear")) {
					System.out.println(top
							+ m.getMenu("[ CHECK ]")
							+ m.getMenu("You have " + player.getPartygear() + " Partygear.")
							+ bot); }
				if (m.check(input1, "Joy")) {
					System.out.println(top
							+ m.getMenu("[ CHECK ]")
							+ m.getMenu("You have " + player.stJoy() +  " joy.")
							+ bot); }
				if (m.check(input1, "Level")) {
					System.out.println(top
							+ m.getMenu("[ CHECK ]")
							+ m.getMenu("You are level " + player.getLevel() + ".")
							+ bot); }
				if (m.check(input1, "exp")) {
					System.out.println(top
							+ m.getMenu("[ CHECK ]")
							+ m.getMenu("You currently have " + player.stExp() + " exp")
							+ bot); }
				if (m.check(input1, "All")) {
					System.out.println(top
							+ m.getMenu("[ CHECK ]")
							+ m.getMenu("Your stats are:")
							+ m.getMenu("Level: " + player.getLevel())
							+ m.getMenu("Joy: " + player.stJoy())
							+ m.getMenu(player.barJoy())
							+ m.getMenu("Exp: " + player.stExp())
							+ m.getMenu(player.barExp())
							+ m.getMenu("Honeydrops: " + player.getHoneydrops())
							+ m.getMenu("Partygear: " + player.getPartygear())
							+ m.getMenu("Friends made: " + friendsMade)
							+ bot); }
				if (m.check(input1, "Nothing")) {
					System.out.println(top 
							+ m.getMenu("[ CHECK ]")
							+ m.getMenu("You stare into the infinite abyss.")
							+ bot); }
				
			}
//ActionVisit
			if (m.check(input, "Visit")){  
				System.out.println(top
						+ m.getMenu("Would you like to visit one of your friends? (Y/N)")
						+ m.getMenu("They might give you Joy or a gift of Honeydrops,")
						+ m.getMenu("but you might get sad if they're not around or in a bad mood!")
						+ bot); 
				String input1 = in.next();
				if (m.check(input1, "Yes")) {
					int event = (int) (Math.random() * 4);
					if (event == 0) { //event 0
						int reaction = (int) ((Math.random() * (player.getJoy()/4)) + 10);
						player.addJoy(reaction);
						System.out.println(top
								+ m.getMenu("The friend you visited was very happy to see you!")
								+ m.getMenu("You had a nice visit and gained " + reaction + " Joy!")
								+ m.getMenu("")
								+ m.getMenu("You now have " + player.stJoy() + " Joy.")
								+ m.getMenu(player.barJoy())
								+ bot); }
					if (event == 1) { //event 1
						int reaction = (int) ((Math.random() * player.getLevel()*5)) + 4;
						player.addHoneydrops(reaction);
						System.out.println(top
								+ m.getMenu("The friend you visited was very happy to see you!")
								+ m.getMenu("They gave you a gift of " + reaction + " Honeydrops.")
								+ m.getMenu("")
								+ m.getMenu("You now have " + player.getHoneydrops() + " Honeydrops.")
								+ bot);  }
					if (event == 2) { //event 2
						int reaction = (int) ((Math.random() * player.getLevel()*5)) + 1;
						player.remJoy(reaction);
						System.out.println(top
								+ m.getMenu("The friend you visited was in a bad mood!")
								+ m.getMenu("They made you upset and you lost " + reaction + " Joy.")
								+ m.getMenu("")
								+ m.getMenu("You now have " + player.stJoy() + " Joy.")
								+ m.getMenu(player.barJoy())
								+ bot);  } 
					if (event == 3) { //event 3
						int reaction = (int) ((Math.random() * player.getLevel()*3)) + 1;
						player.remJoy(reaction);
						System.out.println(top
								+ m.getMenu("The friend you wanted to visit wasn't home!")
								+ m.getMenu("You were disappointed and lost " + reaction + " Joy.")
								+ m.getMenu("")
								+ m.getMenu("You now have " + player.stJoy() + " Joy.")
								+ m.getMenu(player.barJoy())
								+ bot);  }   
				}
				}
//ActionHelp
			if (m.check(input, "Help")) {
				System.out.println(top
						+ m.getMenu("[ HELP ]")
						+ m.getMenu("What do you need help with?")
						+ m.getMenu("Actions      Stats      Items      Nevermind")
						+ bot);
				String input1 = in.next();
		//HelpAction
				if (m.check(input1, "Actions") || m.check(input1, "Action")) {
					System.out.println(top
							+ m.getMenu("[ HELP ]")
							+ m.getMenu("What action do you need help with?")
							+ m.getMenu("Explore      Shop      Dance      View      Home")
							+ bot);
					String input2 = in.next();
					if (m.check(input2, "Explore")) {
						System.out.println(top
								+ m.getMenu("[ HELP ]")
								+ m.getMenu("Explore the land! You may find a bee")
								+ m.getMenu("to befriend, or happen on some loot.")
								+ bot); }
					if (m.check(input2, "Shop")) {
						System.out.println(top
								+ m.getMenu("[ HELP ]")
								+ m.getMenu("Buy partygear from the shop!")
								+ m.getMenu("Partygear is used to Dance, which increases your Joy.")
								+ bot); }
					if (m.check(input2, "Dance")) {
						System.out.println(top
								+ m.getMenu("[ HELP ]")
								+ m.getMenu("Dance to regain Joy!")
								+ m.getMenu("Each dance uses 1 partygear and gives 40 joy.")
								+ bot); }
					if (m.check(input2, "Check")) {
						System.out.println(top
								+ m.getMenu("[ HELP ]")
								+ m.getMenu("View any of your stats or items!")
								+ bot); }
					if (m.check(input2, "Home")) {
						System.out.println(top
								+ m.getMenu("[ HELP ]")
								+ m.getMenu("Return Home to your hive!") 
								+ m.getMenu("Be careful, this will end the game!")
								+ bot); }
				} //end Action Inquiry
		//HelpStats
				if (m.check(input1, "Stats")) {
					System.out.println(top
							+ m.getMenu("[ HELP ]")
							+ m.getMenu("You can view your stats using the View action.")
							+ m.getMenu("What stats do you need help with?")
							+ m.getMenu("Joy      Level      Exp      Nevermind")
							+ bot);
					String input2 = in.next();
					if (m.check(input2, "Joy")) {
						System.out.println(top
								+ m.getMenu("[ HELP ]")
								+ m.getMenu("Your Joy is like your health! If you lose it all, game over.")
								+ m.getMenu("You gain Joy by Dancing, and lose it when other bees are rude to you.")
								+ bot); }
					if (m.check(input2, "Level")) {
						System.out.println(top
								+ m.getMenu("[ HELP ]")
								+ m.getMenu("Your Level affects your maximum Joy, and how friendly you are.")
								+ m.getMenu("Level up by befriending other bees.")); }
					if (m.check(input2, "Exp")) {
						System.out.println("Your exp is how close you are to the next level."); }
				} //end Stats Inquiry
		//HelpItems
				if (m.check(input1, "Items")) {
					System.out.println(top
							+ m.getMenu("[ HELP ]")
							+ m.getMenu("What items do you need help with?")
							+ m.getMenu("Honeydrops      Partygear"
							+ bot));
					String input2 = in.next();
					if (m.check(input2, "Honeydrops")) {
						System.out.println(top
								+ m.getMenu("[ HELP ]")
								+ m.getMenu("Your honeydrops are your currency!")
								+ m.getMenu("You can use them to purchase Partygear.")
								+ m.getMenu("Find them when Exploring or as gifts from befriended bees.")
								+ bot); }
					if (m.check(input, "Partygear")) {
						System.out.println(top
								+ m.getMenu("[ HELP ]")
								+ m.getMenu("Partygear is what you use to dance!")
								+ m.getMenu("You use 1 partygear with each dance.")
								+ bot); }
				} //end Items Inquiry
			} //end ActionHelp
//ActionHome
			if (m.check(input, "Leave")) {
				System.out.println(top
						+ m.getMenu("[ LEAVE ]")
						+ m.getMenu("Are you sure you'd like to go home?")
						+ m.getMenu("This will end the current game! (Y/N)")
						+ bot);
				String input1 = in.next();
				if (m.check(input1, "Yes")) {
					System.out.println(top
							+ m.getMenu("[ LEAVE ]")
						+ m.getMenu("Thanks for playing!")
						+ m.getMenu("You got to level " + player.getLevel() + " and made " + friendsMade + " friends.")
						+ bot);
					player.endBee();}
				else if (m.check(input1, "No")) {
					System.out.println(top
							+ m.getMenu("[ LEAVE ]")
						+ m.getMenu("I'm glad you're staying, there are so many other bees to befriend!")
						+ bot); }
			}
		} //turn loop end
	}
}
