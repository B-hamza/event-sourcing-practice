package core.eventsourcing;

/**
 * The state is the sum of all the events associated with one aggregate.
 * 
 * @param <ID_STATE> state id type
 * @param <ID_COMMAND> command id type
 * @param <STATE> state type
 */
@SuppressWarnings("rawtypes")
public class State<S extends State> {

  private final Class<? extends S> stateType;

  public State(Class<? extends S> stateType) {
    this.stateType = stateType;
  }

  /**
   * @return the stateType
   */
  public String getStateType() {
    return stateType.getName();
  }
}
