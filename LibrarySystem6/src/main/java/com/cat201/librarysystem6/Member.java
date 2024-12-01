package com.cat201.librarysystem6;

import java.io.*;
import java.util.ArrayList;

public class Member {

    private ArrayList<Member> members = new ArrayList<Member>();
    private String name;
    private String memberID;
    private String password;
    private String state;
    private String email;
    private String phoneNumber;
    private String securityAnswer;

    // Constructor
    public Member(String name, String memberID, String password, String state, String email, String phoneNumber, String securityAnswer) {
        this.name = name;
        this.memberID = memberID;
        this.password = password;
        this.state = state;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.securityAnswer = securityAnswer;
    }

    // Getters and Setters for each field
    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String formatCSV(){
        return name + "," + memberID + "," + password + "," + state + "," + email + "," + phoneNumber + "," + securityAnswer;
    }

    public static ArrayList<Member> getAllMembers(String filename) {
        ArrayList<Member> members = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // Assumes CSV values are separated by commas
                if (data.length == 7) {                 // Ensure the line has the correct number of fields
                    members.add(new Member(data[0], data[1], data[2], data[3],
                            data[4], data[5], data[6]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", memberId='" + memberID + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", securityAnswer='" + securityAnswer + '\'' +
                '}';
    }
}