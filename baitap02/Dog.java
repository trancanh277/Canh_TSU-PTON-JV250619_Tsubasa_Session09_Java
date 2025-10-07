package baitap02;

public class Dog extends Pet {
    // Override extends Pet
    @Override
    public void speak() {
        System.out.println("Gâu gâu");
    }

    // Constructors
    // Constructor default
    public Dog() {
    }

    // Constructor extends Pet
    public Dog(String petId, String petName, int petAge) {
        super(petId, petName, petAge);
    }
}
