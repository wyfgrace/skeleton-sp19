public class Human {
    double height;
    double weight;
    int posX;
    int posY;

    public Human() {
        height = 1.6;
        weight = 50;
        posX = 1;
        posY = 2;
    }

    public Human(double height, double weight, int posX, int posY){
        this.weight = weight;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }

    public void describe(){
        System.out.println(this.weight);
        System.out.println(this.height);
        System.out.println(this.posX);
        System.out.println(this.posY);
    }

    public static void main(String[] args) {
        Human a = new Human(1.7,130,0,1);
        Human b = new Human(1.2,30,10,10);
        b.describe();
    }
}
