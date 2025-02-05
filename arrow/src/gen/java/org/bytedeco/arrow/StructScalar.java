// Targeted by JavaCPP version 1.5.7-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.arrow;

import org.bytedeco.arrow.Function;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;

import static org.bytedeco.arrow.global.arrow.*;


@Namespace("arrow") @NoOffset @Properties(inherit = org.bytedeco.arrow.presets.arrow.class)
public class StructScalar extends Scalar {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public StructScalar(Pointer p) { super(p); }


  public native @ByRef ScalarVector value(); public native StructScalar value(ScalarVector setter);

  public native @ByVal ScalarResult field(@ByVal FieldRef ref);

  public static native @ByVal StructScalarResult Make(@ByVal @Cast("arrow::StructScalar::ValueType*") ScalarVector value,
                                                      @ByVal StringVector field_names);
}
