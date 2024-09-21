package com.kimsang.microservices.core.product;


import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

public abstract class MongoDbTestBase {
  private static MongoDBContainer database = new MongoDBContainer("mongo:4.4.2");

  static {
    database.start();
  }

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", database::getReplicaSetUrl);
    registry.add("spring.data.mongodb.port", () -> database.getMappedPort(27017));
    registry.add("spring.data.mongodb.database", () -> "test");
  }
}
