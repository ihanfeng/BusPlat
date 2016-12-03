package com.zhiyin;

import jauter.Router;

public class MyRouter extends Router<MyMethod, Class<? extends MyAction>, MyRouter> {
  // This is to overcome method chaining inheritance problem
  // http://stackoverflow.com/questions/1069528/method-chaining-inheritance-don-t-play-well-together-java
  @Override protected MyRouter getThis() { return this; }

  @Override protected MyMethod CONNECT() { return MyMethod.CONNECT; }
  @Override protected MyMethod DELETE()  { return MyMethod.DELETE ; }
  @Override protected MyMethod GET()     { return MyMethod.GET    ; }
  @Override protected MyMethod HEAD()    { return MyMethod.HEAD   ; }
  @Override protected MyMethod OPTIONS() { return MyMethod.OPTIONS; }
  @Override protected MyMethod PATCH()   { return MyMethod.PATCH  ; }
  @Override protected MyMethod POST()    { return MyMethod.POST   ; }
  @Override protected MyMethod PUT()     { return MyMethod.PUT    ; }
  @Override protected MyMethod TRACE()   { return MyMethod.TRACE  ; }
}