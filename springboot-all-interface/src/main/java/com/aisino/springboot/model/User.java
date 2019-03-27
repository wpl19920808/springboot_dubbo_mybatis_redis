package com.aisino.springboot.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 8729267397169157982L;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.nick
     *
     * @mbg.generated
     */
    private String nick;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.phone
     *
     * @mbg.generated
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.address
     *
     * @mbg.generated
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.email
     *
     * @mbg.generated
     */
    private String email;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.id
     *
     * @return the value of u_user.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.id
     *
     * @param id the value for u_user.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.nick
     *
     * @return the value of u_user.nick
     *
     * @mbg.generated
     */
    public String getNick() {
        return nick;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.nick
     *
     * @param nick the value for u_user.nick
     *
     * @mbg.generated
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.phone
     *
     * @return the value of u_user.phone
     *
     * @mbg.generated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.phone
     *
     * @param phone the value for u_user.phone
     *
     * @mbg.generated
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.address
     *
     * @return the value of u_user.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.address
     *
     * @param address the value for u_user.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.email
     *
     * @return the value of u_user.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.email
     *
     * @param email the value for u_user.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email;
    }
}