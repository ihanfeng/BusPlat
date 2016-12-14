package ch.kerbtier.amarillo.tests.actions;

import ch.kerbtier.amarillo.Route;

@SuppressWarnings("unused")
public class RuleAc {

  @Route(pattern = "a/b/c")
  public Integer integer() {
    return 1;
  }

  @Route(pattern = "a/.*/.*")
  public Integer integer3() {
    return 3;
  }

  @Route(pattern = "a/b/.*")
  public Integer integer2() {
    return 2;
  }



}
