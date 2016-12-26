package com.github.q120011676.spring.j2cache.server;

/**
 * Created by say on 3/21/16.
 */
public interface TestService {
    String getName();

    String setName(String name);

    void cleanName();

    void setN(String name);
    void setB(String name);
    void setC(String name);
    String getC();

    void clean();
}
