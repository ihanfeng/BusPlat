package com.zhiyin.search.es.module.test.controller;

import com.alibaba.fastjson.JSON;

import com.zhiyin.search.es.module.test.entity.Book;
import com.zhiyin.search.es.module.test.service.BookService;
import com.zhiyin.search.es.util.EsUtil;
import com.zhiyin.search.es.web.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;


import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookRestController {

    @Resource
    private BookService bookService;

    @Autowired
    private Client client;


    @RequestMapping(value = "/testok", method = RequestMethod.GET)
    public String test() {
        String hello = "hello you!";
        log.info( hello );
        return hello;
    }

    @RequestMapping(value = "/iniIndex", method = RequestMethod.GET)
    public String iniIndex() {
        log.info( "ini all index." );

        Book book = new Book(1L,"Spring Data Elasticsearch");
        Book book1 = new Book( 2L,"Spring Data");
        Book book2 = new Book(3L,"Spring Data Elasticsearch" );
        bookService.save(book);
        bookService.save(book1);
        bookService.save(book2);

        return "ini succ!";
    }

    /**
     * 添加book
     *
     * @param book
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Book book) {
        log.info("add book request body:{}", JSON.toJSONString(book));

        Book ret = bookService.save(book);
        log.info("add ret:{}", JSON.toJSONString(ret));
        return "";
    }


    /**
     * 删除所有Book索引，非常危险
     *
     * @return
     */
//    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
//    public String deleteAll() {
//        log.info("deleteAll dangerous");
//        bookService.deleteAll();
//        return "";
//    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WebResponse<Book> add(@PathVariable("id") long id) {
        log.info("request id:{}", id);

        Book find = bookService.findOne(id);

        log.info("find id={}, result:{}", id, JSON.toJSONString(find));
        return new WebResponse<Book>(HttpStatus.OK.value(), "", find);
    }


    @RequestMapping(value = "/findName", method = RequestMethod.GET)
    public Page<Book> useRequestParam(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "page" ,defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        log.info("/rest/book3 -> pageNumber = {}", page);


        Pageable pageable = new PageRequest(page, size);

        if (StringUtils.isBlank(query)) {
            return bookService.findAll(pageable);
        }
        return bookService.findByName(query, pageable);
    }


//    @RequestMapping(value = "/rest/book1", method = RequestMethod.GET)
//    public Page<Book> usePageable(
//            @RequestParam(value = "query", required = false) String query,
//            @PageableDefault(size = 10) Pageable pageable) {
//
//        log.info("/rest/book1 -> pageSize = {}, pageNumber = {}",
//                pageable.getPageNumber(), pageable.getPageSize());
//
//        if (StringUtils.isBlank(query)) {
//            return bookService.findAll(pageable);
//        }
//        return bookService.findByMessage(query, pageable);
//    }

//	@RequestMapping(value = "/rest/book2", method = RequestMethod.GET)
//	public Page<Book> useHttpServletRequest(
//			@RequestParam(value = "query", required = false) String query,
//			HttpServletRequest request) {
//
//		int size = 10;
//		int page = Integer.parseInt(request.getParameter("page"));
//
//		log.info("/rest/book2 -> pageNumber = {}", page);
//
//		Pageable pageable = new PageRequest(page, size);
//
//		if (StringUtils.isBlank(query)) {
//			return bookService.findAll(pageable);
//		}
//		return bookService.findByMessage(query, pageable);
//	}

}
