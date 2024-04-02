/*Iliya Klishin
 *Dr. Steinberg
 *COP 3503 Summer 2022
 *Programming Assignment 2
 */
 
 class Node<T,U>
 {
  private T name;
  private U office;
  Node<T,U> p;
  Node<T,U> left;
  Node<T,U> right;
  private int color;
  
    public Node() // default constructor for the Node class
    {
      this.name = null;
      this.office = null;
      p = null;
      left = null;
      right = null;
      color = 0;
    }
    
    
    public Node(T name, U office)// Overloaded constructor with T name and U office values
    {
      this.name = name;
      this.office = office;
      this.p = null;
      this.left = null;
      this.right = null;
      this.color = 1;
    }
  
    public T getName()
    {
      return this.name;
    }
    
    public U getOffice()
    {
      return this.office;
    }
    
    public int getColor()
    {
      return this.color;
    }
    
    public Node<T,U> getParent()
    {
      return this.p;
    }
    
    public Node<T,U> getLeft()
    {
      return this.left;
    }
    
    public Node<T,U> getRight()
    {
      return this.right;
    }
    
    public void setName(T name)
    {
      this.name = name;
    }
    
    public void setOffice(U office)
    {
      this.office = office;
    }
    
    public void setColor(int color)
    {
      this.color = color;
    }
    
    public void setParent(Node<T,U> parent)
    {
      this.p = parent;
    }
    
    public void setLeft(Node<T,U> left)
    {
      this.left = left;
    }
    
    public void setRight(Node<T,U> right)
    {
      this.right = right;
    }
     
 
 }
 
 public class AddressBookTree<T extends Comparable<T>, U>
 {
 
   private Node<T,U> root;
   Node<T,U> xih;
   Node<T,U> yih;        
   Node<T,U> yrh;
   Node<T,U> zh;
   
   Node<T,U> nil;
   Node<T,U> s;
   Node<T,U> s1;
   //String fill = "";
   
   private int numBlack;
   private int numRed;
   
   public AddressBookTree() // Default constructor for AddressBookTree class
   {
     this.nil = new Node<T,U>();
     nil.setColor(0);
     
     this.root = nil;
     this.root.p = nil;
     this.root.left = nil;
     this.root.right = nil;
     this.xih = null;
     this.yih = null;
     this.zh = null;
     this.s = new Node<T,U>();
     s.left = nil;
     s.right = nil;
     s.p = nil;
     this.numBlack = 0;
     this.numRed = 0;
   }
   
    //@Override
    //public int compareTo(T name)
    //{
      //return this.root.getName().compareTo(z.getName());
    //}
   
   public Node<T,U> getRoot()
   {
     return this.root;
   }
   
   public void LeftRotate(Node<T,U> x) // Method for Left Rotation
   {
     if(x.right != nil)
     {
     
     Node<T,U> yrh = x.right;
     x.right = yrh.left;
     
     if(yrh.left != nil)
       yrh.left.p = x;
       
     yrh.p = x.p;
     
     if(x.p == nil)
       this.root = yrh;
       
     else if(x == x.p.left)
       x.p.left = yrh;
       
     else
     {
       x.p.right = yrh;
     }
     
     yrh.left = x;
     x.p = yrh;
     
     }
   }
   
   public void RightRotate(Node<T,U> x) //Method for Right Rotation
   {
     if(x.left != nil)
     {
     
     Node<T,U> yrh = x.left;
     x.left = yrh.right;
     
     if(yrh.right != nil)
       yrh.right.p = x;
       
     yrh.p = x.p;
     
     if(x.p == nil)
       this.root = yrh;
       
     else if(x == x.p.right)
       x.p.right = yrh;
       
     else
     {
       x.p.left = yrh;
     }
     
     yrh.right = x;
     x.p = yrh;
     
     }
     
   }
   
   
   
   public void insert(T name, U office)//Method for insertion of the node in the RB tree.
   {
     
     Node<T,U> zh = new Node<T,U>(name, office);
     //z.setName(name);
     //z.setOffice(office);
     
     Node<T,U> yih = nil;
     Node<T,U> xih = getRoot();
     
       while(xih != nil)
       {
         yih = xih;
         
         if(zh.getName().compareTo(xih.getName()) < 0)
         {
           xih = xih.left;
         }
         else
         {
           xih = xih.right;
         }
       }
     
     zh.p = yih;
     
       if(yih == nil)
       {
         this.root = zh;
         //System.out.printf("Root values:%n");
         //System.out.println(this.root.getName() + " " + this.root.getOffice());
       }
       else if(zh.getName().compareTo(yih.getName()) < 0)
       {
         yih.left = zh;
       }
       else
       {
         yih.right = zh;
       }
     
     zh.left = nil;
     zh.right = nil;
     zh.setColor(1);
     
     //if(zh.p.p == nil)
     //{
       //return;
     //}
     //System.out.println("Displaying during insert:");
     //display();
     
     insertFixUp(zh);
     inBlackNum();
     inRedNum();
   }
   
   
   
   public void insertFixUp(Node<T,U> z) //Method for fixing the RB tree after the insertion
   {
     while(z.p.getColor() == 1)
     {
       if(z.p == z.p.p.left)
       {
         Node<T,U> yih = z.p.p.right;
         
         if(yih.getColor() == 1)
         {
           z.p.setColor(0);
           yih.setColor(0);
           z.p.p.setColor(1);
           z = z.p.p;
         }
         
         else
         {
           if(z == z.p.right)
           {
             z = z.p;
             LeftRotate(z);
           }
           
           z.p.setColor(0);
           z.p.p.setColor(1);
           RightRotate(z.p.p);
         }
       }
       
       else
       {
         yih = z.p.p.left;
         
         if(yih.getColor() == 1)
         {
           z.p.setColor(0);
           yih.setColor(0);
           z.p.p.setColor(1);
           z = z.p.p;
         }
         
         else
         {
           if(z == z.p.left)
           {
             z = z.p;
             RightRotate(z);
           }
           
           z.p.setColor(0);
           z.p.p.setColor(1);
           LeftRotate(z.p.p);
         }
       }
       
       if(z == root)
         break;
     }
     
     this.root.setColor(0);
   }
   
   public void RBTransplant(Node<T,U> x, Node<T,U> y) //Transplant method for RB trees
   {
     if(x.p == nil)
     {
       root = y;
     }
     
     else if(x == x.p.left)
     {
       x.p.left = y;
     }
     
     else
     {
       x.p.right = y;
     }
     
     y.p = x.p;
   }
   
   public Node<T,U> search(Node<T,U> root, T name) // This method searches for the node which needs to be deleted
   {
     s.setName(name);
     s1 = root;
     
     if(s1 != nil)
     {
       if(s1.getName().compareTo(s.getName()) == 0)
         return s1;
         
       else if(s1.getName().compareTo(s.getName()) < 0)
         return search(s1.right, name);
       else
         return search(s1.left, name);
     }
     
     else
       return nil;
   }
   
   public Node<T,U> minimum(Node<T,U> z) // This method find the node with the minimum value in the subtree
   {
     while(z.left != nil)
     {
       z = z.left;
     }
     
     return z;
   }
   
   public void deleteNode(T name)// Deletion method
   {
     
     Node<T,U> xih;
     Node<T,U> zh = search(this.root, name);
     
     Node<T,U> yih = zh;
     int y_orig_color = yih.getColor();
     
     if(zh.left == nil)
     {
       xih = zh.right;
       RBTransplant(zh, zh.right);
     }
     
     else if(zh.right == nil)
     {
       xih = zh.left;
       RBTransplant(zh, zh.left);
     }
     
     else
     {
       yih = minimum(zh.right);
       y_orig_color = yih.getColor();
       xih = yih.right;
       
       if(yih.p == zh)
         xih.p = yih;
         
       else
       {
         RBTransplant(yih, yih.right);
         yih.right = zh.right;
         yih.right.p = yih;
       }
       
       RBTransplant(zh, yih);
       yih.left = zh.left;
       yih.left.p = yih;
       yih.setColor(zh.getColor());
     }
     
     if(y_orig_color == 0)
     {
       deleteFixUp(xih);
     }
     
     inBlackNum();
     inRedNum();
   }
   
   public void deleteFixUp(Node<T,U> x)// This method fixes the RB tree after deletion of the node
   {
     Node<T,U> w;
     
     while(x != getRoot() && x.getColor() == 0)
     {
       if(x == x.p.left)
       {
         w = x.p.right;
         if(w.getColor() == 1)
         {
           w.setColor(0);
           x.p.setColor(1);
           LeftRotate(x.p);
           w = x.p.right;
         }
         
         if(w.left.getColor() == 0 && w.right.getColor() == 0)
         {
           w.setColor(1);
           x = x.p;
         }
         
         else
         {
           if(w.right.getColor() == 0)
           {
             w.left.setColor(0);
             w.setColor(1);
             RightRotate(w);
             w = x.p.right;
           }
           
           w.setColor(x.p.getColor());
           x.p.setColor(0);
           w.right.setColor(0);
           LeftRotate(x.p);
           x = root;
         }
         
       }
       
       else
       {
         w = x.p.left;
         if(w.getColor() == 1)
         {
           w.setColor(0);
           x.p.setColor(1);
           RightRotate(x.p);
           w = x.p.left;
         }
         
         if(w.right.getColor() == 0 && w.left.getColor() == 0)
         {
           w.setColor(1);
           x = x.p;
         }
         
         else
         {
           if(w.left.getColor() == 0)
           {
             w.right.setColor(0);
             w.setColor(1);
             LeftRotate(w);
             w = x.p.left;
           }
           
           w.setColor(x.p.getColor());
           x.p.setColor(0);
           w.left.setColor(0);
           RightRotate(x.p);
           x = root;
         }
       }
     }
     
     x.setColor(0);
   }
   
   public void inorder(Node<T,U> root)// This method helps to traverse the RB tree in inorder and print all values in the
   {                                  // sorted ascending order.
     Node<T,U> dh = root;
     //System.out.println(xh.getName() + " " + xh.getOffice());
     if(dh != nil)
     {
       inorder(dh.left);
       System.out.println(dh.getName() + " " + dh.getOffice());
       inorder(dh.right);
     }
   }
   
   public void display()// This method displays all values using inorder traversal.
   {
     inorder(this.root);
   }
   
   public void inBlackNum()// This method resets the number of black nodes to 0.
   {
     this.numBlack = 0;
   }
   
   public void inRedNum()// This method resets the number of red nodes to 0.
   {
     this.numRed = 0;
   }
   
   public int countBlack(Node<T,U> root)// This method counts the number of black nodes in the RB tree.
   {
     Node<T,U> c = root;
     
     if(c != nil)
     {
       if(c.getColor() == 0)
         numBlack++;
       //System.out.println("Num of black nodes:" + numBlack);
       countBlack(c.left);
       countBlack(c.right);
     }
     //if(c.getColor() == 0)
       //return 1;
     //if(c.left != nil && c.right != nil)
       //return countBlack(c.left) + countBlack(c.right);
     
    return numBlack;
     
   }
   
   public int countRed(Node<T,U> root)// This method counts the number of red nodes in the RB tree.
   {
     Node<T,U> c = root;
     
     if(c != nil)
     {
     
       if(c.getColor() == 1)
       {
         numRed++;
       }
       
       countRed(c.left);
       countRed(c.right);
       
     }
     
    
    return numRed;
   }
   
   
 
 }