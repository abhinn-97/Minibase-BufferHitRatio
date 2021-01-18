
package bufmgr;

import diskmgr.*;
import global.*;
import java.io.*;
import java.util.*;
import java.util.LinkedList;

  /**
   * class Policy is a subclass of class Replacer use the given replacement
   * policy algorithm for page replacement
   */
class LRU extends  Replacer {

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
     LinkedList<Integer> list_values = new LinkedList<Integer>();
    private void update(int frameNo) {
      //This function is to be used for LRU and MRU
    }
    /**
     * Class constructor
     * Initializing frames[] pinter = null.
     */
    public LRU(BufMgr mgrArg) {
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
      list_values.remove(new Integer(fdesc.index));
      list_values.add(new Integer(fdesc.index));
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

    //  public Node(int key, int value)
    //  {
    //    this.key = key;
    //    this.value = value;
    //  }

    public int pickVictim() {
      //remove the below statement and write your code
      
      // totBuffers is the frame length(total number of buffers present)
      int value_f;
      
  
  // Would check if the buffer is full or not if there is still space it would add a frame.
   if ( nframes < frametab.length ) 
   {
     //nframes = nframes+=1;
     int numberFrames = nframes++;
     value_f = numberFrames;
     frames[value_f] = value_f;
     frametab[value_f].state = PINNED;
     return value_f;
   }

  else
  {
    //  Node pre;
    //  Node next;

    // private HashMap<Integer, Node> map;
    // bufferCount = frametab.length;
    // private Node head,tail;
  
    // head.next = tail;
    // tail.pre = head;
    // head.pre = 0;
    // tail.next = 0;
    // int count = 0;
    // It checks if the frame is available or not and if it does not find it would choose the LRU from the list to replace, the first is the least used
    int i = 0;
    int total_values = list_values.size();
    while(i < total_values)
    {
      int value = list_values.get(i);
      // int value = total_values.get(i)
      // Would the check the state of where the framtab is Eg, frametab[10] is available/refrenced or not
      if(frametab[value].state == AVAILABLE)
      {
        return value;
      }
      i+=1;
    }

    // Would return -1 if no new pages are added which indicates that the buffer is full
    return -1;
    }
  }
}