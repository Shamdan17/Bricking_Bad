package domain.model.alien;

//public class DrunkAlienBehavior implements AlienBehavior{

//    private List<Alien> aliens = new ArrayList<>();
//
//    public DrunkAlien(MovementBehavior movementBehavior, int length, int width) {
//        super(movementBehavior, length, width);
//    }
//
//    public DrunkAlien(Position position, int length, int width) {
//        //TODO check movement ??
//        super(new LinearMovement(position, new Velocity(3 * Constants.L / 150, 0)), length, width);
//    }
//
//    @Override
//    public void behave() {
//
//    }
//
//    @Override
//    public void collide(MovableShape obj) {
//
//    }
//
//    @Override
//    public SpecificType getSpecificType() {
//        return SpecificType.DrunkAlien;
//    }
//
//    @Override
//    public String toString() {
//        return "Drunk Alien at " + super.getPosition() + " with velocity " + super.getVelocity();
//    }
//
//    public void addAlien(Alien alien){
//        aliens.add(alien);
//    }
//
//    public void removeAlien(Alien alien) {
//        aliens.remove(alien);
//    }
//}
