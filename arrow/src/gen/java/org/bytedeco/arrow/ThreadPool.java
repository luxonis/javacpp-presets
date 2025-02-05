// Targeted by JavaCPP version 1.5.7-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.arrow;

import org.bytedeco.arrow.Function;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;

import static org.bytedeco.arrow.global.arrow.*;


/** An Executor implementation spawning tasks in FIFO manner on a fixed-size
 *  pool of worker threads.
 * 
 *  Note: Any sort of nested parallelism will deadlock this executor.  Blocking waits are
 *  fine but if one task needs to wait for another task it must be expressed as an
 *  asynchronous continuation. */
@Namespace("arrow::internal") @NoOffset @Properties(inherit = org.bytedeco.arrow.presets.arrow.class)
public class ThreadPool extends Executor {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public ThreadPool(Pointer p) { super(p); }

  // Construct a thread pool with the given number of worker threads
  public static native @ByVal ThreadPoolResult Make(int threads);

  // Like Make(), but takes care that the returned ThreadPool is compatible
  // with destruction late at process exit.
  public static native @ByVal ThreadPoolResult MakeEternal(int threads);

  // Destroy thread pool; the pool will first be shut down

  // Return the desired number of worker threads.
  // The actual number of workers may lag a bit before being adjusted to
  // match this value.
  public native int GetCapacity();

  public native @Cast("bool") boolean OwnsThisThread();

  // Return the number of tasks either running or in the queue.
  public native int GetNumTasks();

  // Dynamically change the number of worker threads.
  //
  // This function always returns immediately.
  // If fewer threads are running than this number, new threads are spawned
  // on-demand when needed for task execution.
  // If more threads are running than this number, excess threads are reaped
  // as soon as possible.
  public native @ByVal Status SetCapacity(int threads);

  // Heuristic for the default capacity of a thread pool for CPU-bound tasks.
  // This is exposed as a static method to help with testing.
  public static native int DefaultCapacity();

  // Shutdown the pool.  Once the pool starts shutting down, new tasks
  // cannot be submitted anymore.
  // If "wait" is true, shutdown waits for all pending tasks to be finished.
  // If "wait" is false, workers are stopped as soon as currently executing
  // tasks are finished.
  public native @ByVal Status Shutdown(@Cast("bool") boolean _wait/*=true*/);
  public native @ByVal Status Shutdown();

  // Wait for the thread pool to become idle
  //
  // This is useful for sequencing tests
  public native void WaitForIdle();

  @Opaque public static class State extends Pointer {
      /** Empty constructor. Calls {@code super((Pointer)null)}. */
      public State() { super((Pointer)null); }
      /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
      public State(Pointer p) { super(p); }
  }
}
