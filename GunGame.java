package Himanshu;
import java.util.Random;
import java.util.Scanner;

public class GunGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Game setup
        int score = 0;
        int maxTargets = 5; // Number of targets to shoot
        int remainingTargets = maxTargets;
        boolean gameOver = false;

        System.out.println("Welcome to the Gun Game!");
        System.out.println("You have to shoot " + maxTargets + " targets. Good luck!");
        
        while (!gameOver) {
            // Randomly decide if target appears or not
            int targetDistance = random.nextInt(10) + 1; // Target distance between 1 and 10
            System.out.println("\nA target appears! It's " + targetDistance + " meters away.");
            
            System.out.print("Do you want to shoot? (yes/no): ");
            String playerChoice = scanner.nextLine();
            
            if (playerChoice.equalsIgnoreCase("yes")) {
                // Random chance of hitting the target
                int hitChance = random.nextInt(10) + 1;
                
                if (hitChance <= targetDistance) {
                    System.out.println("You missed!");
                } else {
                    System.out.println("You hit the target!");
                    score++;
                    remainingTargets--;
                }
            } else {
                System.out.println("You chose not to shoot. The target escapes.");
            }

            // Check game over condition
            if (remainingTargets == 0) {
                gameOver = true;
            } else {
                System.out.println("Your score: " + score + " | Targets remaining: " + remainingTargets);
            }
        }

        // Final score
        System.out.println("\nGame Over!");
        System.out.println("Final Score: " + score);
    }
}
