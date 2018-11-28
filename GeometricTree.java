package comp2402a4;

import java.util.Random;
import java.util.*;

public class GeometricTree extends BinaryTree<GeometricTreeNode> {

 public GeometricTree() {
  super (new GeometricTreeNode());
 }

 /**
  * Set the x and y-coordinates of each node such that it is between the
  * x-coordinate of its two children, no two nodes have the same
  * x-coordinate, and each level of the tree is drawn on separate y-coordinates.
  */

   public void inorderDraw() {
    assignLevels();
    GeometricTreeNode no = firstNode();
    for (int pp = 0; no != nil; pp++) {
     no.position.x = pp;
     no = nextNode(no);

    }
   }

   protected void randomX(GeometricTreeNode u, Random r) {
    if (u == null) {
     return;
    }
    u.position.x = r.nextInt(60);
    randomX(u.left, r);
    randomX(u.right, r);
   }

   /**
    */
   public void leftistDraw() {
    assignLevels();
    GeometricTreeNode ge= r;
    ge.position.x = 0;

    Queue<GeometricTreeNode> queueGeo = new ArrayDeque<GeometricTreeNode>();
    queueGeo.add(r);

    while (!queueGeo.isEmpty()) {
     GeometricTreeNode i = queueGeo.remove();
     if (ge.position.y == i.position.y) {
      i.position.x = ge.position.x + 1;
      ge = i;
     } else {
      i.position.x = 0;
      ge = i;
     }
     if (i.left != nil) queueGeo.add(i.left);
     if (i.right != nil) queueGeo.add(i.right);
    }
    r.position.x = 0;
   }

   public void balancedDraw() {
    setXValue(r);
    setSizes();
    int x = 0;
    int y = 0;

    GeometricTreeNode currentNode = r;
    do {
     while (currentNode.left != nil || currentNode.right != nil) {
      if (currentNode.right != nil && currentNode.left != nil) {
       currentNode = findSmallestNode(currentNode);
       y++;
      } else {
       if (currentNode.left != nil) {
        currentNode = currentNode.left;
       } else {
        currentNode = currentNode.right;
       }
       x++;
      }
      setPosition(currentNode, x, y);
     }
     do {
      currentNode = currentNode.parent;
     } while (currentNode != nil
       && ((currentNode.left == nil || currentNode.right == nil) || findLargestNode(currentNode).position.x > 0));
     if (currentNode == nil) {
      break;
     }
     y = currentNode.position.y;
     currentNode = findLargestNode(currentNode);
     currentNode.position.y = y;
     x++;
     currentNode.position.x = x;
    } while (true);
   }

   protected void setXValue(GeometricTreeNode u) {
    if (u == null) return;

    u.position.x = 0;
    setXValue(u.left);
    setXValue(u.right);
   }

   protected void setPosition(GeometricTreeNode node, int x, int y) {
    node.position.x = x;
    node.position.y = y;
   }

   protected void assignLevels() {
    assignLevels(r, 0);
   }

   protected int sizeOfSubtree(GeometricTreeNode u) {
    if (u.left != nil && u.right != nil) return 1 + u.left.size + u.right.size;
    if (u.left != nil) return 1 + u.left.size;
    else if (u.right != nil) return 1 + u.right.size;
    else {return 1; }
   }

   protected GeometricTreeNode nextNodeTwo(GeometricTreeNode node) {
    if (node.parent != nil && node.parent.left == node) {
     node = node.parent;
     if (node.right != nil) {
      node = node.right;
      while (node.left != nil || node.right != nil) {
       while (node.left != nil) {node = node.left;}
       if (node.right != nil) {node = node.right;}
      }
     }
    } else {
     node = node.parent;
    }
    return node;
   }

   protected GeometricTreeNode findSmallestNode(GeometricTreeNode givenNode) {

    if (givenNode.left.size < givenNode.right.size) {
     return givenNode.left;
    } else {
     return givenNode.right;
    }
   }
   protected GeometricTreeNode findLargestNode(GeometricTreeNode givenNode) {

    if (givenNode.right.size > givenNode.left.size) {
     return givenNode.right;
    } else {
     return givenNode.left;
    }
   }
   private void setSizes(){
    GeometricTreeNode current = firstNode();
    while (current != nil) {
     current.size = sizeOfSubtree(current);
     current = nextNodeTwo(current);
    }
   }

   protected void assignLevels(GeometricTreeNode m, int currentLevel) {
    if (m == null) {
     return;
    }
    m.position.y = currentLevel;
    assignLevels(m.left, currentLevel + 1);
    assignLevels(m.right, currentLevel + 1);
   }


 public static void main(String[] args) {
  GeometricTree t = new GeometricTree();
  galtonWatsonTree(t, 100);
  System.out.println(t);
  t.inorderDraw();
  System.out.println(t);
 }

}
