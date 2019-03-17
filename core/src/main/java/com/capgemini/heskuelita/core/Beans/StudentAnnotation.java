package com.capgemini.heskuelita.core.Beans;

import javax.persistence.*;

@Entity (name =  "student")
@Table (name = "student")
@XmlRootElement
public class StudentAnnotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq")
    @Column(name="id_stdent", nullable=false, unique=true)
    private int id;

    @Column(name="first_name", length=28, nullable=false)
    private String first_name;

    @Column(name="last_name", length=28, nullable=false)
    private String last_name;

    @Column(name="type_id", nullable=false)
    private String type_id;

    @Column(name="number_id", length=28, nullable=false)
    private String  number_id;

    @Column(name="gender", length=28, nullable=false)
    private String gender;

    @Column(name="phone_number", length=28, nullable=false)
    private String phone_number;

    public StudentAnnotation() {

        super ();
    }

    public StudentAnnotation(String first_name, String last_name, String type_id, String number_id, String gender, String phone_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.type_id = type_id;
        this.number_id = number_id;
        this.gender = gender;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getNumber_id() {
        return number_id;
    }

    public void setNumber_id(String number_id) {
        this.number_id = number_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
