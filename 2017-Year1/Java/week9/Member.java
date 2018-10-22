package week9;

public class Member {

    private String name;
    private int contributions;

    public Member(String name, int contributions) {

        this.name = capitaliseName(name);
        this.contributions = contributions;
    }

    public String capitaliseName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    public boolean addContributions(int amount) {
        if (amount > 0) {
            this.contributions += amount;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name + " has contributed £" + contributions + ".";
    }
}
