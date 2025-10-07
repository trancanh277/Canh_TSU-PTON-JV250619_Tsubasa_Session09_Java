package baitap02;

public class Cat extends Pet {
    @Override
    public void speak() {
        System.out.println("Meo meo");
    }

    // Constructors
    // Constructor default
    public Cat() {
    }

    // Constructor extend Pet
    public Cat(String petId, String petName, int petAge) {
        super(petId, petName, petAge);
    }

}
