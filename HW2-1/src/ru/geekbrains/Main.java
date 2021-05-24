package ru.geekbrains;
//1.Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
// Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
//2.Создайте два класса: беговая дорожка и стена, при прохождении через которые,
// участники должны выполнять соответствующие действия (бежать или прыгать),
// результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
//3.Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
//4.* У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
// Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.

public class Main {

    public static void main(String[] args) {
        task1(); //task1
        task2(); //task2
        task3(); //task3
    }

    //1.Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
    // Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
    public static void task1(){
        System.out.println("Task 1: " + "\n");
        Cats cat = new Cats("Barsik");
        Humans man = new Humans("Vovan");
        Robots mob = new Robots("Robi");
        System.out.println(man);
        System.out.println(cat);
        System.out.println(mob);
        System.out.println();
    }

    //2.Создайте два класса: беговая дорожка и стена, при прохождении через которые,
    // участники должны выполнять соответствующие действия (бежать или прыгать),
    // результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
    public static void task2() {
        System.out.println("Task 2: " + "\n");
        Cats cat = new Cats("Barsik");
        Humans man = new Humans("Vovan");
        Robots mob = new Robots("Robi");
        JoggingTrack track = new JoggingTrack(300);
        WallsJump wall = new WallsJump(4);
        cat.run(track.getDistance());
        man.run(track.getDistance());
        mob.run(track.getDistance());
        cat.jump(wall.getHeight());
        man.jump(wall.getHeight());
        mob.jump(wall.getHeight());
        System.out.println();
        System.out.println();

    }

    ////3.Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.

    public static void task3() {
        System.out.println("Task 3: " + "\n");
        Spormens[] team = new Spormens[6];
        team[0] = new Cats("Barsik");
        team[1] = new Humans("Vovan");
        team[2] = new Robots("Robi");
        team[3] = new Spormens("Tolik", 150, 1);
        team[4] = new Spormens("Robot", 1000, 1000);
        team[5] = new Cats("Murzik");

        Obstacle[] obstacle = new Obstacle[3];
        obstacle[0] = new WallsJump(2);
        obstacle[1] = new JoggingTrack(100);
        obstacle[2] = new WallsJump(5);

        for (int i = 0; i < team.length; i++) {
            System.out.println(team[i]);
        }

        System.out.println();
        System.out.println("полоса препятствий: ");

        for (int i = 0; i < obstacle.length; i++) {
            System.out.println(obstacle[i]);
        }

        for (int i = 0; i < team.length; i++){
            for (int j = 0; j < obstacle.length; j++){
                if (obstacle[j].getDistance() > 0){
                    team[i].run(obstacle[j].getDistance());

                }
                else team[i].jump(obstacle[j].getHeight());
            }
            System.out.println();
        }
    }



}
