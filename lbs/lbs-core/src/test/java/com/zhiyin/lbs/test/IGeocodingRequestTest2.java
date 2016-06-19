//package com.zhiyin.lbs.test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.zhiyin.map.model.baidu.web.geocoding.BaiduGeocodingToMapModel;
//import com.zhiyin.map.model.baidu.web.geocoding.BaiduGeocodingToRealityModel;
//import com.zhiyin.map.model.base.enmus.CoordinateSystem;
//import com.zhiyin.map.request.baidu.web.geocoding.IBaiduGeocodingRequest;
//import com.zhiyin.utils.json.JSONUtil;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:spring/spring*.xml" })
//public class IGeocodingRequestTest2  {
//
//    @Autowired
//    private IBaiduGeocodingRequest geocodingRequest;
//
////    @Test
//    public void testToMap() {
//        BaiduGeocodingToMapModel model = geocodingRequest.toMap("北京市海淀区中关村南大街27号", "长沙",
//            "7fcf6b7f0ef2fcff63cc42ad4330345d");
//        System.out.println(JSONUtil.toJson(model));
//    }
//
//    @Test
//    public void testToReality() {
//        BaiduGeocodingToRealityModel model = geocodingRequest.toReality("39.990355,116.316538", 2,
//            CoordinateSystem.BD09.getValue(), "7fcf6b7f0ef2fcff63cc42ad4330345d");
//        System.out.println(JSONUtil.toJson(model.getResult().getFormattedAddress()));
//
//
//    }
//
//}
