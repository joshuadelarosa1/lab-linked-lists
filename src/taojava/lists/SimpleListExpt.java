package taojava.lists;

import java.io.PrintWriter;

import java.util.ConcurrentModificationException;
import java.util.ListIterator;

/**
 * Some simple experiments with SimpleLists
 */
public class SimpleListExpt
{

  /**
   * Add an element using an iterator.
   */
  static void add(PrintWriter pen, ListIterator<String> it, String val)
    throws Exception
  {
    pen.println("Add \"" + val + "\" at position " + it.nextIndex());
    it.add(val);
  } // add(PrintWriter)

  /**
   * Print a list from back to front.  Intended primarily as an
   * exercise in moving backwards through the list.
   */
  static void printBackwards(PrintWriter pen, SimpleList<String> lst)
  {
    ListIterator lit = lst.listIterator();
    // Move to the end of the list
    while (lit.hasNext())
      lit.next();
    // Print the elements from back to front
    while (lit.hasPrevious())
      {
        pen.print(lit.previousIndex());
        pen.print(": ");
        pen.println(lit.previous());
      } // while
  } // printBackwards

  /**
   * Print a list.
   */
  static void printList(PrintWriter pen, SimpleList<String> lst)
  {
    int i = 0;
    for (String val : lst)
      {
        pen.println(i++ + ": " + val);
      } // for
  } // printList(PrintWriter, SimpleList<String>)

  /**
   * Add a variety of elements.
   */
  static void expt(PrintWriter pen, SimpleList<String> lst, String[] vals)
    throws Exception
  {
    pen.println("Current list: ");
    printList(pen, lst);
    pen.println();

    ListIterator<String> lit = lst.listIterator();

    for (int i = 0; i < vals.length; i++)
      {
        add(pen, lit, vals[i]);
        printList(pen, lst);
        pen.println();
      } // for
  } // expt(PrintWriter, SimpleList<String>, String[])

  /**
   * My default experiment.
   */
  static void expt(PrintWriter pen, SimpleList<String> lst)
    throws Exception
  {
    expt(pen, lst, new String[] { "alpha", "bravo", "charlie" });
    expt(pen, lst, new String[] { "aardvark", "anteater", "antelope" });
  } // expt(PrintWriter, SimpleList<String>)

  /**
   * An experiment in which we try to change a list and then iterate
   * it.
   */
  static void failFastExpt(PrintWriter pen, SimpleList<String> lst)
    throws Exception
  {
    ListIterator<String> lit1 = lst.listIterator();
    ListIterator<String> lit2 = lst.listIterator();
    add(pen, lit1, "addition");
    try
      {
        pen.println("Attempting additional add with alternate iterator.");
        add(pen, lit2, "additional");
        pen.println("Did not fail fast.");
      } // try
    catch (ConcurrentModificationException cme)
      {
        pen.println("Failed fast!");
      } // catch (ConcurrentModificationException)
  } // failFastExpt(PrintWriter, SimpleList<String>)

  /**
   * An experiment that exercises the previous method.
   */
  static void prevExpt(PrintWriter pen, SimpleList<String> lst)
    throws Exception
  {
    expt(pen, lst, new String[] { "alert", "asleep", "awake", "aware" });
    pen.println("Printing backwards.");
    printBackwards(pen, lst);
  } // prevExpt(PrintWriter, SimpleList<String>)
} // class SimpleListExpt
