package com.zhiyin;

import jauter.Routed;
import static com.zhiyin.MyMethod.*;
/**
 * Created by wangqinghui on 2016/12/2.
 */
public class Test
{

    public static void main(String[] args) {
        MyRouter router = new MyRouter()

                .GET      ("/articles",       MyArticleIndex.class)
                .GET      ("/articles/:id",   MyArticleShow.class)
                .GET      ("/download/:*",    MyDownload.class)      // ":*" must be the last token
                .GET_FIRST("/articles/new",   MyArticleNew.class)    // This will be matched first
                .ANY      ("/form_or_create", MyFormOrCreate.class)  // This will match any method

                .GET("/a/b/c",MyArticleIndex.class)

                .GET("/a/:*",MyArticleShow.class)

                .notFound (My404NotFound.class);
//        Routed routed1 = router.route(GET, "/articles/123");
//        System.out.println(routed1.target());
// routed1.target()   => MyArticleShow.class
// routed1.notFound() => false
// routed1.params()   => Map "id" -> "123"

//        Routed routed2 = router.route(GET, "/download/foo/bar.png");
// routed2.target()   => MyDownload.class
// routed2.notFound() => false
// routed2.params()   => Map of "*" -> "foo/bar.png"

//        Routed routed3 = router.route(GET, "/noexist");
// routed3.target()   => My404NotFound.class
// routed3.notFound() => true
// routed3.params()   => Empty Map
// If a notFound were not registered, routed3 will be null

        Routed routed4 = router.route(GET, "/a/b/c");
        System.out.println("ss" + routed4.target());
        System.out.println(routed4.params());

    }
}
