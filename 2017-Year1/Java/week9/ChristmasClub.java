package week9;

import java.util.ArrayList;

public class ChristmasClub {

    // create private ArrayList , which is an Object of objects
    private ArrayList<Member> allMembers;

    public ChristmasClub() {
        this.allMembers = new ArrayList<>();
    }

    public ChristmasClub(ArrayList <Member> allMembers) {
        this.allMembers = allMembers;
    }

    public void addNewMember(Member newMember) {
        // append new member to list
        this.allMembers.add(newMember);
    }

    public String toString() {
        // list all members in the ChristmasClub object
        String s = "";
        for (Member member : this.allMembers) {
            s += member.toString();
            s += "\n";
        }
        return s;
    }

    public int countContributions() {
        // calculate total Contributions made in a ChristmasClub
        int total = 0;
        for (Member member : this.allMembers) {
            total += member.getContributions();
        }
        return total; // return contributions amount
    }

    public int turkeysAmount(int turkeyCost) {
        // calculate maximum possible turkeys amount to buy
        return this.countContributions() / turkeyCost ;
    }

    public String capitaliseName(String name) {
        // capitalise the name , to avoid search mistakes
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String searchForContribution(String search) {
        search = capitaliseName(search); // capitalise
        String s = "";
        // find a member by name and return his contribution made
        for (Member member : this.allMembers) {
            if (member.getName().equals(search)) {
                s += "Find: Member " + member.getName() +
                    " has contributed £" + member.getContributions() + "\n";
            }
        }
        return s;
    }
}
