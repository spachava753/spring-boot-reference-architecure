package net.sp.referenceapp.event;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AccountEvent implements Serializable {
  public Map<String, Object> headerMap = new HashMap();
  public String eventName;

  public void addHeader(String key, Object value) {
    headerMap.put(key, value);
  }

  public AccountEvent(String name) {
    eventName = name;
  }

  public Map<String, Object> getHeaderMap() {
    return headerMap;
  }

  public void setHeaderMap(Map<String, Object> headerMap) {
    this.headerMap = headerMap;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

}