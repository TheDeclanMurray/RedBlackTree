package src;

import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        System.out.println("Program Running");

        RedBlackTree<Holder> RBT = new RedBlackTree<Holder>();
        RBT.toString();
        RBT.add(new Holder(20));
        RBT.toString();
        // RBT.add(new Holder(12));
        // RBT.toString();
        // RBT.add(new Holder(30));
        // RBT.toString();
        // RBT.add(new Holder(25));
        // RBT.toString();
        // RBT.add(new Holder(15));
        // RBT.toString();
        // RBT.add(new Holder(10));
        // RBT.toString();
        // RBT.add(new Holder(35));
        // RBT.toString();

        Scanner in = new Scanner(System.in);
        while(true){
            System.out.print("Number: ");
            String s = in.nextLine();
            if (s.startsWith("q")){
                break;
            }else{
                int num = Integer.parseInt(s);
                RBT.add(new Holder(num));
                RBT.toString();
            }
        }
        in.close();
        
    }



}