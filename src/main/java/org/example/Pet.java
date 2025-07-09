package org.example;

public class Pet {
    private int id;
    private String name;
    private int ownerId;
    private String gender;
    private int age;

    public Pet(int id, String name, int ownerId, String gender, int age) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.gender = gender;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
