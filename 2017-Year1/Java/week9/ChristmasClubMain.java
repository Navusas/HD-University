package week9;

public class ChristmasClubMain {

    public static final int COST_OF_TURKEY = 10; // set the constant value for turkey /ea


    public static void main(String[] args) {

        // Create members
        Member gary = new Member("Gary", 25);
        Member tony = new Member("Tony", 20);
        Member rubiya = new Member("Rubiya", 22);
        Member steve = new Member("Steve", 18);

        // create new object
        ChristmasClub club = new ChristmasClub();

        // add members objects into christmas club object
        club.addNewMember(gary);
        club.addNewMember(tony);
        club.addNewMember(rubiya);
        club.addNewMember(steve);

        System.out.println(club.toString());
        System.out.println("Total contributions made £" + club.countContributions() + "\n");

        System.out.println(club.turkeysAmount(COST_OF_TURKEY) +
                            "turkeys that can be bought\n" +
                            "Price per turkey:  £" + COST_OF_TURKEY +
                            "\n");

        String name = "tony";
        System.out.println("Search for " + name + " . . . ");
        System.out.println(club.searchForContribution(name));
    }

}
