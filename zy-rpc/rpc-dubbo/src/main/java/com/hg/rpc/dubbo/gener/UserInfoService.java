package com.hg.rpc.dubbo.gener;

import java.util.Map;

public interface UserInfoService {
    public Map<String, String> getUser(String id);
    public Map<String, String>[] getUsers();
}