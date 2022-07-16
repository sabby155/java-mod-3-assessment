public class Ailment {
    String name;
    boolean isCurable;
    int startingHealthIndex;
    String associatedSpecialty;


    public Ailment(String name, boolean isCurable, int startingHealthIndex, String associatedSpecialty) {
        this.name = name;
        this.isCurable = isCurable;
        this.startingHealthIndex = startingHealthIndex;
        this.associatedSpecialty = associatedSpecialty;
        System.out.println("Initialized ailment: " + this);
    }

    public boolean isCurable() {
        if(associatedSpecialty.equals("Toxicology")) {
            System.out.println("Ailment: Not curable.");
            isCurable = false;
        } else {
            System.out.println("Ailment: Curable.");
            isCurable = true;
        }
        return isCurable;
    }

    public int getStartingHealthIndex() {
        return startingHealthIndex;
    }

    public String getAssociatedSpecialty() {
        return associatedSpecialty;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Ailment{" +
                "name='" + name + '\'' +
                ", isCurable=" + isCurable +
                ", startingHealthIndex=" + startingHealthIndex +
                ", associatedSpecialty='" + associatedSpecialty + '\'' +
                '}';
    }
}
