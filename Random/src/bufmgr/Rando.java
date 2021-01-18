
package bufmgr;

import diskmgr.*;
import global.*;
import java.util.Random;
import java.io.*;
import java.util.*;
import java.util.LinkedList;

  /**
   * class Policy is a subclass of class Replacer use the given replacement
   * policy algorithm for page replacement
   */
class Rando extends  Replacer {

    //
    // Frame State Constants
    //
    protected static final int AVAILABLE = 10;
    protected static final int REFERENCED = 11;
    protected static final int PINNED = 12;

    //Following are the fields required for LRU and MRU policies:
    /**
     * private field
     * An array to hold number of frames in the buffer pool
     */

    private int frames[];

    /**
     * private field
     * number of frames used
     */
    private int nframes;

    /**
     * Clock head; required for the default clock algorithm.
     */
    protected int head;

    /**
     * This pushes the given frame to the end of the list.
     *
     * @param frameNo the frame number
     */
    private void update(int frameNo) {
      //This function is to be used for LRU and MRU
    }
    public Random randoming; 
    /**
     * Class constructor
     * Initializing frames[] pinter = null.
     */
    public Rando(BufMgr mgrArg) {
      super(mgrArg);
      // initialize the frame states
      for (int i = 0; i < frametab.length; i++) {
        frametab[i].state = AVAILABLE;
      }
      // initialize parameters for LRU and MRU
      nframes = 0;
      frames = new int[frametab.length];

      // initialize the clock head for Clock policy
      head = -1;
      randoming = new Random();
    }

    /**
     * Notifies the replacer of a new page.
     */
    public void newPage(FrameDesc fdesc) {
      // no need to update frame state
    }

    /**
     * Notifies the replacer of a free page.
     */
    public void freePage(FrameDesc fdesc) {
      fdesc.state = AVAILABLE;
    }

    /**
     * Notifies the replacer of a pined page.
     */
    public void pinPage(FrameDesc fdesc)
     {
      fdesc.state = PINNED;
    }

    /**
     * Notifies the replacer of an unpinned page.
     */
    public void unpinPage(FrameDesc fdesc) 
    {
      if(fdesc.pincnt == 0 )
      {
        fdesc.state = AVAILABLE;
      } 
      else
      {
        fdesc.state = PINNED;
      }
    }

    /**
     * Finding a free frame in the buffer pool
     * or choosing a page to replace using your policy
     *
     * @return return the frame number
     * return -1 if failed
     */

    public int pickVictim() {
      //remove the below statement and write your code
      LinkedList<Integer> new_page = new LinkedList<Integer>();
      int i = 0;
      // if ( nframes < frametab.length ) 
      // {
      //   nframes += nframes
      //   value_f = nframes;
      //   frames[value_f] = value_f;
      //   frametab[value_f].state = PINNED;
      //   return value_f;
      // }
      //It would check whether or not the buffer is full and if that isn't full it would add the into a list

      while(i<frametab.length)
      {
        if (frametab[i].state == AVAILABLE)
        {
          new_page.add(i);
        }
        i += 1;
      }
      int add_size = new_page.size();

      /*Here it would see that the number of pages added are not equal to 0 so new pages are added so it would then choose a random integer number 
      from the size of the list of values which we have added*/

      
      if(add_size!=0)
      {
        int value = randoming.nextInt(add_size);
        return new_page.get(value);
      }
      // Would return -1 if no new pages are added which indicates that the buffer is full
      return -1;
    }

  }