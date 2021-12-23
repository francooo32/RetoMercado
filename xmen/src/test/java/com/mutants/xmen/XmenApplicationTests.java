package com.mutants.xmen;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutants.xmen.model.Adn;
import com.mutants.xmen.model.AdnDB;
import com.mutants.xmen.repository.MutantRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class XmenApplicationTests {
    private static final ObjectMapper om = new ObjectMapper();
    @Autowired
    MutantRepository mutantRepository;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
    	mutantRepository.deleteAll();
    }

    @Test
    public void testSecuenceEndpointWithPOSTMutant() throws Exception {
        Adn expectedRecord = getTestData().get("mutant");
        Adn actualRecord = om.readValue(mockMvc.perform(post("/mutant")
                .contentType("application/json")
                .content(om.writeValueAsString(getTestData().get("mutant"))))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Adn.class);

        Assert.assertTrue(new ReflectionEquals(expectedRecord, "id").matches(actualRecord));
        assertEquals(true, mutantRepository.findById(actualRecord.getId()).isPresent());
    }
    
    @Test
    public void testSecuenceEndpointWithPOSTMutant2secuences() throws Exception {
        Adn expectedRecord = getTestData().get("mutantTwo");
        Adn actualRecord = om.readValue(mockMvc.perform(post("/mutant")
                .contentType("application/json")
                .content(om.writeValueAsString(getTestData().get("mutantTwo"))))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Adn.class);

        Assert.assertTrue(new ReflectionEquals(expectedRecord, "id").matches(actualRecord));
        assertEquals(true, mutantRepository.findById(actualRecord.getId()).isPresent());
    }
    
    @Test
    public void testSecuenceEndpointWithPOSTHuman() throws Exception {
        Adn expectedRecord = getTestData().get("human");
        Adn actualRecord = om.readValue(mockMvc.perform(post("/mutant")
                .contentType("application/json")
                .content(om.writeValueAsString(getTestData().get("human"))))
                .andDo(print())
                .andExpect(status().isForbidden()).andReturn().getResponse().getContentAsString(), Adn.class);

        Assert.assertTrue(new ReflectionEquals(expectedRecord, "id").matches(actualRecord));
        assertEquals(true, mutantRepository.findById(actualRecord.getId()).isPresent());
    }
    
    @Test
    public void testSecuenceEndpointWithPOSTHuman2Secuences() throws Exception {
        Adn expectedRecord = getTestData().get("humanTwo");
        Adn actualRecord = om.readValue(mockMvc.perform(post("/mutant")
                .contentType("application/json")
                .content(om.writeValueAsString(getTestData().get("humanTwo"))))
                .andDo(print())
                .andExpect(status().isForbidden()).andReturn().getResponse().getContentAsString(), Adn.class);

        Assert.assertTrue(new ReflectionEquals(expectedRecord, "id").matches(actualRecord));
        assertEquals(true, mutantRepository.findById(actualRecord.getId()).isPresent());
    }

    @Test
    public void testSecuenceEndpointWithGETStats() throws Exception {
        Map<String, Adn> data = getTestData();
        List<Adn> expectedRecordsMutants = new ArrayList<>();

        for (Map.Entry<String, Adn> kv : data.entrySet()) {
        	expectedRecordsMutants.add(om.readValue(mockMvc.perform(post("/mutant")
                    .contentType("application/json")
                    .content(om.writeValueAsString(kv.getValue())))
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString(), Adn.class));
        }
        Collections.sort(expectedRecordsMutants, Comparator.comparing(Adn::getId));

        AdnDB actualRecords = om.readValue(mockMvc.perform(get("/mutant/stats"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), AdnDB.class); 
        

            Assert.assertTrue(new ReflectionEquals(expectedRecordsMutants.size()).matches(actualRecords.getCount_mutant_dna()));
    }

    


    private Map<String, Adn> getTestData() throws ParseException {
        Map<String, Adn> data = new LinkedHashMap<>();

        Adn mutant = new Adn(
        		"es mutante",
                Arrays.asList("AGGTCT", "AGGGGT","ATGGCC", "ATCCCC"),
                Arrays.asList("AGGTCT", "AGGGGT","ATGGCC", "ATCCCC"),
                Arrays.asList("")
                );
        
        data.put("mutant", mutant);

        Adn human = new Adn(
        		"no es mutante",
        		Arrays.asList("AGGTCT", "AGGGTT","ATGGCC", "ATCGCC"),
                Arrays.asList(""),
                Arrays.asList("AGGTCT", "AGGGTT","ATGGCC", "ATCGCC")
                );
        
        data.put("human", human);

        Adn mutant2SecuencesComparation = new Adn(
        		"es mutante",
                Arrays.asList("AGGTGG", "AGGGGT"),
                Arrays.asList("AGGTGG", "AGGGGT"),
                Arrays.asList("")
                );
        
        data.put("mutantTwo", mutant2SecuencesComparation);
        
        Adn human2SecuencesComparation = new Adn(
        		"no es mutante",
        		Arrays.asList("AGGTCT", "AGGGGT"),
                Arrays.asList(""),
                Arrays.asList("AGGTCT", "AGGGGT")
                );
        
        data.put("humanTwo", human2SecuencesComparation);

        return data;
    }
}
