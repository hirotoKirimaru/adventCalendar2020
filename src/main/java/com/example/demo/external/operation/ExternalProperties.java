package com.example.demo.external.operation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Setter
@ToString
public abstract class ExternalProperties {
  private String schema;
  private String host;
  private int port;
  private String path;

  @Getter
  private int connectionTimeout;
  @Getter
  private int readTimeout;

  private UriComponentsBuilder createUriBuilder() {
    return UriComponentsBuilder.newInstance()
        .scheme(schema)
        .host(host)
        .port(port != 0 ? port : -1)
        .path(path);
  }

  @ToString.Include(name = "rootUrl")
  public String getUrl() {
    return createUriBuilder().toUriString();
  }

  public String getUrl(String path) {
    return createUriBuilder()
        .path(path)
        .toUriString();
  }
}
