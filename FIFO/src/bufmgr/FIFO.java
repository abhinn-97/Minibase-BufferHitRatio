
package bufmgr;

import diskmgr.*;
import global.*;
import java.io.*;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
  /**
   * class Policy is a subclass of class Replacer use the given replacement
   * policy algorithm for page replacement
   */
class FIFO extends  Replacer {

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
    /**
     * Class constructor
     * Initializing frames[] pinter = null.
     */
    public FIFO(BufMgr mgrArg) {
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
      // LinkedList<Integer> queue = new LinkedList<Integer>(); 
      // int i =0;
      // if(queue.size() != 0) // get the 1st element in the queue
		  // {
      //   while(i < frametab.length)
      //   {
      //     if (frametab[i].state == AVAILABLE)
      //   {
      //     int frameNumber = queue.poll();
	    // 	  return frameNumber;
      //   }
      //   i += 1;
      //   }
		  // }
	    // return -1;

      // It would check if there is space or not in the buffer, if it finds there is it would be added at the back.
      int valueFrame;
      if ( nframes < frametab.length ) 
      {
        int numberFrames = nframes++;
        valueFrame = numberFrames;
        frames[valueFrame] = valueFrame;
        frametab[valueFrame].state = PINNED;
        return valueFrame;
      }
      else
      {
        // Now this would be involed when the buffer is full, now the first unpinned page would be picked from the buffer pool
        int i = 0;
        while(i < frametab.length)
        {
          valueFrame = frames[i];
          //int value = total_values.get(i);

          //Would the check the state of where the framtab is Eg, frametab[10] is available/refrenced or not, so it would return the first AVAILABLE frame found
          if(frametab[valueFrame].state == AVAILABLE)
          {
            return valueFrame;
          }
          i+=1;
        }
          //This would move all the other frame in front to fill the empty places of a frame which was freed. 
          // while (index < nframes) 
          // {
          //   frames[index-1] = frames[index];
          //   frame[nframes-1] = frameNo;
          //   return value_f
          // } 
    //    
    // }

    // Would return -1 if no new pages are added which indicates that the buffer is full
    return -1;
      }
    }
  }