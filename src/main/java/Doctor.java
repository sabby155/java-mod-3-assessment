import java.util.*;

public class Doctor {
    String name;
//    private List<Patient> patients;
    private Queue<Patient> patients;
    private String specialty;


    int healingPower = 0;

    public Doctor(String name, String specialty) {
        this.name = name;
        this.patients = new ArrayDeque<Patient>();
        this.specialty = specialty;
        int max = 100;
        int min = -100;
        this.healingPower = (int)(Math.random()*(max-min+1)+min);
        System.out.println("Initialized Dr. " + this.name + " who treats " + this.specialty);
        System.out.println("Their healing power is: " + this.healingPower);
    }

    void treatPatient(Patient patient) {
        //Take a patient and treat that patient
        System.out.println("Doctor: Hello. I'm the doctor. I have a healing power of: " + healingPower);
        System.out.println("Treating patient with health: " + patient.healthIndex);

        boolean patientCured = patient.healed();
        boolean patientDead = patient.died();
        int numOfTreatmentsNeeded = 0;
        boolean patientCurable = patient.myAilment.isCurable();

        while(!patientCured && !patientDead && patientCurable) {
            if (patient.healthIndex > 0 && patient.healthIndex < 100 ) {
                patient.recieveTreatment(healingPower);
                numOfTreatmentsNeeded++;
            } else if (healingPower == 0 || patient.healthIndex <= 0) {
                System.out.println("Doctor: Oh no!");
                patientDead = true;
                removePatient(patient);
            } else {
                System.out.println("Doctor: Wonderful! " + patient.getName() + " needed " +  numOfTreatmentsNeeded);
                patientCured = true;
                removePatient(patient);
            }
        }

    }

    private void removePatient(Patient patient) {
        //Take a patient and remove that patient
        System.out.println("Doctor: Removing patient.");
        patients.remove(patient);
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getPatientCount() {
        return this.patients.size();
    }

    public void addPatient(Patient patient) {
        System.out.println("Adding patient: " + patient + " to " + name + "'s roster.");
        this.patients.add(patient);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", patients=" + patients +
                ", specialty='" + specialty + '\'' +
                ", healingPower=" + healingPower +
                '}';
    }
}
