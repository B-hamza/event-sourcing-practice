package core.eventsourcing;

public class TypeTag<C> {
    
  private final Class<? extends C> type;

  public TypeTag(Class<? extends C> type) {
    this.type = type;
  }

  public Class<? extends C> getType() {
    return type;
  }

  public String getTypeName() {
    return type.getName();
  }

}
