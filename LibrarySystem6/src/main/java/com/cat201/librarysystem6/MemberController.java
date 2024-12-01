package com.cat201.librarysystem6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class MemberController {
    private final String FILE_NAME = "src/main/java/com/cat201/librarysystem6/Members.csv";

    // Fields for sign up page
    @FXML private TextField signUpName;
    @FXML private TextField signUpMemberID;
    @FXML private PasswordField signUpPassword;
    @FXML private TextField signUpState;
    @FXML private TextField signUpEmail;
    @FXML private TextField signUpPhoneNumber;
    @FXML private TextField signUpSecurityAnswer;   // Security Code
    @FXML private Button generateCodeButton;

    // Fields for login page
    @FXML private TextField loginMemberID;
    @FXML private PasswordField loginPassword;
    @FXML private Button loginButton;
    @FXML private Button signUpButton;
    @FXML private Button forgotButton;

    // Fields for forgot password page
    @FXML private TextField forgotMemberID;
    @FXML private TextField forgotName;
    @FXML private TextField retrievedPassword;
    @FXML private TextField forgotSecurityAnswer;
    @FXML private Button forgotRetrieveButton;
    @FXML private Button forgotSearchButton;
    @FXML private Button forgotBackButton;

    /* -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    // Random Security Code Generator
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SECURITY_CODE_LENGTH = 8;
    private static final Set<String> generatedCodes = new HashSet<>();

    @FXML
    private void handleGenerateRandom () {
        // Auto-generate the security code and set it in the text field
        String securityCode = generateUniqueSecurityCode();
        signUpSecurityAnswer.setText(securityCode);

        // Disable the button after one click
        generateCodeButton.setDisable(true);
    }

    private String generateUniqueSecurityCode() {
        SecureRandom random = new SecureRandom();
        String code;

        do {
            StringBuilder randomString = new StringBuilder(SECURITY_CODE_LENGTH);
            for (int i = 0; i < SECURITY_CODE_LENGTH; i++) {
                int index = random.nextInt(CHARACTERS.length());
                randomString.append(CHARACTERS.charAt(index));
            }
            code = randomString.toString();
        } while (generatedCodes.contains(code)); // Ensure uniqueness

        generatedCodes.add(code);
        return code;
    }
    /* -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    // MEMBER SIGN UP PAGE
    // Handles the Sign-up action ("Create" button)
    @FXML
    private void handleSignUp(ActionEvent event) {
        String name = signUpName.getText().trim();
        String memberID = signUpMemberID.getText().trim();
        String password = signUpPassword.getText().trim();
        String state = signUpState.getText().trim();
        String email = signUpEmail.getText().trim();
        String phoneNumber = signUpPhoneNumber.getText().trim();
        String securityAnswer = signUpSecurityAnswer.getText().trim();

        if(name.isEmpty() || memberID.isEmpty() || password.isEmpty() || state.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()  || securityAnswer.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Sign-Up Error", "All fields must be filled out");
            return;
        }

        if(isMemberIDTaken(memberID)){
            showAlert(Alert.AlertType.ERROR, "Sign-Up Error", "Member ID Already exist. Please use a different ID");
                return;
        }

        saveMemberToFile(name, memberID, password, state, email, phoneNumber, securityAnswer);
        showAlert(Alert.AlertType.INFORMATION, "Sign-Up Succes", "You have successfully signed up!");
        clearSignUpFields();
    }

    // Save Member data to csv file
    private void saveMemberToFile(String name, String memberID, String password, String state, String email, String phoneNumber, String securityAnswer){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))){
            writer.write(String.join(",", name, memberID, password, state, email, phoneNumber, securityAnswer));
            writer.newLine();
        }
        catch(IOException e){
            showAlert(Alert.AlertType.ERROR, "File Error", "Could not save member data");
        }
    }

    // Clear Sign-up fields
    private void clearSignUpFields() {
        signUpName.clear();
        signUpMemberID.clear();
        signUpPassword.clear();
        signUpState.clear();
        signUpEmail.clear();
        signUpPhoneNumber.clear();
        signUpSecurityAnswer.clear();
    }

    // Handle Back Button in Sign Up Page
    @FXML
    private void handleBackButtonSU(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }
    /* -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    // MEMBER LOGIN PAGE
    // Handles the login action ("Login" Button)
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String memberID = loginMemberID.getText().trim();
        String password = loginPassword.getText().trim();

        boolean isAuthenticated = validateLogin(memberID, password);
        if (isAuthenticated) {
            showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome back!");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home_member.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle("Home-Member");
            stage.setScene(new Scene(root));
            stage.show();

        } else {
            showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid Member ID or password.");
        }
    }

    // Validate login credential
    private boolean validateLogin(String memberID, String password) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while((line = reader.readLine()) !=null) {
            String[] memberDetails = line.split(",");
            if (memberDetails[1].equals(memberID) && memberDetails[2].equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Handles the forgot password action (Navigate to forgot_pass.fxml)
    @FXML
    private void handleForgotPassword(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("forgot_pass.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setTitle("Forgot Password");
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Handles the Sign-Up button in Login page
    @FXML
    private void handleSignUpinLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign_up.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setTitle("Sign-Up");
        stage.setScene(new Scene(root));
        stage.show();
    }
    /* -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    // FORGOT PASSWORD PAGE
    // Handle the "Search" action on Forgot Password page
    @FXML
    private void handleForgotSearch(ActionEvent event) throws IOException {
        String memberID = forgotMemberID.getText().trim();

        Member member = findMemberByMemberID(memberID);
        if (member != null) {
            forgotName.setText(member.getName());
        } else {
            showAlert(Alert.AlertType.ERROR, "Search Error", "Member ID not found.");
        }
    }

    // Handle the Retrieve Password Action
    @FXML
    private void handleRetrievePassword(ActionEvent event) throws IOException {
        String memberID = forgotMemberID.getText().trim();
        String securityAnswer = forgotSecurityAnswer.getText().trim();

        Member member = findMemberByMemberID(memberID);
        if (member != null && member.getSecurityAnswer().equals(securityAnswer)) {
            retrievedPassword.setText(member.getPassword());
            showAlert(Alert.AlertType.INFORMATION, "Password Retrieved", "Your password is: " + member.getPassword());
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Incorrect security code.");
        }
    }

    // Find member by Member ID
    private Member findMemberByMemberID(String memberID) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields[1].equals(memberID)) {
                return new Member(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6]);
            }
        }
        return null;
    }

    // Handle Back Button in Forgot Password Page
    @FXML
    private void handleBackButtonForgot(ActionEvent event) throws IOException {
        // Load the "login.fxml" file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }
    /* -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    // Check if Member ID already exist
    private boolean isMemberIDTaken(String memberID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[1].equals(memberID)) {
                    return true;
                }
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "Error checking member ID.");
        }
        return false;
    }

    // Utility method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}