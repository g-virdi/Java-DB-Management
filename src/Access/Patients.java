package Access;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patients {
    private final StringProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty date_of_birth;
    private final StringProperty address;
    private final StringProperty phone;
    private final StringProperty insuranceNo;
    private final StringProperty diagnosis;
    private final StringProperty treatment;

    public Patients(String id, String firstName, String lastName, String date_of_birth, String address, String phone,
                       String insuranceNo, String diagnosis, String treatment) {
        this.id = new SimpleStringProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.date_of_birth = new SimpleStringProperty(date_of_birth);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.insuranceNo = new SimpleStringProperty(insuranceNo);
        this.diagnosis = new SimpleStringProperty(diagnosis);
        this.treatment = new SimpleStringProperty(treatment);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getDate_of_birth() {
        return date_of_birth.get();
    }

    public StringProperty date_of_birthProperty() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth.set(date_of_birth);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getInsuranceNo() {
        return insuranceNo.get();
    }

    public StringProperty insuranceNoProperty() {
        return insuranceNo;
    }

    public void setInsuranceNo(String insuranceNo) {
        this.insuranceNo.set(insuranceNo);
    }

    public String getDiagnosis() {
        return diagnosis.get();
    }

    public StringProperty diagnosisProperty() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis.set(diagnosis);
    }

    public String getTreatment() {
        return treatment.get();
    }

    public StringProperty treatmentProperty() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment.set(treatment);
    }
}
