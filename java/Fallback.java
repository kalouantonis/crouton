package irresponsible.crouton;

import clojure.lang.IFn;
import clojure.java.api.Clojure;
import java.lang.IllegalArgumentException;
import java.util.HashMap;
import java.util.Map;
import clojure.lang.IPersistentVector;
import clojure.lang.ITransientMap;
import clojure.lang.RT;

public class Fallback implements IRoute {
  private final IRoute first;
  private final IRoute second;
  public Fallback(IRoute f, IRoute s) {
    if (null == f || null == s)
      throw new IllegalArgumentException("Both routes provided to Either must not be non-nil");
    first = f;
    second = s;
  }
  public final Object match(IPersistentVector pieces, ITransientMap places) {
    Object ret = first.match(pieces, places);
    return (ret == null) ? second.match(pieces, places) : ret;
  }
}