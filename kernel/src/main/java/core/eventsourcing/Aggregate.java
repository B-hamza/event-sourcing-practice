package core.eventsourcing;

/**
 * The aggregate is the (unique) target of a command in the event sourcing
 * driven design architecture. It identifies one state which will be updated by
 * commands.
 * 
 * @param <ID_STATE> state id type
 * @param <STATE> state type
 * @param <ID_COMMAND> command id type
 */
public class Aggregate<ID_STATE extends AggregateId, S extends State<S>, ID_COMMAND> {

  private final ID_STATE id;
  private final ID_COMMAND version;
  private final S state;

  public Aggregate(ID_STATE id, ID_COMMAND version, S state) {
    this.id = id;
    this.version = version;
    this.state = state;
  }

  /**
   * @return the id
   */
  public ID_STATE getId() {
    return id;
  }

  /**
   * @return the version
   */
  public ID_COMMAND getVersion() {
    return version;
  }

  /**
   * @return the state
   */
  public S getState() {
    return state;
  }

}
