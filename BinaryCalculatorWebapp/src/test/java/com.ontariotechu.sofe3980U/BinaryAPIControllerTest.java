package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("10001"));
    }
    @Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void add3() throws Exception {
        this.mvc.perform(get("/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void add4() throws Exception {
        this.mvc.perform(get("/add_json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void add5() throws Exception {
        this.mvc.perform(get("/add_json")
                        .param("operand1", "10")
                        .param("operand2", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    /**
     * Tests normal binary multiplication
     * 101 (5) * 10 (2) = 1010 (10)
     */
    @Test
    public void multiply1() throws Exception {
        this.mvc.perform(get("/multiply")
                        .param("operand1", "101")
                        .param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("1010"));
    }

    /**
     * Tests multiplication by zero
     * Any number * 0 = 0
     */
    @Test
    public void multiply2() throws Exception {
        this.mvc.perform(get("/multiply")
                        .param("operand1", "1011")
                        .param("operand2", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    /**
     * Tests multiplication JSON endpoint
     */
    @Test
    public void multiply3() throws Exception {
        this.mvc.perform(get("/multiply_json")
                        .param("operand1", "10")
                        .param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operator").value("multiply"))
                .andExpect(jsonPath("$.result").value(100));
    }

    /**
     * Tests normal OR operation
     * 1010 OR 1100 = 1110
     */
    @Test
    public void or1() throws Exception {
        this.mvc.perform(get("/or")
                        .param("operand1", "1010")
                        .param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(content().string("1110"));
    }

    /**
     * Tests OR with zero
     * 1010 OR 0000 = 1010
     */
    @Test
    public void or2() throws Exception {
        this.mvc.perform(get("/or")
                        .param("operand1", "1010")
                        .param("operand2", "0000"))
                .andExpect(status().isOk())
                .andExpect(content().string("1010"));
    }

    /**
     * Tests OR JSON endpoint
     */
    @Test
    public void or3() throws Exception {
        this.mvc.perform(get("/or_json")
                        .param("operand1", "1010")
                        .param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operator").value("or"))
                .andExpect(jsonPath("$.result").value(1110));
    }

    /**
     * Tests normal AND operation
     * 1010 AND 1100 = 1000
     */
    @Test
    public void and1() throws Exception {
        this.mvc.perform(get("/and")
                        .param("operand1", "1010")
                        .param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(content().string("1000"));
    }

    /**
     * Tests AND with zero
     * Any number AND 0000 = 0
     */
    @Test
    public void and2() throws Exception {
        this.mvc.perform(get("/and")
                        .param("operand1", "1010")
                        .param("operand2", "0000"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    /**
     * Tests AND JSON endpoint.
     */
    @Test
    public void and3() throws Exception {
        this.mvc.perform(get("/and_json")
                        .param("operand1", "1010")
                        .param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operator").value("and"))
                .andExpect(jsonPath("$.result").value(1000));
    }
}