import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HospitalWorld {
    public static void main(String[] args) throws IOException {

        // your code here


        System.out.println("Restored the following from patient.data: ");
        System.out.println(FileReader.readFromFile("patient.data", true));

        System.out.println("Restored the following from doctor.data: ");
        System.out.println(FileReader.readFromFile("doctor.data", true));

//        initializeIfWantToRestoreFiles(hospital);


        //Create new hospital
        Hospital hospital = createHospital();

        //Add + print ailments that hospital treats
        printAilments(hospital);

        //Begin Doctor and Patient creation loops
        initializeDoctorsLoop(hospital);
        initializePatientsLoop(hospital);

        //Print hospital world
        print(hospital.toString());

        initializeMorePatientMenu(hospital);
        initializeSelectPatientTotreat(hospital);


        List<Patient> patientList = hospital.getPatientsList();
        List<Doctor> doctorList = hospital.getDoctorsList();


        writePatientList(patientList);
        writePatientJson(patientList);
        writeDoctorList(doctorList);
        writeDoctorJson(doctorList);


    }


    public static void writePatientJson(List<Patient> patients) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(patients);
        System.out.println(json);
        //Write JSON to file.
        try {
            FileReader.writeToFile("patient.data", json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void writeDoctorJson(List<Doctor> doctor) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(doctor);
        System.out.println(json);
        //Write JSON to file.
        try {
            FileReader.writeToFile("doctor.data", json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Patient>  readPatientJson() throws JsonProcessingException {
        String json = FileReader.readFromFile("patient.data");
        //return new ObjectMapper().readValue(json, List<Student>.class)
        try {
            return Arrays.asList(new ObjectMapper().readValue(new File("patient.data"), Patient[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Doctor>  readDoctorJson() throws JsonProcessingException {
        String json = FileReader.readFromFile("doctor.data");
        //return new ObjectMapper().readValue(json, List<Student>.class)
        try {
            return Arrays.asList(new ObjectMapper().readValue(new File("doctor.data"), Doctor[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void writeDoctorList(List<Doctor> doctorList) throws IOException {
        for (Doctor doctor : doctorList) {
            FileReader.writeToFile("doctor.data", doctor.getName());
        }
        printDoctorListAsJSON(doctorList);
    }

    static void printDoctorListAsJSON(List<Doctor> doctorList) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(doctorList);
        System.out.println(json);
    }


    private static void writePatientList(List<Patient> patientList) throws IOException {
        for (Patient patient : patientList) {
            FileReader.writeToFile("patient.data", patient.getName());
        }
        printPatientListAsJSON(patientList);
    }
    static void printPatientListAsJSON(List<Patient> patientList) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(patientList);
        System.out.println(json);
    }




    private static void initializeMorePatientMenu(Hospital hospital) {
        print("Would you like to add more patients? Select Y: Yes, N: No");
        String userInput = getUserStringInput();

        if(userInput.equalsIgnoreCase("Y")) {
            Patient patient = createPatient(hospital);

            while(patient == null) {
                print("Lets try this again.");
                patient = createPatient(hospital);
            }
            if(patient != null) {
                hospital.addPatient(patient);
                initializeMorePatientMenu(hospital);
            }

        } else if(userInput.equalsIgnoreCase("N")) {
            print("Sure. You said no. Taking you back.");
            return;
        }
    }

    private static void initializeSelectPatientTotreat(Hospital hospital) {
        List<Patient> listofPatients = hospital.getPatientsList();
        int patientNum = 0;

        if(listofPatients.isEmpty() || listofPatients == null) {
            print("There are no patients to treat!");
        } else {
            print("Which of our patients would you like to treat?");
            System.out.println("list of p" + listofPatients);

            for (Patient patient : listofPatients) {
                patientNum++;
                print("Select " + patientNum + " for patient : " + patient.getName());
            }

            int userInput = getUserNumInput();

            if(userInput > 0 && userInput <= listofPatients.size()) {
                Patient patientToTreat = listofPatients.get(userInput - 1);
                Doctor chosenDoctorOfPatient = patientToTreat.getMyDoctor();
                print("You selected " + patientToTreat.getName() + " to treat.");
                print("Their doctor is " + chosenDoctorOfPatient.getName());

                chosenDoctorOfPatient.treatPatient(patientToTreat);
            } else {
                initializeSelectPatientTotreat(hospital);
            }

        }


    }

    private static void initializePatientsLoop(Hospital hospital) {
        int patientsCreated = 0;
        int numOfPatientsNeeded = 5;

        //Create 5 patients, each with a name and ailment provided by the user

        while(patientsCreated < numOfPatientsNeeded) {
            Patient patient = createPatient(hospital);

            while(patient == null) {
                print("Lets try this again.");
                patient = createPatient(hospital);
            }

            patientsCreated++;
            print(patientsCreated + " / " + numOfPatientsNeeded + " patients created.");
            if(patient != null) {
                hospital.addPatient(patient);
            }
        }
    }

    private static void initializeDoctorsLoop(Hospital hospital) {
        int doctorsCreated = 0;
        int numOfDoctorsNeeded = 3;
        //Create 3 doctors, each with a name and specialty provided by the user

        while(doctorsCreated < numOfDoctorsNeeded) {
            Doctor doctor = createDoctor();

            while(doctor == null) {
                print("Lets try this again.");
                doctor = createDoctor();
            }

            doctorsCreated++;
            print(doctorsCreated + " / " + numOfDoctorsNeeded + " doctors created.");
            hospital.addDoctor(doctor);
        }
    }

    private static void printAilments(Hospital newHospital) {
        print(newHospital.getName() + " treats: ");
        List<Ailment> ailmentsTreated = newHospital.getAilmentsTreated();
        for (Ailment ailment : ailmentsTreated) {
            print(ailment.getName());
        }
    }

    private static Patient createPatient(Hospital hospital) {
        print("What is the Patient's name?");
        String name = getUserStringInput();

        print("What is the Patient's need? Please Choose 1: Check-up, 2: Digestive, 3: Skin-related, 4: Poisonous");
        int ailmentInput = getUserNumInput();
        Ailment ailment = null;
        Patient thisPatient;

        ArrayList<Ailment> hospitalAilmentsList = (ArrayList<Ailment>) hospital.getAilmentsTreated();

        if(ailmentInput == 1) {
            ailment = hospitalAilmentsList.get(0);
            thisPatient = new Patient(name, ailment.getStartingHealthIndex(), ailment);
            return thisPatient;
        } else if (ailmentInput == 2) {
            ailment = hospitalAilmentsList.get(1);
            thisPatient = new Patient(name, ailment.getStartingHealthIndex(), ailment);
            return thisPatient;
        } else if (ailmentInput == 3) {
            ailment = hospitalAilmentsList.get(2);
            thisPatient = new Patient(name, ailment.getStartingHealthIndex(), ailment);
            return thisPatient;
        } else if (ailmentInput == 4){
            ailment = hospitalAilmentsList.get(3);
            thisPatient = new Patient(name, ailment.getStartingHealthIndex(), ailment);
            return thisPatient;
        }

        return null;

    }

    private static Hospital createHospital() {
        print("What's the hospital name?");
        String name = getUserStringInput();
        Hospital thisHospital = new Hospital(name);
        return thisHospital;
    }

    private static Doctor createDoctor() {
        print("What is the Doctor's name?");
        String name = getUserStringInput();


        print("What is the Doctor's specialty? Please Choose 1: General , 2: Gastroenterology, 3: Dermatology, 4: Toxicology ");
        int specialtyInput = getUserNumInput();
        String specialty = null;
        Doctor thisDoctor;

        if(specialtyInput == 1) {
            specialty = "General";
            thisDoctor = new Doctor(name, specialty);
            return thisDoctor;
        } else if (specialtyInput == 2) {
            specialty = "Gastroenterology";
            thisDoctor = new Doctor(name, specialty);
            return thisDoctor;
        } else if (specialtyInput == 3) {
            specialty = "Dermatology";
            thisDoctor = new Doctor(name, specialty);
            return thisDoctor;
        } else if (specialtyInput == 4){
            specialty = "Toxicology";
            thisDoctor = new Doctor(name, specialty);
            return thisDoctor;
        }

        return null;

    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static String getUserStringInput() {
        try{
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            return userInput;
        } catch(IllegalFormatException e) {
            System.out.println("Are you sure you selected correctly?");
        }
        return null;
    }

    private static int getUserNumInput() {
        try{
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();
            return userInput;
        } catch(InputMismatchException e) {
            System.out.println("Are you sure you selected correctly?");
            return 0;
        }
    }
}
