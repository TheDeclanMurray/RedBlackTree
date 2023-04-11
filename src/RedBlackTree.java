package src;

import java.util.Scanner;

import javax.print.attribute.standard.PrinterMessageFromOperator;

public class RedBlackTree<T extends Comparable<T>> {
    
    public Node<T> root;

    public RedBlackTree(){
         root = null;
    }

    // public void PrimAdd (T data){
    //     Node<T> baby = new Node<T>(data);
    //     if(root == null){
    //         root = baby;
    //         return;
    //     }
    //     curr = root;
    //     while(curr != null){
    //         if(curr.data.compareTo(baby.data) >= 0){
    //             // new data is smaller, left sub-tree
    //             if(curr.left == null){
    //                 curr.left = baby;
    //                 return;
    //             }
    //             curr = curr.left;
    //         }else{
    //             // new data larger, right sub-tree
    //             if(curr.right == null){
    //                 curr.right = baby;
    //                 return;
    //             }
    //             curr = curr.right;
    //         }
    //     }




    // }

    public void add( T data ){
        Node<T> baby = new Node<T>(data);
        // root = __adder__(null, null, this.root, baby);
        plus(null, null, root, baby, false, false);
        root.color = 0;
    }

    private Node<T> __adder__(Node<T> GPrnt, Node<T> Prnt, Node<T> curr, Node<T> baby){
        if( curr == null){
            curr = baby;
            return curr;
        }
        if(curr.data.compareTo(baby.data) >= 0){
            //left sub tree
            curr.left = __adder__(Prnt, curr, curr.left, baby);

        }else{
            //right sub tree
            curr.right = __adder__(Prnt, curr, curr.right, baby);

        }

        return null;
    }


    private Node<T> plus(Node<T> curr, Node<T> child, Node<T> gChild, Node<T> baby, boolean childLeft, boolean gChildLeft){
        String prnt = "\nCurr: ";
        if( curr == null){
            prnt += "null";
        }else{
            prnt += curr.toString();
        }
        prnt += "\nChild: ";
        if( child == null){
            prnt += "null";
        }else{
            prnt += child.toString();
        }
        prnt += "\nG-Child: ";
        if( gChild == null){
            prnt += "null";
        }else{
            prnt += gChild.toString();
        }
        // System.out.println(prnt);


        boolean foundBaby = false;

        if ( gChild == null){
            // found the leaf
            // System.out.println("Found the Spot");
            if (child == null){
                //root
                // System.out.println("Root empty, thats the spot");
                root = baby;
                return null;
            }
            child.setChild(gChildLeft, baby);
            gChild = baby;
            foundBaby = true;
        }

        if(!foundBaby){
            Node<T> rtn;

            // put go left or right down the tree
            if (gChild.data.compareTo(baby.data) < 0){
                // Left
                // System.out.println("To the Left");
                rtn = plus(child, gChild, gChild.left, baby, gChildLeft, true);
            }else{
                // right
                // System.out.println("To the Right");
                rtn = plus(child, gChild, gChild.right, baby, gChildLeft, false);
            }

            if (rtn != null){
                //child changed
                if(curr == null){
                    root = rtn;
                }else{
                    curr.setChild(childLeft, rtn);
                }
                return null;
            }else{
                // null return, need to reset child
                if(child != null){
                    gChild = child.getChild(gChildLeft);
                }
                
            }
        }

        

        

        if(curr == null){
            return null;
        }


        // handle the way back up


        if(child.color == 1 && gChild.color == 1){
            // Problem
            Node<T> uncle = curr.getChild(!childLeft);
            if(uncle == null || uncle.color == 0){
                // Black Uncle
                if(childLeft == gChildLeft){
                    // Line
                    System.out.println("Black Uncle Line");

                    curr.setChild(childLeft, child.getChild(!gChildLeft));
                    child.setChild(!gChildLeft, curr);
                    child.color = 0;
                    curr.color = 1;
                    return child;
                }else{
                    // Triangle
                    System.out.println("Black Uncle Triangle");

                    curr.setChild(childLeft, gChild.getChild(gChildLeft));
                    child.setChild(gChildLeft, gChild.getChild(!gChildLeft));
                    gChild.setChild(gChildLeft, curr);
                    gChild.setChild(!gChildLeft, child);
                    curr.color = 1;
                    gChild.color = 0;
                    return gChild;
                }
            }else{
                // Red uncle
                System.out.println("Red Uncle");
                child.color = 0;
                curr.color = 1;
                if(uncle != null){
                    uncle.color = 0;
                }
            }
            // if(uncle == null || uncle.color == 1){
                
            // }else{
                
            // }



        }

        return null;
    }    

    public void remove( T data ){

    }


    public String toString(){
        String rtn = "";
        
        // "[15]"
        // 1                       [   ]
        // 2           [   ]                    [   ]
        // 3    [   ]        [   ]        [   ]        [   ]
        // 4 [   ] [   ]  [   ] [   ]  [   ] [   ]  [   ] [   ]
        
        String  val[] = new String[15];
        for(int i = 0; i < 15; i++){
            val[i] = "   ";
        }
        if(root != null){
            val[0] = root.toString();
            if(root.left != null){
                val[1] = root.left.toString();
                if(root.left.left != null){
                    val[3] = root.left.left.toString();
                    if(root.left.left.left != null){
                        val[7] = root.left.left.left.toString();
                    }
                    if(root.left.left.right != null){
                        val[8] = root.left.left.right.toString();
                    }
                }
                if(root.left.right != null){
                    val[4] = root.left.right.toString();
                    if(root.left.right.left != null){
                        val[9] = root.left.right.left.toString();
                    }
                    if(root.left.right.right != null){
                        val[10] = root.left.right.right.toString();
                    }
                }
            }
            if(root.right != null){
                val[2] = root.right.toString();
                if(root.right.left != null){
                    val[5] = root.right.left.toString();
                    if(root.right.left.left != null){
                        val[11] = root.right.left.left.toString();
                    }
                    if(root.right.left.right != null){
                        val[12] = root.right.left.right.toString();
                    }
                }
                if(root.right.right != null){
                    val[6] = root.right.right.toString();
                    if(root.right.right.left != null){
                        val[13] = root.right.right.left.toString();
                    }
                    if(root.right.right.right != null){
                        val[14] = root.right.right.right.toString();
                    }
                }
            }
        }
        String str = """
            |                   [%s]
            |         [%s]                [%s]
            |   [%s]      [%s]     [%s]      [%s]
            |[%s][%s] [%s][%s] [%s][%s] [%s][%s]\n\n
            """;
        System.out.printf(str,val[0],val[1],val[2],val[3],val[4],val[5]
        ,val[6],val[7],val[8],val[9],val[10],val[11],val[12],val[13],val[14]);    
        
        return rtn;
    }


    private class Node<E extends Comparable<E>> {

        public E data;
        public Node<E> left;
        public Node<E> right;
        public int color; // 0 = black, 1 = red

        public Node(E data){
            this.data = data;
            left = null;
            right = null;
            color = 1; // Defalt to Red on creation
        }

        public void setChild(boolean isLeft, Node<E> child){
            if(isLeft){
                this.left = child;
            }else{
                this.right = child;
            }
        }

        public Node<E> getChild(boolean isLeft){
            if(isLeft){
                return this.left;
            }
            return this.right;
        }

        public String toString(){
            String str = this.data.toString();
            if(color == 0){
                str = "B"+str;
            }else{
                str = "R"+str;
            }


            return str;
        }

    }
}
