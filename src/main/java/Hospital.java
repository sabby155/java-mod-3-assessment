import java.util.*;

public class Hospital {
    private String name;
    private Map<String, Set<Doctor>> specialtyToDoctors;
    private List<Patient> patientsList = new ArrayList<>();
    private List<Doctor> doctorsList = new ArrayList<>();
    private List<Ailment> ailmentsTreated;

    public Hospital(String name) {
        this.name = name;
        this.specialtyToDoctors = new HashMap<>();
        this.ailmentsTreated = new ArrayList<>();
        this.ailmentsTreated.add(new Ailment("Check-up", true, 80, "General"));
        this.ailmentsTreated.add(new Ailment("Digestive", true, 60, "Gastroenterology"));
        this.ailmentsTreated.add(new Ailment("Skin-related", true, 40, "Dermatology"));
        this.ailmentsTreated.add(new Ailment("Poisonous", false, 20, "Toxicology"));
        System.out.println("Initialized hospital: " + this.name);
    }

    public List<Ailment> getAilmentsTreated() {
        return ailmentsTreated;
    }

    public String getName() {
        return name;
    }

    public List<Patient> getPatientsList() {
        return patientsList;
    }
    public List<Doctor> getDoctorsList() {
        return doctorsList;
    }

    public void addDoctor(Doctor doctor) {
        // case: this specialty already exists in the map
        if (specialtyToDoctors.containsKey(doctor.getSpecialty())) {
            Set<Doctor> withSpecialty = specialtyToDoctors.get(doctor.getSpecialty());
            withSpecialty.add(doctor);
        } else {
            // case 2, this specialty does not already exist in the map
            Set<Doctor> doctors = new HashSet<>();
            doctors.add(doctor);
            specialtyToDoctors.put(doctor.getSpecialty(), doctors);
            doctorsList.add(doctor);
        }
        System.out.println("Hospital: Added doctor to our map." + specialtyToDoctors);
    }

    public void addPatient(Patient patient) {
        Set<Doctor> doctorsWithThatSpecialty = specialtyToDoctors.get(patient.getNeed());
        System.out.println("DoctorsWithThatSpecialty:" + doctorsWithThatSpecialty);
        if(doctorsWithThatSpecialty == null) {
            System.out.println("Oops. No doctors for this.");
        } else {
            Doctor chosenDoctor = findDoctorWithShortestQueue(doctorsWithThatSpecialty);
            chosenDoctor.addPatient(patient);
            patientsList.add(patient);
            patient.setMyDoctor(chosenDoctor);
        }
    }

    private Doctor findDoctorWithShortestQueue(Set<Doctor> doctors) {
        Doctor chosenDoctor = null;

        for (Doctor doctor : doctors) {
            if (chosenDoctor == null) {
                chosenDoctor = doctor;
            } else if (chosenDoctor.getPatientCount() > doctor.getPatientCount()) {
                chosenDoctor = doctor;
            }
        }
        return chosenDoctor;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                ", specialtyToDoctors=" + specialtyToDoctors +
                ", ailmentsTreated=" + ailmentsTreated +
                '}';
    }



}
