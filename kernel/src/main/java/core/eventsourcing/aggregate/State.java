package core.eventsourcing.aggregate;

import core.eventsourcing.TypeTag;

/**
 * The state is the sum of all the events associated with one aggregate.
 * @param <S>      state type
 */
public abstract class State<S> extends TypeTag<S> {

  public State(Class<? extends S> stateType) {
    super(stateType);
  }

  /**
   * @return the stateType
   */
  public String getStateType() {
    return this.getTypeName();
  }

  @SuppressWarnings("unchecked")
  public static <U> State<U> empty() {
    return (State<U>)new Empty();
  }

  private static class Empty extends State<Empty> {
    private Empty() {
      super(Empty.class);
    }
  }

}
