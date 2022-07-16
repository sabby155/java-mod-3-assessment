public class Patient {
    String name;
    int healthIndex;
    Ailment myAilment;

    Doctor myDoctor;

    String need;


    public Patient(String name, int healthIndex, Ailment myAilment) {
        this.name = name;
        this.healthIndex = healthIndex;
        this.myAilment = myAilment;
        this.need = myAilment.getAssociatedSpecialty();
        System.out.println("Initialized Patient: " + this.name + " with health: " + this.healthIndex + " who needs " + this.myAilment.getName() + " care.");
    }

    public String getName() {
        return name;
    }

    String getNeed() {
        return need;
    }

    public Doctor getMyDoctor() {
        return myDoctor;
    }

    public void setMyDoctor(Doctor myDoctor) {
        this.myDoctor = myDoctor;
    }

    public void recieveTreatment(int healed) {
        //Take Patient and add corresponding Doctor's healing power int
        System.out.println("Patient: " + healed + " is being added to my life.");
        this.healthIndex += healed;

        System.out.println("Patient: I am now at:  ========= " + healthIndex);

        if(healthIndex <= 0) {
            died();
        }
    }

    public boolean healed() {
        //Patient healed (100 health) - exit them from hospital
        if(healthIndex >= 100) {
            System.out.println("Patient: I'm cured!");
            return true;
        }
        return false;
    }

    public boolean died() {
        //Patient died (0 health) - exit them from hospital
        if(healthIndex <= 0) {
            System.out.println("Patient: I'm dead!");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", healthIndex=" + healthIndex +
                ", myAilment=" + myAilment +
                ", need='" + need + '\'' +
                '}';
    }
}
