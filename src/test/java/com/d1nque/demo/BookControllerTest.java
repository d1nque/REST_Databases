package com.d1nque.demo;


import com.d1nque.demo.controller.BookController;
import com.d1nque.demo.model.Book;
import com.d1nque.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class
)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookController bookController;

    @BeforeEach
    void setUp(){
        mvc = MockMvcBuilders
                .standaloneSetup(bookController)
                .build();
    }

    @AfterEach
    public void afterEach(){
        bookService.deleteAll();
    }


    @Test
    public void testCreateBook() throws Exception{
        String title = "Book2";
        String author = "Author4";
        int categoryId = 2;
        String body = """
                {
                "title": "%s",
                "author": "%s",
                "category":{
                    "id": %d
                    }
                }
                """.formatted(title, author, categoryId);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/book/create-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void testCreateBook2() throws Exception{
        Book book = new Book(null, "book1", "author1");

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/book/create-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(book))
                        .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void testGetAllBooks() throws Exception{
        this.mvc
                .perform(MockMvcRequestBuilders.get("/api/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
