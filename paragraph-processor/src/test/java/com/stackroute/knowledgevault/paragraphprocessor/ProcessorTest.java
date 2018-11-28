package com.stackroute.knowledgevault.paragraphprocessor;

import com.stackroute.knowledgevault.paragraphprocessor.utilities.DocProcessor;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.FillUpData;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.Pair;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import static org.junit.Assert.*;

public class ProcessorTest {

    private Processor processor;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorTest.class);

    @Before
    public void setUp() {
        this.processor = new Processor();
    }

    @After
    public void tearDown() {
        this.processor = null;
    }

    @Test
    public void paraProcessingTest() {
        this.processor.setFilePath("../paragraph-processor/assets/taggerResource");
        this.processor.setIndexPath("../paragraph-processor/assets/taggerIndices");
        this.processor.getFullTextSearch().indexer(this.processor.getFilePath(),this.processor.getIndexPath());
        String paragraph = "my name is neeraj and i have been suffering from cancer in my lungs. I also have toothache for past 5days";
        LOGGER.info(paragraph);
        this.processor.paraProcessing(paragraph);
    }

    @Test
    public void keywordMapping() {
        this.processor.setFilePath("../paragraph-processor/assets/taggerResource/");
        this.processor.setIndexPath("../paragraph-processor/assets/taggerIndices");
        this.processor.getFullTextSearch().indexer(this.processor.getFilePath(),this.processor.getIndexPath());
        String tag = this.processor.keywordMapping("cancer");
        assertEquals("diseases",tag);
    }

    @Test
    public void testPatternMatch() {
        String dur = "5days";
        assertEquals("not found",this.processor.patMatch(dur));
    }

    @Test
    public void testValidKey() {
        DocProcessor.validKey("running cancers","cancer");
    }

    @Test
    public void jsonldGenTest() {

        Map<String,List<Pair>> tags = new HashMap<>();
        String doc = "The thirteen common communicable diseases found in India are as follows: 1. Malaria 2. Typhoid 3. Hepatitis 4. Jaundice 5. Leptospirosis 6. Diarrhoeal Diseases 7. Amoebiasis 8. Cholera 9. Brucellosis 10. Hookworm Infection 11. Influenza 12. Filariasis 13. Tuberculosis.\n" +
                "\n" +
                "Worldwide, the lack of clean water for drinking, cooking and washing, and the lack of sanitary waste disposal are to blame for over 12 million deaths a year, say researchers. About 1.2 billion people are at risk because they lack access to safe fresh water. India too has its share of infectious epidemics; and though mortality owing to these is decreasing, it is a significant part of the disease burden our society carries.\n" +
                "\n" +
                "The disease burden is high in India, for obvious reasons like poor sanitation, lack of access to fresh water, poor hygiene, etc., which are common in the most developing countries. Though exact dependable statistics are not available, a good percentage of cases go unreported. Secondly, ‘infection is not recognized till it becomes symptomatic.\n" +
                "\n" +
                "Cholera is an acute diarrhoeal disease caused by V. Cholera (classical or El T). It is now commonly due to the El T or biotype. The majority of infections are mild or symptomatic. Epidemics of cholera are characteristically abrupt and often create an acute public health problem. They have a high potential to spread fast and cause deaths. The epidemic reaches a peak and subsides gradually as the ‘force of infection declines. Often, when time control measures are instituted, the epidemic has already reached its peak and is waning\n" +
                "\n" +
                "Typhoid fever is an acute, systemic infection presenting as fever with abdominal symp\u00ADtoms, caused by Salmonella typhi and paratyphi. Before nineteenth century, typhus and typhoid fever were considered to be the same. Enteric fever is an alternative name for typhoid. Salmonella typhi and paratyphi colonise only humans.\n" +
                "\n" +
                "The organisms are acquired via ingestion of food or water, contaminated with human excreta from infected persons. Direct person-to-person transmission is rare. Typhoid is a global health problem. It is seen in children older than the age of one.\n" +
                "\n" +
                "Outbreak of typhoid in developing countries results in high mortality. The recent development of antibiotic resistant organisms is causing much concern. Typhoid fever is more common in the tropics. It tends to occur in places, where the sanitation standards are poor. A bacterial organism called salmonella typhi causes typhoid fever.\n" +
                "\n" +
                "Salmonella paratyphi can also cause fever and abdominal symptoms. The disease caused by both these entities is called enteric fever. The disease presents with a typical, continuous fever for about three to four weeks, relative bradycardia with abdomi\u00ADnal pain (due to enlargement of lymph nodes in the abdomen), and constipation.\n" +
                "\n" +
                "Geographical Distribution Worldwide, typhoid fever affects about six million people with more than 6, 00,000 deaths a year. Almost 80 percent of cases and deaths occur in Asia, and most others in Africa and Latin America. Among Asian countries, India prob\u00ADably has a large number of these cases.\n" +
                "\n" +
                "Indian Statistics Typhoid fever is endemic in India. Health surveys conducted by the Central Ministry of Health in the community development areas indicated a morbidity rate varying from 102 to 2,219 per 1, 00,000 population in different parts of the country. A limited study in an urban slum showed 1 percent of children up to 17 years of age suf\u00ADfer from typhoid fever every year.\n" +
                "\n" +
                "Carriers of Typhoid Fever Typhoid infection is mainly acquired from persons who are carriers of the disease. Carriers are the people who continue to excrete salmonella through their urine and feces a year after an attack of typhoid. A chronic carrier state develops in about 2 to 5 percent of the cases. The organisms in such cases make the gall bladder their habitat.\n" +
                "\n" +
                "dry hacking cough\n" +
                "\n" +
                "Eyes\n" +
                "\n";

        String[] paras = doc.trim().split("\\n+");
        for(String para: paras) {
            Map<String, Double> keys = DocProcessor.performNGramAnalysis(para);
            LOGGER.info("returned keys: {}", keys.keySet());

            for (Map.Entry<String, Double> key : keys.entrySet()) {

                File dictionary = new File("../../knowledge-vault/paragraph-processor/assets/taggerResource/");
                for (File f : dictionary.listFiles()) {
                    tags.putIfAbsent(f.getName(), new ArrayList<>());
                    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                        String txt;
                        while ((txt = br.readLine()) != null) {
                            if (txt.length() > 0) {
                                if (txt.trim().compareToIgnoreCase(key.getKey().trim()) == 0 || key.getKey().trim().compareToIgnoreCase(txt.trim()) == 0) {
                                    Pair p = new Pair(txt.trim(), key.getValue());
                                    tags.get(f.getName()).add(p);
                                    break;
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            LOGGER.info(tags.toString());

            JSONObject obj = FillUpData.fillOntology(tags);
        }
    }
}