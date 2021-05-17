package Access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.*;

public class DataController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private DatePicker date_of_birth;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField insuranceNo;
    @FXML
    private TextArea diagnosis;
    @FXML
    private  TextArea treatment;

    //Button fxid
    @FXML
    private Button addButton;
    @FXML
    private Button clear;
    @FXML
    private Button info;
    @FXML
    private Button updateButton;
    @FXML
    private Button delete;
    @FXML
    private Button search;
    @FXML
    private Button searchbyName;

    //Table and Columns
    @FXML
    private TableView<Patients> table;
    @FXML
    private TableColumn<Patients, String> idCol;
    @FXML
    private TableColumn<Patients, String> fnCol;
    @FXML
    private TableColumn<Patients, String> lnCol;
    @FXML
    private TableColumn<Patients, String> dobCol;
    @FXML
    private TableColumn<Patients, String> adCol;
    @FXML
    private TableColumn<Patients, String> pCol;
    @FXML
    private TableColumn<Patients, String> inNoCol;
    @FXML
    private TableColumn<Patients, String> diaCol;
    @FXML
    private TableColumn<Patients, String> treatCol;

    private ObservableList<Patients> dataList;
    private ObservableList<Patients> searchlist;

    private DBConnector con;
    public void initialize(URL url, ResourceBundle rb){
        this.con = new DBConnector();
    }

    @FXML
    private void addPatient(ActionEvent event){
        String sqlInsert = "INSERT INTO patient (id, firstName, lastName, date_of_birth, address, phone, insuranceNo, diagnosis, treatment) VALUES (?,?,?,?,?,?,?,?,?)";

        try{
            Connection con = DBConnector.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ps.setString(1, this.id.getText());
            ps.setString(2, this.firstName.getText());
            ps.setString(3, this.lastName.getText());
            ps.setString(4, this.date_of_birth.getEditor().getText());
            ps.setString(5, this.address.getText());
            ps.setString(6, this.phone.getText());
            ps.setString(7, this.insuranceNo.getText());
            ps.setString(8, this.diagnosis.getText());
            ps.setString(9, this.treatment.getText());

            ps.execute();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        AlertBox.display("Patient added to Database");
    }


    @FXML
    private void clear(ActionEvent event){
        this.id.setText("");
        this.firstName.setText("");
        this.lastName.setText("");
        this.date_of_birth.setValue(null);
        this.address.setText("");
        this.phone.setText("");
        this.insuranceNo.setText("");
        this.diagnosis.setText("");
        this.treatment.setText("");
    }

    @FXML
    private void load(ActionEvent event){
        try{
            Connection con = DBConnector.getConnection();

            this.dataList = FXCollections.observableArrayList();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM patient");
            while(rs.next()){
                this.dataList.add(new Patients(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9)));
            }

            this.idCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("id"));
            this.fnCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("firstName"));
            this.lnCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("lastName"));
            this.dobCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("date_of_birth"));
            this.adCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("address"));
            this.pCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("phone"));
            this.inNoCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("insuranceNo"));
            this.diaCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("diagnosis"));
            this.treatCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("treatment"));

            this.table.setItems(null);
            this.table.setItems(this.dataList);


        }catch(SQLException e){
            System.err.println(e);
        }
    }


    @FXML
    private void deletePatient(ActionEvent event){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm delete operation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?");
        Optional<ButtonType> action = alert.showAndWait();

        if(action.get() == ButtonType.OK){

            String sqlQuery = "DELETE FROM patient WHERE id = ?";

            try{
                Connection con = DBConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlQuery);
                ps.setString(1,id.getText());
                ps.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }

            AlertBox.display("Patient deletet");
        }

    }


    @FXML
    private void searchbyID(ActionEvent event){

        this.searchlist = FXCollections.observableArrayList();

        try{
            Connection con = DBConnector.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM patient WHERE id = '" + id.getText()+ "'");
            while (rs.next()){

                this.searchlist.add(new Patients(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9)));
            }

            this.idCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("id"));
            this.fnCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("firstName"));
            this.lnCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("lastName"));
            this.dobCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("date_of_birth"));
            this.adCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("address"));
            this.pCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("phone"));
            this.inNoCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("insuranceNo"));
            this.diaCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("diagnosis"));
            this.treatCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("treatment"));

            this.table.setItems(null);
            this.table.setItems(this.searchlist);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void searchName(ActionEvent event){

        this.searchlist = FXCollections.observableArrayList();

        try{
            Connection con = DBConnector.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM patient WHERE firstName = '" + firstName.getText()+ "' AND " +
                    "lastName = '"+lastName.getText()+"'");
            while (rs.next()){

                this.searchlist.add(new Patients(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9)));
            }

            this.idCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("id"));
            this.fnCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("firstName"));
            this.lnCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("lastName"));
            this.dobCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("date_of_birth"));
            this.adCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("address"));
            this.pCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("phone"));
            this.inNoCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("insuranceNo"));
            this.diaCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("diagnosis"));
            this.treatCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("treatment"));

            this.table.setItems(null);
            this.table.setItems(this.searchlist);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void update(ActionEvent event){

        String sqlQuery = "UPDATE patient SET address = ?, phone = ?, insuranceNo = ?, diagnosis = ?, treatment = ? " +
                "WHERE id = ?" ;

        try{
            Connection con = DBConnector.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlQuery);
            ps.setString(1,address.getText());
            ps.setString(2,phone.getText());
            ps.setString(3,insuranceNo.getText());
            ps.setString(4,diagnosis.getText());
            ps.setString(5,treatment.getText());
            ps.setString(6,id.getText());
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        AlertBox.display("Information updated");

    }

}
